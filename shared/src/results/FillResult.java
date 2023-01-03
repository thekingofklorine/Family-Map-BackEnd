package results;

/**
 * parses results of a fill request
 */
public class FillResult {
    /**
     * string message of what the serverold.java did
     */
    String message;
    /**
     * boolean if whether request was a success
     */
    Boolean success;

    /**
     * @param message: tells how many people and events were added
     * @param success: whether the request succeeded
     */
    public FillResult(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
