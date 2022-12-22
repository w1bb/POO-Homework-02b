package fileio;

import execution.AccountType;
import execution.users.User;

public final class UsersInput {
    private CredentialsInput credentialsInput;

    public UsersInput() {
    }

    public CredentialsInput getCredentials() {
        return credentialsInput;
    }

    public void setCredentials(final CredentialsInput credentials) {
        this.credentialsInput = credentials;
    }

    /**
     * This method converts a UsersInput to a real User.
     * @return The converted value
     */
    public User toUser() {
        return switch (credentialsInput.getAccountType()) {
            case "standard" ->
                    new User(credentialsInput.getName(), credentialsInput.getPassword(),
                            AccountType.STANDARD,
                            credentialsInput.getCountry(),
                            Integer.parseInt(credentialsInput.getBalance()));
            case "premium" ->
                    new User(credentialsInput.getName(), credentialsInput.getPassword(),
                            AccountType.PREMIUM,
                            credentialsInput.getCountry(),
                            Integer.parseInt(credentialsInput.getBalance()));
            default -> null;
        };
    }
}
