package execution.interpreters;

import execution.movies.Movie;
import execution.notifications.Notification;
import execution.pages.PageQuery;
import execution.pages.PageResponse;

public final class DatabaseInterpreter implements GeneralInterpreter {

    private PageResponse executeAdd(final PageQuery pq) {
        Movie newMovie = pq.getCurrentActionsInput().getAddedMovie().toMovie();
        Notification notification = pq.getMoviesDB().add(newMovie);
        if (notification == null) {
            return PageResponse.Builder.createError();
        }
        pq.getUsersDB().notifyUsers(notification);
        PageResponse.Builder builder = new PageResponse.Builder();
        return builder.newUser(pq.getCurrentUser()).build();
    }

    private PageResponse executeDelete(final PageQuery pq) {
        String deletedMovie = pq.getCurrentActionsInput().getDeletedMovie();
        Notification notification = pq.getMoviesDB().delete(deletedMovie);
        if (notification == null) {
            return PageResponse.Builder.createError();
        }
        pq.getUsersDB().notifyUsers(notification);
        PageResponse.Builder builder = new PageResponse.Builder();
        return builder.newUser(pq.getCurrentUser()).build();
    }

    @Override
    public PageResponse executeAction(final PageQuery pq) {
        return switch (pq.getCurrentActionsInput().getFeature()) {
            case "add" -> executeAdd(pq);
            case "delete" -> executeDelete(pq);
            default -> null;
        };
    }
}
