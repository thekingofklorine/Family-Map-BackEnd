package services;

import dao.*;
import results.ClearResult;

/**
 * processes a clear request
 */
public class ClearService {
        private final Database db = new Database();
    /**
     * attempts to clear the user
     * @return the result of the clear
     */
    public ClearResult clear(){
        ClearResult result;
        try{
            db.openConnection();
            UserDAO uDao = new UserDAO(db.getConnection());
            AuthorizationTokenDAO aDao = new AuthorizationTokenDAO(db.getConnection());
            EventDAO eDao = new EventDAO(db.getConnection());
            PersonDAO pDao = new PersonDAO(db.getConnection());
            uDao.clear();
            aDao.clear();
            eDao.clear();
            pDao.clear();
            db.closeConnection(true);
            result = new ClearResult("clear succeeded", true);
            return result;
        }
        catch (DataAccessException e){
            e.printStackTrace();
            db.closeConnection(false);
            result= new ClearResult("error: fail to login", false);
            return result;
        }
    }

}