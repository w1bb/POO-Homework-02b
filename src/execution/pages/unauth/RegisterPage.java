package execution.pages.unauth;

import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.pages.Page;
import execution.pages.PageFactory;
import execution.pages.PageQuery;
import execution.pages.PageResponse;
import execution.users.User;
import fileio.CredentialsInput;
import fileio.UsersInput;

import java.util.ArrayList;
import java.util.Arrays;

public final class RegisterPage extends Page {
    private static RegisterPage instance = null;

    private RegisterPage() {
        super("register",
                new ArrayList<>(Arrays.asList(
                        "login",
                        "register")));
    }

    /**
     * This function is used for the singleton design patterns and returns the only (real) instance
     * of this page.
     * @return The (only) instance of the page.
     */
    public static RegisterPage getInstance() {
        if (instance == null) {
            instance = new RegisterPage();
        }
        return instance;
    }

    /**
     * This method executes the "register" feature on the current page.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    private PageResponse executeRegister(final PageQuery pq) {
        CredentialsInput newUserCredentials = pq.getCurrentActionsInput().getCredentials();
        UsersInput newUserInput = new UsersInput();
        newUserInput.setCredentials(newUserCredentials);
        User newUser = newUserInput.toUser();
        if (pq.getUsersDB().checkName(newUser.getName())) {
            return PageResponse.Builder.createError();
        }

        // Add new user and change current user
        pq.getUsersDB().add(newUser);
        PageResponse.Builder builder = new PageResponse.Builder();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.set("currentUser", newUser.toObjectNode());

        return builder.newUser(newUser)
                .newPage(PageFactory.getPage("auth-homepage"))
                .actionOutput(objectNode).build();
    }

    /**
     * This method executes a feature on the current page.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    public PageResponse execute(final PageQuery pq) {
        return pq.getCurrentActionsInput().getFeature().equals("register")
                ? executeRegister(pq) : PageResponse.Builder.createError();
    }

    /**
     * This method executes after a given page was just visited. In this case, nothing happens.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    public PageResponse afterEnter(final PageQuery pq) {
        // This class does not include an afterEnter method.
        return null;
    }
}
