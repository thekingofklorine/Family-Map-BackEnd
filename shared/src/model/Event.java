package model;

import java.util.Objects;

/**
 * this creates an event for a person
 */
public class Event {
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

    private Event[] events;

    /**
     * @param eventID: Unique identifier for this event
     * @param associatedUsername: Username of user to which this event belongs
     * @param personID: ID of person to which this event belongs
     * @param latitude: Latitude of event’s location
     * @param longitude: Longitude of event’s location
     * @param country: Country in which event occurred
     * @param city: City in which event occurred
     * @param eventType: Type of event
     * @param year: Year in which event occurred
     */
    public Event(String eventID, String associatedUsername, String personID, Float latitude,
                 Float longitude, String country, String city, String eventType, Integer year) {
        this.eventID = eventID;
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    public void setLatitude(Float latitude) {
        this.latitude=latitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude=longitude;
    }

    public void setYear(Integer year) {
        this.year=year;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events=events;
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

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(eventID, event.eventID) && Objects.equals(associatedUsername, event.associatedUsername) && Objects.equals(personID, event.personID) && Objects.equals(latitude, event.latitude) && Objects.equals(longitude, event.longitude) && Objects.equals(country, event.country) && Objects.equals(city, event.city) && Objects.equals(eventType, event.eventType) && Objects.equals(year, event.year);
    }

}
