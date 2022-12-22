package execution.users;

import java.util.ArrayList;

public final class UsersDB {
    private final ArrayList<User> users;

    public UsersDB() {
        users = new ArrayList<>();
    }

    /**
     * This method adds another user to the database
     * @param user The user to be added
     */
    public void add(final User user) {
        // Maybe add some checks before?
        users.add(user);
    }

    /**
     * This method searches for a given user in the database. If there is at least a user with the
     * correct credentials, then the first valid instance is returned.
     * @param name The searched user's username
     * @param password The searched user's password
     * @return The first user with the given credentials
     */
    public User search(final String name, final String password) {
        for (User user : users) {
            if (user.getName().equals(name) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    /**
     * This method checks if a given username is attributed to one of the users in the database.
     * @param name The name to be checked
     * @return true if and only if there is a user with the given username
     */
    public boolean checkName(final String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
