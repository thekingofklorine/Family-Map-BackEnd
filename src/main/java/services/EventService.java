package services;

import dao.*;
import model.AuthorizationToken;
import model.Event;
import results.EventResult;

/**
 * processes a person/persons request
 */
public class EventService {

    private final Database db = new Database();

    /**
     * attempts to find person
     * @param eventID: person id to look for
     * @return the persons data
     */
    public EventResult event(String eventID, String authtoken){ //returns the specific event details
        EventResult result;
        try{
            db.openConnection();
            AuthorizationTokenDAO authorizationTokenDao = new AuthorizationTokenDAO(db.getConnection());
            EventDAO pDao = new EventDAO(db.getConnection());
            Event event = pDao.find(eventID);
            if(event == null){ //for my test code
                db.closeConnection(false);
                result= new EventResult(false,"error: person doesn't exist");
                return result;
            }
            AuthorizationToken authorizationToken =authorizationTokenDao.findWithToken(authtoken);
            String username = authorizationToken.getUsername();
            String associatedUsername = event.getAssociatedUsername();

            if(!associatedUsername.equals(username)){  //if the person tries to get a person id that doesn't belong ot them
                db.closeConnection(false);
                result= new EventResult(false,"error: person doesn't belong to you");
                return result;
            }


            db.closeConnection(true);

            result = new EventResult(
                    event.getEventID(),
                    event.getAssociatedUsername(),
                    event.getPersonID(),
                    event.getLatitude(),
                    event.getLongitude(),
                    event.getCountry(),
                    event.getCity(),
                    event.getEventType(),
                    event.getYear(),
                    true);
            return result;

        }catch (DataAccessException e){
            e.printStackTrace();
            db.closeConnection(false);
            result= new EventResult(false,"error: fail to get event");
            return result;
        }
    }
    public EventResult eventArray(String authToken){       //returns all people for user
        EventResult result;
        try{
            db.openConnection();
            AuthorizationTokenDAO aDao = new AuthorizationTokenDAO(db.getConnection());
            EventDAO eDao = new EventDAO(db.getConnection());
            AuthorizationToken usernameToken = aDao.findWithToken(authToken);
            if(usernameToken == null){
                db.closeConnection(false);
                result= new EventResult(false,"error: fail to find people");
                return result;
            }
            String username = usernameToken.getUsername();
            Event[] event = eDao.findAll(username);
            result = new EventResult(true, event);
            db.closeConnection(true);
            return result;

        }catch (DataAccessException e){
            e.printStackTrace();
            db.closeConnection(false);
            result= new EventResult(false,"error: fail to find event");
            return result;
        }
    }
}
