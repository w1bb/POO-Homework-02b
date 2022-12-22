package execution.movies;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import execution.users.User;

import java.util.ArrayList;

public final class MoviesDB {
    private final ArrayList<Movie> movies;

    public MoviesDB() {
        movies = new ArrayList<>();
    }

    /**
     * This method adds a given movie to the database.
     * @param movie The movie to be added to the database.
     */
    public void add(final Movie movie) {
        movies.add(movie);
    }

    /**
     * This method searches for a movie that starts with a given name and is available for a given
     * user.
     * @param startsWith The String representing the beginning of a valid movie;
     * @param forUser The user for whom the search is completed.
     * @return A movie that starts with startsWith and can be viewed by the given user or null in
     * case such a movie does not exist.
     */
    public MoviesDB search(final String startsWith,
                           final User forUser) {
        MoviesDB moviesDB = new MoviesDB();
        for (Movie movie : movies) {
            if (movie.getName().startsWith(startsWith)
                    && !movie.isBannedForUser(forUser)) {
                moviesDB.add(movie);
            }
        }
        return moviesDB;
    }

    /**
     * This method searches for an exact movie name.
     * @param movieName The name of the movie to be searched;
     * @param forUser The user for whom the search is to be completed.
     * @return A movie if the specified name exists in the database or null otherwise.
     */
    public Movie searchExact(final String movieName, final User forUser) {
        for (Movie movie : movies) {
            if (movie.getName().equals(movieName)) {
                return movie.isBannedForUser(forUser) ? null : movie;
            }
        }
        return null;
    }

    /**
     * This method sorts in-place the database based on some criteria.
     * @param sortRatingAscending true/false if the output should be sorted in ascending/descending
     *                            order based on the movies' ratings or none otherwise;
     * @param sortDurationAscending true/false if the output should be sorted in
     *                              ascending/descending order based on the movies' durations or
     *                              none otherwise.
     */
    private void selfSort(final Boolean sortRatingAscending, final Boolean sortDurationAscending) {
        movies.sort((a, b) -> {
            if (sortDurationAscending != null) {
                if (a.getDuration() != b.getDuration()) {
                    return (sortDurationAscending ? 1 : -1) * (a.getDuration() - b.getDuration());
                }
            }
            if (sortRatingAscending != null) {
                final int aRatingInt = (int) Math.round(a.computeRating() * 100.0);
                final int bRatingInt = (int) Math.round(b.computeRating() * 100.0);
                if (aRatingInt != bRatingInt) {
                    return (sortRatingAscending ? 1 : -1) * (aRatingInt - bRatingInt);
                }
            }
            // This should never be reached!
            return 0;
        });
    }

    /**
     * This method creates a copy of a database and then filters (and sorts) it.
     * @param sortRatingAscending true/false if the output should be sorted in ascending/descending
     *                            order based on the movies' ratings or none otherwise;
     * @param sortDurationAscending true/false if the output should be sorted in
     *                              ascending/descending order based on the movies' durations or
     *                              none otherwise;
     * @param actors If specified, these are the filtered (required) actors;
     * @param genres If specified, these are the filtered (required) genres
     * @param forUser The user for whom the filtering is done.
     * @return A curated movies database.
     */
    public MoviesDB filter(final Boolean sortRatingAscending, final Boolean sortDurationAscending,
                           final ArrayList<String> actors, final ArrayList<String> genres,
                           final User forUser) {
        MoviesDB filteredDB = new MoviesDB();
        for (Movie movie : movies) {
            boolean addable = !movie.isBannedForUser(forUser);
            if (!addable) {
                continue;
            }
            // Check each actor
            if (actors != null) {
                for (String neededActor : actors) {
                    if (!movie.getActors().contains(neededActor)) {
                        addable = false;
                        break;
                    }
                }
                if (!addable) {
                    continue;
                }
            }
            // Check each genre
            if (genres != null) {
                for (String neededGenre : genres) {
                    if (!movie.getGenres().contains(neededGenre)) {
                        addable = false;
                        break;
                    }
                }
                if (!addable) {
                    continue;
                }
            }
            // Add good movies
            filteredDB.add(movie);
        }
        // Sort the movies and return
        filteredDB.selfSort(sortRatingAscending, sortDurationAscending);
        return filteredDB;
    }

    /**
     * This method converts the database into an ArrayNode of information.
     * @return An outputable ArrayNode.
     */
    public ArrayNode toArrayNode() {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode returnNode = objectMapper.createArrayNode();
        for (Movie movie : movies) {
            returnNode.add(movie.toObjectNode());
        }
        return returnNode;
    }
}
