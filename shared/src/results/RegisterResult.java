package results;

/**
 * creates a result for a request to register
 */
public class RegisterResult {
    /**
     * string of authToken for the user
     */
    String authtoken;
    /**
     * string of user username
     */
    String username;
    /**
     * string of user ID
     */
    String personID;
    /**
     * bool of whether the request was a success
     */
    Boolean success;

    /**
     * string of error message
     */
    String message;

    /**
     * @param authtoken: Unique authtoken
     * @param username: Username that is associated with the authtoken
     * @param personID: Unique identifier for this person
     * @param success: whether the service succeeded
     */
    public RegisterResult(String authtoken, String username, String personID, boolean success) {
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        this.success = success;
    }

    public RegisterResult(String message, Boolean success) {
        this.success=success;
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message=message;
    }

    public String getAuthToken() {
        return authtoken;
    }

    public void setAuthToken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
