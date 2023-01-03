package results;

import model.Event;

/**
* parses events request
*/
public class EventsResult {
    /**
    * json array of event data
    */
    Event [] data;
    /**
     * bool of action success
     */
    Boolean success;
    String message;



    /**
     * string username associated with event
     */
    String associatedUsername;
    String eventID;

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
     * constructor
     * @param data: json array of event data
     * @param success: bool of success
     */
    public EventsResult(Event[] data, Boolean success) {
        this.data = data;
        this.success = success;
    }

    public EventsResult(String associatedUsername, String eventID, String personID, Float latitude, Float longitude, String country, String city, String eventType, Integer year) {
        this.associatedUsername=associatedUsername;
        this.eventID=eventID;
        this.personID=personID;
        this.latitude=latitude;
        this.longitude=longitude;
        this.country=country;
        this.city=city;
        this.eventType=eventType;
        this.year=year;
    }

    public EventsResult(Boolean success, String message) {
        this.success=success;
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message=message;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername=associatedUsername;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID=eventID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID=personID;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude=latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude=longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country=country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city=city;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType=eventType;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year=year;
    }

    public Event[] getData() {
        return data;
    }

    public void setData(Event[] data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
