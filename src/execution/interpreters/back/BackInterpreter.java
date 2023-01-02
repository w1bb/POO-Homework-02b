package execution.interpreters.back;

import com.fasterxml.jackson.databind.ObjectMapper;
import execution.interpreters.GeneralInterpreter;
import execution.interpreters.changepage.ChangePageInterpreter;
import execution.pages.PageFactory;
import execution.pages.PageQuery;
import execution.pages.PageResponse;
import fileio.ActionsInput;

import java.util.ArrayList;

public final class BackInterpreter implements GeneralInterpreter {
    public BackInterpreter() {
    }

    @Override
    public PageResponse executeAction(PageQuery pq) {
        ArrayList<String> visitedPages = pq.getVisitedPages();
        System.out.println("Hello there!");
        if (!pq.getCurrentPage().isForAuth() || visitedPages.size() < 2) {
            System.out.println("Hello there 1!");
            return PageResponse.Builder.createError();
        }
        String pageName = visitedPages.get(visitedPages.size() - 2);
        if (!PageFactory.getPage(pageName).isForAuth()) {
            System.out.println("Hello there 2!");
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
            System.out.println("With rereun!");
            builder.rerunAction(pastAction);
        } else if (pastAction.getFeature() == null) {
            System.out.println("With rereun!");
            builder.rerunAction(pastAction);
        } else {
//            return PageResponse.Builder.createError();
            builder.newPage(PageFactory.getPage("auth-homepage"));
        }
        return builder.newUser(pq.getCurrentUser()).build();
    }
}
