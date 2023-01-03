package results;

/**
 * parses in login results
 */
public class LoginResult {
    /**
     * string auth token for the user
     */
    String authtoken;
    /**
     * string username of the user
     */
    String username;
    /**
     * string id of the user
     */
    String personID;
    /**
     * boolean of whether the login was a success
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
     * @param success: whether login was a success
     */
    public LoginResult(String authtoken, String username, String personID, Boolean success) {
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        this.success = success;
    }

    public LoginResult(String message, Boolean success){
        this.success = success;
        this.message = message;
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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {return message;}

    public void setMessage(String message) { this.message=message;}
}
