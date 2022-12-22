package execution.pages.auth;

import execution.pages.Page;
import execution.pages.PageFactory;
import execution.pages.PageQuery;
import execution.pages.PageResponse;

public final class LogoutPage extends Page {
    private static LogoutPage instance = null;

    private LogoutPage() {
        super("logout", null);
    }

    /**
     * This function is used for the singleton design patterns and returns the only (real) instance
     * of this page.
     * @return The (only) instance of the page.
     */
    public static LogoutPage getInstance() {
        if (instance == null) {
            instance = new LogoutPage();
        }
        return instance;
    }

    /**
     * This method executes a feature on the current page. In this case, nothing happens.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    public PageResponse execute(final PageQuery pq) {
        // This class does not include an execute method.
        return null;
    }

    /**
     * This method executes after a given page was just visited.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    public PageResponse afterEnter(final PageQuery pq) {
        PageResponse.Builder builder = new PageResponse.Builder();
        return builder.newPage(PageFactory.getPage("unauth-homepage")).build();
    }
}
