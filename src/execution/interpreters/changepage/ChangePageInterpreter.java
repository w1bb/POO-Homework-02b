package execution.interpreters.changepage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.interpreters.GeneralInterpreter;
import execution.pages.PageFactory;
import execution.pages.PageQuery;
import execution.pages.PageResponse;

public final class ChangePageInterpreter implements GeneralInterpreter {
    private final ObjectMapper objectMapper;

    public ChangePageInterpreter() {
        objectMapper = new ObjectMapper();
    }

    /**
     * This method executes a given action.
     * @param pq The action to be executed, alongside other useful information.
     * @return A PageResponse containing the information required by the Interpreter.
     */
    @Override
    public PageResponse executeAction(final PageQuery pq) {
        PageResponse.Builder builder = new PageResponse.Builder();
        builder.newUser(pq.getCurrentUser()).moviesDBSubset(pq.getMoviesDBSubset());
        if (pq.getCurrentPage().canVisit(pq.getCurrentActionsInput().getPage())) {
            builder.newPage(PageFactory.getPage(pq.getCurrentActionsInput().getPage()))
                    .actionOutput(null);
        } else {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("error", "Error");

            builder.newPage(pq.getCurrentPage()).actionOutput(objectNode);
        }
        return builder.build();
    }


}
