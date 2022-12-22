package execution.interpreters.onpage;

import execution.interpreters.GeneralInterpreter;
import execution.pages.PageQuery;
import execution.pages.PageResponse;

public final class OnPageInterpreter implements GeneralInterpreter {
    /**
     * This method executes a given action.
     * @param pq The action to be executed, alongside other useful information.
     * @return A PageResponse containing the information required by the Interpreter.
     */
    @Override
    public PageResponse executeAction(final PageQuery pq) {
        return pq.getCurrentPage().execute(pq);
    }
}
