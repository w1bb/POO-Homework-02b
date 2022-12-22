package execution.pages.auth;

import execution.pages.Page;
import execution.pages.PageQuery;
import execution.pages.PageResponse;
import execution.users.User;

import java.util.ArrayList;
import java.util.Arrays;

public final class UpgradesPage extends Page {
    private static UpgradesPage instance = null;

    private UpgradesPage() {
        super("upgrades",
                new ArrayList<>(Arrays.asList(
                        "auth-homepage",
                        "movies",
                        "logout")));
    }

    /**
     * This function is used for the singleton design patterns and returns the only (real) instance
     * of this page.
     * @return The (only) instance of the page.
     */
    public static UpgradesPage getInstance() {
        if (instance == null) {
            instance = new UpgradesPage();
        }
        return instance;
    }

    /**
     * This method executes the "buy tokens" feature on the current page.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    private PageResponse executeBuyTokens(final PageQuery pq) {
        User currentUser = pq.getCurrentUser();
        boolean bought = currentUser.buyTokens(
                Integer.parseInt(pq.getCurrentActionsInput().getCount()));
        PageResponse.Builder builder = new PageResponse.Builder();
        return bought ? builder.newUser(currentUser).build() : PageResponse.Builder.createError();
    }

    /**
     * This method executes the "buy premium account" feature on the current page.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    private PageResponse executeBuyPremiumAccount(final PageQuery pq) {
        User currentUser = pq.getCurrentUser();
        boolean bought = currentUser.buyPremium();
        PageResponse.Builder builder = new PageResponse.Builder();
        return bought ? builder.newUser(currentUser).build() : PageResponse.Builder.createError();
    }

    /**
     * This method executes a feature on the current page.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    public PageResponse execute(final PageQuery pq) {
        return switch (pq.getCurrentActionsInput().getFeature()) {
            case "buy tokens" -> executeBuyTokens(pq);
            case "buy premium account" -> executeBuyPremiumAccount(pq);
            default -> PageResponse.Builder.createError();
        };
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
