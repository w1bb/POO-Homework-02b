package execution.notifications;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.movies.Movie;

public final class Notification {
    private Movie movie;
    private NotificationType notificationType;

    public Notification(Movie movie, NotificationType notificationType) {
        this.movie = movie;
        this.notificationType = notificationType;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public ObjectNode toObjectNode() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode returnNode = objectMapper.createObjectNode();
        returnNode.put("movieName", movie.getName());
        returnNode.put("message", notificationType.toString());
        return returnNode;
    }
}
