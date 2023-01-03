package services;

import dao.*;
import model.Event;
import model.Person;
import model.User;
import request.LoadRequest;
import results.LoadResult;

/**
 * this processes the load request
 */
public class LoadService {

    private final Database db = new Database();
    /**
     * attempts to load the DB
     * @param r the load request
     * @return the results of the load
     */
    public LoadResult load(LoadRequest r){
        LoadResult result;

        try{
            User[] users = r.getUsers();
            Person[] persons = r.getPersons();
            Event[] events = r.getEvents();

            ClearService clear = new ClearService();
            clear.clear();

            db.openConnection();
            UserDAO userDAO = new UserDAO(db.getConnection());
            PersonDAO personDAO = new PersonDAO(db.getConnection());
            EventDAO eventDAO = new EventDAO(db.getConnection());

            for(User value : users){
                User user = new User(value.getUsername(), value.getPassword(), value.getEmail(), value.getFirstName(),
                        value.getLastName(), value.getGender(), value.getPersonID());
                userDAO.insert(user);
            }
            for(Person value : persons){
                Person person = new Person(value.getPersonID(), value.getAssociatedUsername(), value.getFirstName(),
                        value.getLastName(), value.getGender(), value.getFatherID(), value.getMotherID(), value.getSpouseID());
                personDAO.insert(person);
            }
            for(Event value : events){
                Event event = new Event(value.getEventID(), value.getAssociatedUsername(), value.getPersonID(),
                        value.getLatitude(), value.getLongitude(), value.getCountry(), value.getCity(),
                        value.getEventType(), value.getYear());
                eventDAO.insert(event);
            }
            db.closeConnection(true);
            result = new LoadResult("Successfully added " + users.length + " users, " + persons.length +
                    " persons, and " + events.length + " events", true);
            return result;

        }catch(DataAccessException e){
            e.printStackTrace();
            db.closeConnection(false);
            result= new LoadResult("error: fail to load", false);
            return result;
        }



    }
}
