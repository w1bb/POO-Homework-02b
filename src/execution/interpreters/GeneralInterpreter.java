package execution.interpreters;

import execution.pages.PageQuery;
import execution.pages.PageResponse;

public interface GeneralInterpreter {
    /**
     * This method executes a given action.
     * @param pq The action to be executed, alongside other useful information.
     * @return A PageResponse containing the information required by the Interpreter.
     */
    PageResponse executeAction(PageQuery pq);
}
