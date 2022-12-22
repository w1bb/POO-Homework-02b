package execution.pages.auth;

import execution.pages.Page;
import execution.pages.PageQuery;
import execution.pages.PageResponse;

import java.util.ArrayList;
import java.util.Arrays;

public final class AuthHomePage extends Page {
    private static AuthHomePage instance = null;

    private AuthHomePage() {
        super("auth-homepage",
                new ArrayList<>(Arrays.asList(
                        "movies",
                        "upgrades",
                        "logout")));
    }

    /**
     * This function is used for the singleton design patterns and returns the only (real) instance
     * of this page.
     * @return The (only) instance of the page.
     */
    public static AuthHomePage getInstance() {
        if (instance == null) {
            instance = new AuthHomePage();
        }
        return instance;
    }

    /**
     * This method executes a feature on the current page. In this case, nothing happens.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    public PageResponse execute(final PageQuery pq) {
        return PageResponse.Builder.createError();
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
