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
        ArrayList<ActionsInput> pastActions = pq.getPastActions();
        PageResponse.Builder builder = new PageResponse.Builder();
        builder.rerunAction(pastActions.get(pastActions.size() - 2));
        return builder.newUser(pq.getCurrentUser()).build();
    }
}
