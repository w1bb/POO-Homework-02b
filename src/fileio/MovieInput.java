package fileio;

import execution.movies.Movie;

import java.util.ArrayList;

public final class MovieInput {
    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;

    public MovieInput() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(final int year) {
        this.year = year;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public ArrayList<String> getGenres() {
        return this.genres;
    }

    public void setGenres(final ArrayList<String> genre) {
        this.genres = genre;
    }

    public ArrayList<String> getActors() {
        return this.actors;
    }

    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getCountriesBanned() {
        return this.countriesBanned;
    }

    public void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    /**
     * This method converts an input to a real Movie type.
     * @return The converted type
     */
    public Movie toMovie() {
        return new Movie(name, year, duration, genres, actors, countriesBanned);
    }
}
