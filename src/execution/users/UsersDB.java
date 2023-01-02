package execution.users;

import execution.AccountType;
import execution.movies.Movie;
import execution.notifications.Notification;
import execution.notifications.NotificationType;

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

    public void notifyUsers(Notification notification) {
        assert notification != null;
        final Movie movie = notification.getMovie();
        for (User user : users) {
            if (notification.getNotificationType() == NotificationType.DATABASE_ADD) {
                for (String movieGenre : movie.getGenres()) {
                    if (user.getSubscribedGenres().contains(movieGenre)) {
                        user.getNotifications().add(notification);
                        break;
                    }
                }
            } else if (notification.getNotificationType() == NotificationType.DATABASE_REMOVE) {
                if (user.getPurchasedMovies().contains(notification.getMovie())) {
                    user.getPurchasedMovies().remove(notification.getMovie());
                    if (user.getAccountType() == AccountType.PREMIUM) {
                        user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() + 1);
                    } else {
                        user.setTokensCount(user.getTokensCount() + movie.getTokensCost());
                    }
                    user.getNotifications().add(notification);
                }
            }
        }
    }
}
