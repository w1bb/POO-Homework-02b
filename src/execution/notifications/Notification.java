package execution.notifications;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Notification {
    String movieName;
    NotificationType notificationType;

    public Notification(String movieName, NotificationType notificationType) {
        this.movieName = movieName;
        this.notificationType = notificationType;
    }

    public ObjectNode toObjectNode() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode returnNode = objectMapper.createObjectNode();
        returnNode.put("movieName", movieName);
        returnNode.put("message", notificationType.toString());
        return returnNode;
    }
}
