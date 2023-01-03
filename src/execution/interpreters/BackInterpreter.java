package execution.interpreters;

import execution.pages.PageFactory;
import execution.pages.PageQuery;
import execution.pages.PageResponse;
import fileio.ActionsInput;

import java.util.ArrayList;

public final class BackInterpreter implements GeneralInterpreter {
    public BackInterpreter() {
    }

    @Override
    public PageResponse executeAction(final PageQuery pq) {
        ArrayList<String> visitedPages = pq.getVisitedPages();
        if (!pq.getCurrentPage().isForAuth() || visitedPages.size() < 2) {
            return PageResponse.Builder.createError();
        }
        String pageName = visitedPages.get(visitedPages.size() - 2);
        if (!PageFactory.getPage(pageName).isForAuth()) {
            return PageResponse.Builder.createError();
        }
        visitedPages.remove(visitedPages.size() - 1);
        ArrayList<ActionsInput> pastActions = pq.getPastActions();
        pastActions.remove(pastActions.size() - 1);
        ActionsInput pastAction = pastActions.get(pastActions.size() - 1);
        PageResponse.Builder builder = new PageResponse.Builder();
        if (pastAction != null
                && !pastAction.getType().equals("on page")
                && pastAction.getFeature() != null
                && !pastAction.getFeature().equals("login")
                && !pastAction.getFeature().equals("register")) {
            builder.rerunAction(pastAction);
        } else if (pastAction != null && pastAction.getFeature() == null) {
            builder.rerunAction(pastAction);
        } else {
            builder.newPage(PageFactory.getPage("auth-homepage"));
        }
        return builder.newUser(pq.getCurrentUser()).build();
    }
}
