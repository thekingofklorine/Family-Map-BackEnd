package services;

import dao.*;
import model.AuthorizationToken;
import model.Person;
import results.PersonResult;

/**
 * processes a person/persons request
 */
public class PersonService {

    private final Database db = new Database();

    /**
     * attempts to find person
     * @param personID: person id to look for
     * @return the persons data
     */
    public PersonResult person(String personID, String authtoken){ //returns the specific person details
        PersonResult result;
        try{
            db.openConnection();
            AuthorizationTokenDAO authorizationTokenDao = new AuthorizationTokenDAO(db.getConnection());
            PersonDAO pDao = new PersonDAO(db.getConnection());
            Person person = pDao.find(personID);
            AuthorizationToken authorizationToken =authorizationTokenDao.findWithToken(authtoken);
            String username = authorizationToken.getUsername();
            String associatedUsername = person.getAssociatedUsername();

            if(associatedUsername == null){ //for my test code
                db.closeConnection(false);
                result= new PersonResult(false,"error: person doesn't exist");
                return result;
            }

            if(!associatedUsername.equals(username)){  //if the person tries to get a person id that doesn't belong ot them
                db.closeConnection(false);
                result= new PersonResult(false,"error: person doesn't belong to you");
                return result;
            }

            db.closeConnection(true);

            result = new PersonResult(person.getAssociatedUsername(), person.getPersonID(), person.getFirstName(),
                    person.getLastName(), person.getGender(), person.getFatherID(), person.getMotherID(),
                    person.getSpouseID(), true);
            return result;

        }catch (DataAccessException e){
            e.printStackTrace();
            db.closeConnection(false);
            result= new PersonResult(false,"error: fail to login");
            return result;
        }
    }
    public PersonResult personArray(String authToken){       //returns all people for user
        PersonResult result;
        try{
            db.openConnection();
            AuthorizationTokenDAO aDao = new AuthorizationTokenDAO(db.getConnection());
            PersonDAO pDao = new PersonDAO(db.getConnection());
            AuthorizationToken usernameToken = aDao.findWithToken(authToken);
            if(usernameToken == null){
                db.closeConnection(false);
                result= new PersonResult(false,"error: fail to find people");
                return result;
            }
            String username = usernameToken.getUsername();
            Person[] person = pDao.findAll(username);
            result = new PersonResult(person, true);
            db.closeConnection(true);
            return result;

        }catch (DataAccessException e){
            e.printStackTrace();
            db.closeConnection(false);
            result= new PersonResult(false,"error: fail to find people");
            return result;
        }
    }
}
