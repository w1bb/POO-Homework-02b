package fileio;

public final class ActionsFiltersSortInput {
    private String rating;
    private String duration;

    public ActionsFiltersSortInput() {
        rating = "";
        duration = "";
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(final String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(final String duration) {
        this.duration = duration;
    }
}
