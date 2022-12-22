package execution.pages;

import execution.movies.MoviesDB;
import execution.users.User;
import execution.users.UsersDB;
import fileio.ActionsInput;

public final class PageQuery {
    private Page currentPage;
    private User currentUser;
    private ActionsInput currentActionsInput;
    private UsersDB usersDB;
    private MoviesDB moviesDB;
    private MoviesDB moviesDBSubset;

    public PageQuery() {
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(final Page currentPage) {
        this.currentPage = currentPage;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }

    public ActionsInput getCurrentActionsInput() {
        return currentActionsInput;
    }

    public void setCurrentActionsInput(final ActionsInput currentActionsInput) {
        this.currentActionsInput = currentActionsInput;
    }

    public UsersDB getUsersDB() {
        return usersDB;
    }

    public void setUsersDB(final UsersDB usersDB) {
        this.usersDB = usersDB;
    }

    public MoviesDB getMoviesDB() {
        return moviesDB;
    }

    public void setMoviesDB(final MoviesDB moviesDB) {
        this.moviesDB = moviesDB;
    }

    public MoviesDB getMoviesDBSubset() {
        return moviesDBSubset;
    }

    public void setMoviesDBSubset(final MoviesDB moviesDBSubset) {
        this.moviesDBSubset = moviesDBSubset;
    }

    @Override
    public String toString() {
        return "PageQuery{"
                +  "currentPage=" + currentPage
                + ", currentUser=" + currentUser
                + ", currentActionsInput=" + currentActionsInput
                + ", usersDB=" + usersDB
                + ", moviesDB=" + moviesDB + '}';
    }
}
