package results;

/**
 * this parses in the result of the load
 */
public class LoadResult {
    /**
     * string of what the serverold.java did
     */
    String message;
    /**
     * boolean of if the request was a success
     */
    Boolean success;

    /**
     * @param message number of each thing loaded in
     * @param success whether it succeeded or not
     */
    public LoadResult(String message, Boolean success) {
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
