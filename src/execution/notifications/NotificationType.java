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

    public String toString() {
        return "null";
    }
}
