package execution.pages.auth;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.AccountType;
import execution.movies.Movie;
import execution.movies.MoviesDB;
import execution.pages.Page;
import execution.pages.PageQuery;
import execution.pages.PageResponse;
import execution.users.User;

import java.util.ArrayList;
import java.util.Arrays;

public final class SeeDetailsPage extends Page {
    private static SeeDetailsPage instance = null;
    private Movie currentMovie;

    private SeeDetailsPage() {
        super("see details",
                new ArrayList<>(Arrays.asList(
                        "auth-homepage",
                        "movies",
                        "upgrades",
                        "logout")));
        currentMovie = null;
    }

    /**
     * This function is used for the singleton design patterns and returns the only (real) instance
     * of this page.
     * @return The (only) instance of the page.
     */
    public static SeeDetailsPage getInstance() {
        if (instance == null) {
            instance = new SeeDetailsPage();
        }
        return instance;
    }

    /**
     * This method executes the "purchase" feature on the current page.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    private PageResponse executePurchase(final PageQuery pq) {
        // Check if it has already been purchased
        User currentUser = pq.getCurrentUser();

        ArrayList<Movie> purchasedMovies = currentUser.getPurchasedMovies();
        if (currentUser.getNumFreePremiumMovies() > 0
                && currentUser.getAccountType() == AccountType.PREMIUM) {
            // It is already free
            currentUser.setNumFreePremiumMovies(currentUser.getNumFreePremiumMovies() - 1);
            purchasedMovies.add(currentMovie);
        } else if (currentUser.getTokensCount() >= currentMovie.getTokensCost()) {
            // Buy with tokens
            currentUser.setTokensCount(currentUser.getTokensCount() - currentMovie.getTokensCost());
            purchasedMovies.add(currentMovie);
        } else {
            // Cannot buy
            return PageResponse.Builder.createError();
        }
        PageResponse.Builder builder = new PageResponse.Builder();
        return builder.newUser(currentUser).actionOutput(getCurrentMovieAsObjectNode()).build();
    }

    /**
     * This method executes the "watch" feature on the current page.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    private PageResponse executeWatch(final PageQuery pq) {
        User currentUser = pq.getCurrentUser();
        if (!currentUser.getPurchasedMovies().contains(currentMovie)) {
            return PageResponse.Builder.createError();
        }
        currentMovie.watch(currentUser);
        PageResponse.Builder builder = new PageResponse.Builder();
        return builder.newUser(currentUser).actionOutput(getCurrentMovieAsObjectNode()).build();
    }

    /**
     * This method executes the "like" feature on the current page.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    private PageResponse executeLike(final PageQuery pq) {
        User currentUser = pq.getCurrentUser();
        if (!currentUser.getPurchasedMovies().contains(currentMovie)
                || !currentUser.getWatchedMovies().contains(currentMovie)) {
            return PageResponse.Builder.createError();
        }
        if (!currentUser.getLikedMovies().contains(currentMovie)) {
            currentUser.getLikedMovies().add(currentMovie);
            currentMovie.like(currentUser);
        }
        PageResponse.Builder builder = new PageResponse.Builder();
        return builder.newUser(currentUser).actionOutput(getCurrentMovieAsObjectNode()).build();
    }

    /**
     * This method executes the "rate" feature on the current page.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    private PageResponse executeRate(final PageQuery pq) {
        User currentUser = pq.getCurrentUser();
        if (pq.getCurrentActionsInput().getRate() < Movie.MINIMUM_RATING
            || pq.getCurrentActionsInput().getRate() > Movie.MAXIMUM_RATING) {
            return PageResponse.Builder.createError();
        }
        if (!currentUser.getPurchasedMovies().contains(currentMovie)
                || !currentUser.getWatchedMovies().contains(currentMovie)) {
            return PageResponse.Builder.createError();
        }
        if (!currentUser.getRatedMovies().contains(currentMovie)) {
            currentUser.getRatedMovies().add(currentMovie);
            currentMovie.rate(currentUser, pq.getCurrentActionsInput().getRate());
        }
        PageResponse.Builder builder = new PageResponse.Builder();
        return builder.newUser(currentUser).actionOutput(getCurrentMovieAsObjectNode()).build();
    }

    /**
     * This method executes a feature on the current page.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    public PageResponse execute(final PageQuery pq) {
        if (currentMovie == null) {
            return PageResponse.Builder.createError();
        }
        return switch (pq.getCurrentActionsInput().getFeature()) {
            case "purchase" -> executePurchase(pq);
            case "watch" -> executeWatch(pq);
            case "like" -> executeLike(pq);
            case "rate" -> executeRate(pq);
            default -> PageResponse.Builder.createError(); // This should NEVER be reached
        };
    }

    /**
     * This method executes after a given page was just visited.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    public PageResponse afterEnter(final PageQuery pq) {
        // This class does not include an afterEnter method.
        MoviesDB moviesDB = (pq.getMoviesDBSubset() == null) ? pq.getMoviesDB()
                : pq.getMoviesDBSubset();
        currentMovie = moviesDB.searchExact(pq.getCurrentActionsInput().getMovie(),
                pq.getCurrentUser());
        if (currentMovie == null) {
            // This should NEVER be reached (?)
            return PageResponse.Builder.createError();
        }
        PageResponse.Builder builder = new PageResponse.Builder();
        return builder.newUser(pq.getCurrentUser())
                .actionOutput(getCurrentMovieAsObjectNode())
                .build();
    }

    private ObjectNode getCurrentMovieAsObjectNode() {
        ObjectNode objectNode = objectMapper.createObjectNode();
        ArrayNode arrayNode = objectMapper.createArrayNode();
        arrayNode.add(currentMovie.toObjectNode());
        objectNode.set("currentMoviesList", arrayNode);
        return objectNode;
    }
}
