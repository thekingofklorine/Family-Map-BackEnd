package results;

/**
 * parses results of a clear request
 */
public class ClearResult {
    /**
     * String message describing serverold.java actions
     */
    String message;
    /**
     * boolean of whether the request was a success
     */
    Boolean success;

    /**
     * @param message: tells whether it succeeded
     * @param success: whether the request succeeded
     */
    public ClearResult(String message, Boolean success) {
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
