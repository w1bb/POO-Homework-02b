package execution.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import execution.AccountType;
import execution.movies.Movie;

import java.util.ArrayList;

public final class User {
    private static final int BONUS_FREE_MOVIES = 15;
    private static final int PREMIUM_COST = 10;

    private final String name;
    private final String password;
    private AccountType accountType;
    private final String country;
    private int balance;

    private int tokensCount;
    private int numFreePremiumMovies;

    private ArrayList<Movie> purchasedMovies;
    private ArrayList<Movie> watchedMovies;
    private ArrayList<Movie> likedMovies;
    private ArrayList<Movie> ratedMovies;

    public User(final String name, final String password, final AccountType accountType,
                final String country, final int balance) {
        this.name = name;
        this.password = password;
        this.accountType = accountType;
        this.country = country;
        this.balance = balance;

        this.tokensCount = 0;
        this.purchasedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();

        this.numFreePremiumMovies = BONUS_FREE_MOVIES;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(final int balance) {
        this.balance = balance;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getCountry() {
        return this.country;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(final AccountType accountType) {
        this.accountType = accountType;
    }

    /**
     * This method converts the current user to an outputable ObjectNode.
     * @return An ObjectNode containing all the information of a given user.
     */
    public ObjectNode toObjectNode() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode returnNode = objectMapper.createObjectNode();

        ObjectNode credentialsNode = objectMapper.createObjectNode();
        credentialsNode.put("name", name);
        credentialsNode.put("password", password);
        credentialsNode.put("accountType", accountType.toString());
        credentialsNode.put("country", country);
        credentialsNode.put("balance", String.valueOf(balance));
        returnNode.set("credentials", credentialsNode);

        returnNode.put("tokensCount", tokensCount);
        returnNode.put("numFreePremiumMovies", numFreePremiumMovies);

        ArrayNode purchasedNode = objectMapper.createArrayNode();
        for (Movie movie : this.purchasedMovies) {
            purchasedNode.add(movie.toObjectNode());
        }
        ArrayNode watchedNode = objectMapper.createArrayNode();
        for (Movie movie : this.watchedMovies) {
            watchedNode.add(movie.toObjectNode());
        }
        ArrayNode likedNode = objectMapper.createArrayNode();
        for (Movie movie : this.likedMovies) {
            likedNode.add(movie.toObjectNode());
        }
        ArrayNode ratedNode = objectMapper.createArrayNode();
        for (Movie movie : this.ratedMovies) {
            ratedNode.add(movie.toObjectNode());
        }
        returnNode.set("purchasedMovies", purchasedNode);
        returnNode.set("watchedMovies", watchedNode);
        returnNode.set("likedMovies", likedNode);
        returnNode.set("ratedMovies", ratedNode);

        return returnNode;
    }

    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    /**
     * This method facilitates the purchase of tokens based on a user's balance.
     * @param count The number of tokens to be purchased by a user;
     * @return true if and only if the purchase went through.
     */
    public boolean buyTokens(final int count) {
        if (balance < count) {
            return false;
        }
        balance -= count;
        tokensCount += count;
        return true;
    }

    /**
     * This method facilitates the purchase of premium based on a user's balance.
     * @return true if and only if the purchase went through.
     */
    public boolean buyPremium() {
        if (tokensCount < PREMIUM_COST) {
            return false;
        }
        tokensCount -= PREMIUM_COST;
        accountType = AccountType.PREMIUM;
        return true;
    }
}
