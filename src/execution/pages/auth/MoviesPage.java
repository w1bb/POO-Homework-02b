package execution.pages.auth;

import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.movies.MoviesDB;
import execution.pages.Page;
import execution.pages.PageQuery;
import execution.pages.PageResponse;
import fileio.ActionsInput;

import java.util.ArrayList;
import java.util.Arrays;

public final class MoviesPage extends Page {
    private static MoviesPage instance = null;

    private MoviesPage() {
        super("movies",
                new ArrayList<>(Arrays.asList(
                        "auth-homepage",
                        "movies",
                        "see details",
                        "logout")));
    }

    /**
     * This function is used for the singleton design patterns and returns the only (real) instance
     * of this page.
     * @return The (only) instance of the page.
     */
    public static MoviesPage getInstance() {
        if (instance == null) {
            instance = new MoviesPage();
        }
        return instance;
    }

    /**
     * This method executes the "search" feature on the current page.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    private PageResponse executeSearch(final PageQuery pq) {
        PageResponse.Builder builder = new PageResponse.Builder();
        ObjectNode objectNode = objectMapper.createObjectNode();
        MoviesDB curatedList = pq.getMoviesDB().search(
                pq.getCurrentActionsInput().getStartsWith(), pq.getCurrentUser());
        objectNode.set("currentMoviesList", curatedList.toArrayNode());
        return builder.newUser(pq.getCurrentUser())
                .actionOutput(objectNode)
                .moviesDBSubset(curatedList)
                .build();
    }

    /**
     * This method executes the "filter" feature on the current page.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    private PageResponse executeFilter(final PageQuery pq) {
        PageResponse.Builder builder = new PageResponse.Builder();
        ActionsInput actionsInput = pq.getCurrentActionsInput();

        Boolean sortRatingAscending = null;
        Boolean sortDurationAscending = null;
        if (actionsInput.getFilters().getSort() != null) {
            String actionsInputSR = actionsInput.getFilters().getSort().getRating();
            if (actionsInputSR.equals("increasing")) {
                sortRatingAscending = true;
            } else if (actionsInputSR.equals("decreasing")) {
                sortRatingAscending = false;
            }

            String actionsInputSD = actionsInput.getFilters().getSort().getDuration();
            if (actionsInputSD.equals("increasing")) {
                sortDurationAscending = true;
            } else if (actionsInputSD.equals("decreasing")) {
                sortDurationAscending = false;
            }
        }
        ArrayList<String> containsActors = null;
        ArrayList<String> containsGenres = null;
        if (actionsInput.getFilters().getContains() != null) {
            containsActors = actionsInput.getFilters().getContains().getActors();
            containsGenres = actionsInput.getFilters().getContains().getGenre();
        }

        MoviesDB curatedList = pq.getMoviesDB().filter(
                sortRatingAscending, sortDurationAscending,
                containsActors, containsGenres,
                pq.getCurrentUser());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.set("currentMoviesList", curatedList.toArrayNode());
        return builder.newUser(pq.getCurrentUser())
                .actionOutput(objectNode)
                .moviesDBSubset(curatedList)
                .build();
    }

    /**
     * This method executes a feature on the current page.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    public PageResponse execute(final PageQuery pq) {
        return switch (pq.getCurrentActionsInput().getFeature()) {
            case "search" -> executeSearch(pq);
            case "filter" -> executeFilter(pq);
            default -> null;
        };
    }

    /**
     * This method executes after a given page was just visited.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    public PageResponse afterEnter(final PageQuery pq) {
        PageResponse.Builder builder = new PageResponse.Builder();
        ObjectNode objectNode = objectMapper.createObjectNode();
        MoviesDB curatedList = pq.getMoviesDB().search("", pq.getCurrentUser());
        objectNode.set("currentMoviesList", curatedList.toArrayNode());
        return builder.newUser(pq.getCurrentUser())
                .actionOutput(objectNode)
                .moviesDBSubset(curatedList)
                .build();
    }
}
