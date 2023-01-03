package results;

import model.Person;

/**
 * parses a persons request
 */
public class PersonsResult {
    /**
     * json array of people data
     */
    Person [] data;
    /**
     * bool of succes
     */
    Boolean success;
    /**
     * strong message of failure
     */
    String message;

    /**
     * constructor
     * @param data: json array of person data
     * @param success: bool of whether request succeeded
     *
     */
    public PersonsResult(Person[] data, Boolean success) {
        this.data = data;
        this.success = success;
        this.message = message;
    }

    public PersonsResult(Boolean success, String message) {
        this.success=success;
        this.message=message;
    }

    public Person[] getData() {
        return data;
    }

    public void setData(Person[] data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
