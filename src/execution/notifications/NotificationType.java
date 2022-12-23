package execution.notifications;

public enum NotificationType {
    DATABASE_ADD {
        @Override
        public String toString() {
            return "ADD";
        }
    };

    public String toString() {
        return "null";
    }
}
