package request;

/**
 * creates a login request
 */
public class LoginRequest {
    /**
     * string of username
     */
    String username;
    /**
     * string of password
     */
    String password;

    /**
     * @param username: Unique username for user
     * @param password: User’s password
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
