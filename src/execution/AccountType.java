package execution;

public enum AccountType {
    STANDARD {
        @Override
        public String toString() {
            return "standard";
        }
    },
    PREMIUM {
        @Override
        public String toString() {
            return "premium";
        }
    };

    /**
     * This method converts an AccountType to a String representing its meaning.
     * @return The converted value.
     */
    public String toString() {
        return "null AccountType";
    }
}
