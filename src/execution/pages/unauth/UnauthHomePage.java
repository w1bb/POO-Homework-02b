package execution.pages.unauth;

import execution.pages.Page;
import execution.pages.PageQuery;
import execution.pages.PageResponse;

import java.util.ArrayList;
import java.util.Arrays;

public final class UnauthHomePage extends Page {
    private static UnauthHomePage instance = null;

    private UnauthHomePage() {
        super("unauth-homepage",
                new ArrayList<>(Arrays.asList(
                        "login",
                        "register")));
    }

    /**
     * This function is used for the singleton design patterns and returns the only (real) instance
     * of this page.
     * @return The (only) instance of the page.
     */
    public static UnauthHomePage getInstance() {
        if (instance == null) {
            instance = new UnauthHomePage();
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
