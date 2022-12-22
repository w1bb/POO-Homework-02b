package fileio;

import java.util.ArrayList;

public final class Input {
    private ArrayList<UsersInput> users;
    private ArrayList<MovieInput> movies;
    private ArrayList<ActionsInput> actions;

    public Input() {
    }

    public ArrayList<UsersInput> getUsers() {
        return this.users;
    }

    public void setUsers(final ArrayList<UsersInput> users) {
        this.users = users;
    }

    public ArrayList<MovieInput> getMovies() {
        return this.movies;
    }

    public void setMovies(final ArrayList<MovieInput> movies) {
        this.movies = movies;
    }

    public ArrayList<ActionsInput> getActions() {
        return this.actions;
    }

    public void setActions(final ArrayList<ActionsInput> actions) {
        this.actions = actions;
    }
}
