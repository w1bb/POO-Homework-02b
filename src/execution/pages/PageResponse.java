package execution.pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.movies.MoviesDB;
import execution.users.User;
import fileio.ActionsInput;

import javax.swing.*;
import java.util.ArrayList;

public final class PageResponse {
    private final Page newPage;
    private final User newUser;
    private final ObjectNode actionOutput;
    private final MoviesDB moviesDBSubset;
    private final ArrayList<String> visitedPages;
    private final ArrayList<ActionsInput> pastActions;
    private final ActionsInput rerunAction;

    public Page getNewPage() {
        return newPage;
    }

    public User getNewUser() {
        return newUser;
    }

    public ObjectNode getActionOutput() {
        return actionOutput;
    }

    public MoviesDB getMoviesDBSubset() {
        return moviesDBSubset;
    }

    public ArrayList<String> getVisitedPages() {
        return visitedPages;
    }

    public ActionsInput getRerunAction() {
        return rerunAction;
    }

    public static final class Builder {
        private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
        private Page newPage;
        private User newUser;
        private ObjectNode actionOutput;
        private MoviesDB moviesDBSubset;
        private ArrayList<String> visitedPages;
        private ArrayList<ActionsInput> pastActions;
        private ActionsInput rerunAction;

        public Builder() {
            this.newPage = null;
            this.newUser = null;
            this.actionOutput = null;
            this.moviesDBSubset = null;
            this.visitedPages = null;
            this.rerunAction = null;
        }

        /**
         * This method sets the newPage filed for the builder.
         * @param newPageVal The new newPage value.
         * @return The builder.
         */
        public Builder newPage(final Page newPageVal) {
            this.newPage = newPageVal;
            return this;
        }

        /**
         * This method sets the newUser filed for the builder.
         * @param newUserVal The new newUser value.
         * @return The builder.
         */
        public Builder newUser(final User newUserVal) {
            this.newUser = newUserVal;
            return this;
        }

        /**
         * This method sets the actionOutput filed for the builder.
         * @param actionOutputVal The new actionOutput value.
         * @return The builder.
         */
        public Builder actionOutput(final ObjectNode actionOutputVal) {
            this.actionOutput = actionOutputVal;
            return this;
        }

        /**
         * This method sets the moviesDBSubset filed for the builder.
         * @param moviesDBSubsetVal The new moviesDBSubset value.
         * @return The builder.
         */
        public Builder moviesDBSubset(final MoviesDB moviesDBSubsetVal) {
            this.moviesDBSubset = moviesDBSubsetVal;
            return this;
        }

        /**
         * This method sets the visitedPages filed for the builder.
         * @param visitedPagesVal The new visitedPages value.
         * @return The builder.
         */
        public Builder visitedPages(final ArrayList<String> visitedPagesVal) {
            this.visitedPages = visitedPagesVal;
            return this;
        }

        /**
         * This method sets the pastActions filed for the builder.
         * @param pastActionsVal The new pastActions value.
         * @return The builder.
         */
        public Builder pastActions(final ArrayList<ActionsInput> pastActionsVal) {
            this.pastActions = pastActionsVal;
            return this;
        }

        /**
         * This method sets the rerunAction filed for the builder.
         * @param rerunActionVal The new rerunAction value.
         * @return The builder.
         */
        public Builder rerunAction(final ActionsInput rerunActionVal) {
            this.rerunAction = rerunActionVal;
            return this;
        }

        /**
         * This method constructs the corresponding PageResponse.
         * @return The corresponding PageResponse.
         */
        public PageResponse build() {
            return new PageResponse(this);
        }

        /**
         * This method constructs a predefined error PageResponse.
         * @return The error PageResponse.
         */
        public static PageResponse createError() {
            ObjectNode objectNode = OBJECT_MAPPER.createObjectNode();
            objectNode.put("error", "Error");
            return (new Builder()).actionOutput(objectNode).build();
        }
    }

    private PageResponse(final Builder builder) {
        this.newPage = builder.newPage;
        this.newUser = builder.newUser;
        this.actionOutput = builder.actionOutput;
        this.moviesDBSubset = builder.moviesDBSubset;
        this.visitedPages = builder.visitedPages;
        this.pastActions = builder.pastActions;
        this.rerunAction = builder.rerunAction;
    }

    @Override
    public String toString() {
        return "PageResponse{"
                + "newPage=" + newPage
                + ", newUser=" + newUser
                + ", actionOutput=" + actionOutput + '}';
    }
}
