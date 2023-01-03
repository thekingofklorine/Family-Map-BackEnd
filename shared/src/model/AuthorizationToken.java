package model;

/**
 * this creates an auth token for the user
 */
public class AuthorizationToken {
    /**
     * authToken String
     */
    String authToken;
    /**
     * username string
     */
    String username;

    /**
     * @param authToken: Unique authtoken
     * @param username: Username that is associated with the authtoken
     */
    public AuthorizationToken(String authToken, String username) {
        this.authToken = authToken;
        this.username = username;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
