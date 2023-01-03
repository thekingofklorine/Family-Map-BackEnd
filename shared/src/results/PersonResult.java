package results;

import model.Person;

/**
 * parses the results of a person request
 */
public class PersonResult {
    /**
     * string of associated username
     */
    String associatedUsername;
    /**
     * string of person ID
     */
    String personID;
    /**
     * string of person first name
     */
    String firstName;
    /**
     * string of person last name
     */
    String lastName;
    /**
     * string person gender m or f
     */
    String gender;
    /**
     * string of father id
     */
    String fatherID;
    /**
     * string of mother ID
     */
    String motherID;
    /**
     * string of spouse ID
     */
    String spouseID;
    /**
     * bool of whether request suceeded
     */
    Boolean success;
    /**
     * string message if failed
     */
    String message;

    /**
     * json array of people data
     */
    Person [] data;


    /**
     * constructor
     * @param associatedUsername: username for person
     * @param personID: id for person
     * @param firstName: person first name
     * @param lastName: person last name
     * @param gender: person gender
     * @param fatherID: father ID can be null
     * @param motherID: mother id can be null
     * @param spouseID: spouse ID can be null
     * @param success: boolean of whether it worked
     */
    public PersonResult(String associatedUsername, String personID, String firstName, String lastName,
                        String gender, String fatherID, String motherID, String spouseID, Boolean success) {
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
        this.success = success;
    }

    public PersonResult(Boolean success, String message) {
        this.success=success;
        this.message=message;
    }

    /**
     * constructor
     * @param data: json array of person data
     * @param success: bool of whether request succeeded
     */
    public PersonResult(Person[] data, Boolean success) {
        this.data = data;
        this.success = success;
    }

    public Person[] getData() {
        return data;
    }

    public void setData(Person[] data) {
        this.data = data;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
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
