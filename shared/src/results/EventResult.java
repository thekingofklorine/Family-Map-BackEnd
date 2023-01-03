package results;

import model.Event;

/**
 * parses event request
 */
public class EventResult {
    /**
     * event ID string
     */
    String eventID;
    /**
     * string username associated with event
     */
    String associatedUsername;
    /**
     * string person id
     */
    String personID;
    /**
     * float of the latitude
     */
    Float latitude;
    /**
     * float of longitude
     */
    Float longitude;
    /**
     * string of country
     */
    String country;
    /**
     * string of city
     */
    String city;
    /**
     * string of event type
     */
    String eventType;
    /**
     * int of what year
     */
    Integer year;
    /**
     * bool for action success
     */
    Boolean success;

    String message;

    /**
     * json array of event data
     */
    Event [] data;



    /**
     * constructor
     * @param eventID: Unique identifier for this event
     * @param associatedUsername: Username of user to which this event belongs
     * @param personID: ID of person to which this event belongs
     * @param latitude: Latitude of event’s location
     * @param longitude: Longitude of event’s location
     * @param country: Country in which event occurred
     * @param city: City in which event occurred
     * @param eventType: Type of event
     * @param year: Year in which event occurred
     * @param success: bool of action success
     */
    public EventResult(String eventID, String associatedUsername, String personID, Float latitude,
                       Float longitude, String country, String city, String eventType, Integer year,
                       Boolean success) {
        this.eventID = eventID;
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
        this.success = success;
    }

    public EventResult(Boolean success, Event[] data) {
        this.success=success;
        this.data=data;
    }

    public EventResult(Boolean success, String message) {
        this.success=success;
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message=message;
    }

    public Event[] getData() {
        return data;
    }

    public void setData(Event[] data) {
        this.data=data;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
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

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
