package execution.pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.movies.MoviesDB;
import execution.users.User;

public final class PageResponse {
    private final Page newPage;
    private final User newUser;
    private final ObjectNode actionOutput;
    private final MoviesDB moviesDBSubset;

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

    public static final class Builder {
        private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
        private Page newPage;
        private User newUser;
        private ObjectNode actionOutput;
        private MoviesDB moviesDBSubset;

        public Builder() {
            this.newPage = null;
            this.newUser = null;
            this.actionOutput = null;
            this.moviesDBSubset = null;
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
    }

    @Override
    public String toString() {
        return "PageResponse{"
                + "newPage=" + newPage
                + ", newUser=" + newUser
                + ", actionOutput=" + actionOutput + '}';
    }
}
