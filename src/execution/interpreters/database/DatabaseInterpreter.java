package execution.interpreters.database;

import execution.interpreters.GeneralInterpreter;
import execution.movies.Movie;
import execution.notifications.Notification;
import execution.pages.PageQuery;
import execution.pages.PageResponse;

public final class DatabaseInterpreter implements GeneralInterpreter {

    private PageResponse executeAdd(PageQuery pq) {
        Movie newMovie = pq.getCurrentActionsInput().getAddedMovie().toMovie();
        Notification notification = pq.getMoviesDB().add(newMovie);
        if (notification == null) {
            return PageResponse.Builder.createError();
        }
        pq.getUsersDB().notifyUsers(notification);
        PageResponse.Builder builder = new PageResponse.Builder();
        return builder.newUser(pq.getCurrentUser()).build();
    }

    @Override
    public PageResponse executeAction(PageQuery pq) {
        if (pq.getCurrentActionsInput().getFeature().equals("add")) {
            return executeAdd(pq);
        }
        System.out.println("!!!!!!!!!!!!!!!!" + pq.getCurrentActionsInput().getFeature());
        return null;
    }
}
