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
    };

    public String toString() {
        return "null";
    }
}
