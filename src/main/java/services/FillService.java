package services;

import dao.*;
import results.FillResult;
import utility.FillGeneration;

/**
 * processes a fill request
 */
public class FillService {
    private final Database db = new Database();

    /**
     *attempts to fill the table
     * @param username: username being used
     * @param numGen: number of generations to be created
     * @return the result of  the fill
     */
    public FillResult fill(String username, Integer numGen){
        FillResult result;
        try{

            db.openConnection();
            //todo is this right?
            EventDAO eDao = new EventDAO(db.getConnection());
            PersonDAO pDao = new PersonDAO(db.getConnection());
            UserDAO userDAO = new UserDAO(db.getConnection());
            if(userDAO.find(username) == null){
                db.closeConnection(false);
                result= new FillResult("error: fail to fill", false);
                return result;
            }
            db.closeConnection(true);

            FillGeneration tree;
            tree = new FillGeneration(username, numGen);
            result = tree.fillTree();
            return result;
        }catch (DataAccessException e){
            e.printStackTrace();
            db.closeConnection(false);
            result= new FillResult("error: fail to fill", false);
            return result;
        }

    }


}

