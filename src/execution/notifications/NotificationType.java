package execution.notifications;

public enum NotificationType {
    DATABASE_ADD {
        @Override
        public String toString() {
            return "ADD";
        }
    }, DATABASE_REMOVE {
        @Override
        public String toString() {
            return "REMOVE";
        }
    }, RECOMMENDATION {
        @Override
        public String toString() {
            return "Recommendation";
        }
    };

    /**
     * This method converts this datatype into a readable string that will be used in the output.
     * @return The outputable String.
     */
    public String toString() {
        return "null";
    }
}
