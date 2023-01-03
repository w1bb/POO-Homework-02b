package execution.interpreters.subscribe;

import execution.interpreters.GeneralInterpreter;
import execution.pages.PageFactory;
import execution.pages.PageQuery;
import execution.pages.PageResponse;

public final class SubscribeInterpreter implements GeneralInterpreter {
    @Override
    public PageResponse executeAction(final PageQuery pq) {
        if (pq.getCurrentPage() != PageFactory.getPage("see details")) {
            return PageResponse.Builder.createError();
        }
        return pq.getCurrentPage().execute(pq);
    }
}
