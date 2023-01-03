package execution.notifications;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.movies.Movie;

public final class Notification {
    private Movie movie;
    private NotificationType notificationType;

    public Notification(final Movie movie, final NotificationType notificationType) {
        this.movie = movie;
        this.notificationType = notificationType;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(final Movie movie) {
        this.movie = movie;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(final NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    /**
     * This method converts the notification into an outputable ObjectNode.
     * @return The outputable ObjectNode.
     */
    public ObjectNode toObjectNode() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode returnNode = objectMapper.createObjectNode();

        if (notificationType == NotificationType.RECOMMENDATION) {
            if (movie != null) {
                returnNode.put("movieName", movie.getName());
            } else {
                returnNode.put("movieName", "No recommendation");
            }
        } else {
            assert movie != null;
            returnNode.put("movieName", movie.getName());
        }

        returnNode.put("message", notificationType.toString());
        return returnNode;
    }
}
