package request;

import model.Event;
import model.Person;
import model.User;

/**
 * request to load in a set of data into the database
 */
public class LoadRequest {
    /**
     * json array of users
     */
    User [] users;
    /**
     * json array of persons
     */
    Person[] persons;
    /**
     * json array of events
     */
    Event [] events;

    /**
     * @param users json array of User objects
     * @param persons json array of Person objects
     * @param events json array of Event objects
     */
    public LoadRequest(User[] users, Person[] persons, Event[] events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Person[] getPersons() {
        return persons;
    }

    public void setPersons(Person[] persons) {
        this.persons = persons;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }
}
