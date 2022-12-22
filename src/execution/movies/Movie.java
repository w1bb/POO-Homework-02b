package execution.movies;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.users.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class Movie {
    public static final int MINIMUM_RATING = 1;
    public static final int MAXIMUM_RATING = 5;
    private static final int GENERAL_COST = 2;
    private final String name;
    private final int year;
    private final int duration;
    private final ArrayList<String> genres;
    private final ArrayList<String> actors;
    private final ArrayList<String> countriesBanned;

    private final ArrayList<User> watched;
    private final ArrayList<User> likes;
    private final ArrayList<User> dislikes;
    private final HashMap<User, Integer> ratings;
    private final int tokensCost;

    public Movie(final String name, final int year, final int duration,
                 final ArrayList<String> genres, final ArrayList<String> actors,
                 final ArrayList<String> countriesBanned) {
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.genres = genres;
        this.actors = actors;
        this.countriesBanned = countriesBanned;
        this.likes = new ArrayList<>();
        this.dislikes = new ArrayList<>();
        this.watched = new ArrayList<>();
        this.ratings = new HashMap<>();
        this.tokensCost = GENERAL_COST;
    }

    public String getName() {
        return this.name;
    }

    /**
     * This method checks if a given movie can be watched by a user based on its country.
     * @param user The user to check with.
     * @return true if and only if the movie can be watched by a given user.
     */
    public boolean isBannedForUser(final User user) {
        return this.countriesBanned.contains(user.getCountry());
    }

    public ArrayList<String> getCountriesBanned() {
        return this.countriesBanned;
    }

    public ArrayList<String> getActors() {
        return this.actors;
    }

    public ArrayList<String> getGenres() {
        return this.genres;
    }

    public int getTokensCost() {
        return tokensCost;
    }

    /**
     * This method "watches" a movie for a given user.
     * @param user The user to watch the movie for.
     */
    public void watch(final User user) {
        if (!user.getWatchedMovies().contains(this)) {
            user.getWatchedMovies().add(this);
            watched.add(user);
        }
    }

    /**
     * This method "likes" a movie for a given user.
     * @param user The user to like the movie for.
     */
    public void like(final User user) {
        if (!watched.contains(user)) {
            return;
        }
        dislikes.remove(user);
        if (!likes.contains(user)) {
            likes.add(user);
        }
    }

    /**
     * This method "dislikes" a movie for a given user. Currently, this function is not used, but
     * future functionality may be implemented later on.
     * @param user The user to dislike the movie for.
     */
    public void dislike(final User user) {
        if (!watched.contains(user)) {
            return;
        }
        likes.remove(user);
        if (!dislikes.contains(user)) {
            dislikes.add(user);
        }
    }

    /**
     * This method returns the current number of likes. Currently, this function is not used, but
     * future functionality may be implemented later on.
     * @return The number of likes for a given movie.
     */
    public int getLikeCount() {
        return likes.size();
    }

    /**
     * This method returns the current number of dislikes. Currently, this function is not used, but
     * future functionality may be implemented later on.
     * @return The number of dislikes for a given movie.
     */
    public int getDislikeCount() {
        return dislikes.size();
    }

    /**
     * This method "rates" a movie for a given user.
     * @param user The user to rate a movie for.
     * @param rating The rating (expected <= 5)
     */
    public void rate(final User user, final int rating) {
        ratings.put(user, rating);
    }

    /**
     * This method computes a movie's rating as the mean value of all the ratings.
     * @return The movie's rating.
     */
    public double computeRating() {
        double rating = 0;
        for (Map.Entry<User, Integer> x : ratings.entrySet()) {
            rating += (double) x.getValue();
        }
        return (ratings.size() == 0) ? 0 : rating / ratings.size();
    }

    public int getDuration() {
        return duration;
    }

    /**
     * This method creates an ObjectNode containing all the information present in a given movie.
     * @return An outputable ObjectNode.
     */
    public ObjectNode toObjectNode() {
        final double digitsPow = 100.0;
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode returnNode = objectMapper.createObjectNode();
        returnNode.put("name", name);
        returnNode.put("year", year);
        returnNode.put("duration", duration);
        returnNode.set("genres", objectMapper.valueToTree(genres));
        returnNode.set("actors", objectMapper.valueToTree(actors));
        returnNode.set("countriesBanned", objectMapper.valueToTree(countriesBanned));
        returnNode.put("numLikes", likes.size());
        double computedRating = (double) Math.round(computeRating() * digitsPow) / digitsPow;
        returnNode.put("rating", computedRating);
        returnNode.put("numRatings", ratings.size());
        return returnNode;
    }

    @Override
    public String toString() {
        return "Movie{"
                + "name='" + name + '\''
                + ", year=" + year
                + ", duration=" + duration
                + ", genres=" + genres
                + ", actors=" + actors
                + ", countriesBanned=" + countriesBanned
                + ", watched=" + watched
                + ", likes=" + likes
                + ", dislikes=" + dislikes
                + ", ratings=" + ratings
                + ", tokensCost=" + tokensCost + '}';
    }
}
