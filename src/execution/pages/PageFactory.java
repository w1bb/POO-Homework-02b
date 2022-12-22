package execution.pages;

import execution.pages.auth.AuthHomePage;
import execution.pages.auth.LogoutPage;
import execution.pages.auth.MoviesPage;
import execution.pages.auth.SeeDetailsPage;
import execution.pages.auth.UpgradesPage;
import execution.pages.unauth.LoginPage;
import execution.pages.unauth.RegisterPage;
import execution.pages.unauth.UnauthHomePage;

public final class PageFactory {
    private PageFactory() {
    }

    /**
     * This method creates a new page based on a given name.
     * @param pageName The name of the required page.
     * @return The requested page.
     */
    public static Page getPage(final String pageName) {
        return switch (pageName) {
            case "login" -> LoginPage.getInstance();
            case "register" -> RegisterPage.getInstance();
            case "movies" -> MoviesPage.getInstance();
            case "upgrades" -> UpgradesPage.getInstance();
            case "see details" -> SeeDetailsPage.getInstance();
            case "logout" -> LogoutPage.getInstance();
            case "auth-homepage" -> AuthHomePage.getInstance();
            case "unauth-homepage" -> UnauthHomePage.getInstance();
            default -> null;
        };
    }
}
