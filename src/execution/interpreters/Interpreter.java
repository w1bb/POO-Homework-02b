package execution.interpreters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.interpreters.back.BackInterpreter;
import execution.interpreters.changepage.ChangePageInterpreter;
import execution.interpreters.database.DatabaseInterpreter;
import execution.interpreters.onpage.OnPageInterpreter;
import execution.interpreters.subscribe.SubscribeInterpreter;
import execution.movies.MoviesDB;
import execution.pages.Page;
import execution.pages.PageQuery;
import execution.pages.PageResponse;
import execution.pages.unauth.UnauthHomePage;
import execution.users.User;
import execution.users.UsersDB;
import fileio.ActionsInput;
import fileio.Input;

import java.util.ArrayList;

public final class Interpreter implements GeneralInterpreter {
    private final UsersDB usersDB;
    private final MoviesDB moviesDB;

    private final ArrayList<ActionsInput> allActionsInputs;

    private final ChangePageInterpreter changePageInterpreter;

    private final OnPageInterpreter onPageInterpreter;

    private final BackInterpreter backInterpreter;

    private final SubscribeInterpreter subscribeInterpreter;

    private final DatabaseInterpreter databaseInterpreter;

    public Interpreter(final Input input) {
        this.usersDB = new UsersDB();
        this.moviesDB = new MoviesDB();
        input.getUsers().forEach(usersInput -> usersDB.add(usersInput.toUser()));
        input.getMovies().forEach(movieInput -> moviesDB.add(movieInput.toMovie()));
        allActionsInputs = input.getActions();

        changePageInterpreter = new ChangePageInterpreter();
        onPageInterpreter = new OnPageInterpreter();
        backInterpreter = new BackInterpreter();
        subscribeInterpreter = new SubscribeInterpreter();
        databaseInterpreter = new DatabaseInterpreter();
    }

    /**
     * This method executes a given action.
     * @param pq The action to be executed, alongside other useful information.
     * @return A PageResponse containing the information required by the Interpreter.
     */
    public PageResponse executeAction(final PageQuery pq) {
        PageResponse returnValue = switch (pq.getCurrentActionsInput().getType()) {
            case "change page" -> changePageInterpreter.executeAction(pq);
            case "on page" -> onPageInterpreter.executeAction(pq);
            case "subscribe" -> subscribeInterpreter.executeAction(pq);
            case "back" -> backInterpreter.executeAction(pq);
            case "database" -> databaseInterpreter.executeAction(pq);
            // This should NEVER be reached
            default -> null;
        };
//        assert returnValue != null;
        if (returnValue != null && returnValue.getRerunAction() != null) {
            pq.setCurrentActionsInput(returnValue.getRerunAction());
            return executeAction(pq);
        }
        return returnValue;
    }

    /**
     * This method runs all the actions present in an input file.
     * @return An outputable ArrayNode.
     */
    public ArrayNode runActions() {
        User currentUser = null;
        Page currentPage = UnauthHomePage.getInstance();

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode returnNode = objectMapper.createArrayNode();

        PageQuery pq = new PageQuery();
        pq.setMoviesDB(moviesDB);
        pq.setUsersDB(usersDB);
        for (ActionsInput actionsInput : allActionsInputs) {
            pq.setCurrentActionsInput(actionsInput);
            pq.setCurrentPage(currentPage);
            pq.setCurrentUser(currentUser);
            PageResponse pageResponse = executeAction(pq);
            if (pageResponse == null) {
                ObjectNode objectNode = objectMapper.createObjectNode();
                objectNode.set("currentMoviesList", objectMapper.createArrayNode());
                objectNode.set("currentUser", null);
                objectNode.put("error", "Error");
                returnNode.add(objectNode);
                continue;
            }
            User originalCurrentUser = currentUser;
            Page originalCurrentPage = currentPage;
            pq.setMoviesDBSubset(pageResponse.getMoviesDBSubset());
            while (pageResponse != null) {
                if (pageResponse.getActionOutput() != null) {
                    ObjectNode objectNode = pageResponse.getActionOutput();
                    if (!objectNode.has("error")) {
                        objectNode.set("error", null);
                    } else {
                        objectNode.set("currentMoviesList", objectMapper.createArrayNode());
                        objectNode.set("currentUser", null);
                        currentUser = originalCurrentUser;
                        currentPage = originalCurrentPage;
                        returnNode.add(objectNode);
                        break;
                    }
                    if (!objectNode.has("currentMoviesList")) {
                        objectNode.set("currentMoviesList", objectMapper.createArrayNode());
                    }
                    if (!objectNode.has("currentUser")) {
                        assert currentUser != null;
                        objectNode.set("currentUser", currentUser.toObjectNode());
                    }
                    returnNode.add(objectNode);
                }
                currentUser = pageResponse.getNewUser();
                pq.setCurrentUser(currentUser);
                if (pageResponse.getNewPage() == null) {
                    break;
                }
                currentPage = pageResponse.getNewPage();
                pq.setCurrentPage(currentPage);
                pageResponse = currentPage.afterEnter(pq);
            }
            // The page changed
            if (originalCurrentPage != currentPage) {
                ArrayList<String> visitedPages = pq.getVisitedPages();
                visitedPages.add(currentPage.getName());
                ArrayList<ActionsInput> pastActions = pq.getPastActions();
                pastActions.add(actionsInput);
                System.out.println(visitedPages);
            }
        }
        return returnNode;
    }
}
