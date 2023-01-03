package services;

import dao.AuthorizationTokenDAO;
import dao.DataAccessException;
import dao.Database;
import dao.UserDAO;
import model.AuthorizationToken;
import model.User;
import request.RegisterRequest;
import results.RegisterResult;
import utility.FillGeneration;

import java.util.UUID;

/**
 * this class registers the user and returns key info
 */
public class RegisterService {

    private final Database db = new Database();

    /**
     * runs a register request
     * @param r: Register Request from user
     * @return :the results of the register attempt
     */
    public RegisterResult register(RegisterRequest r){

        RegisterResult result;


        String username = r.getUsername();

        try{
            db.openConnection();
            UserDAO uDao = new UserDAO(db.getConnection());
            AuthorizationTokenDAO aDao = new AuthorizationTokenDAO(db.getConnection());
            if(uDao.find(username) != null){ //for junit code
                db.closeConnection(false);
                result= new RegisterResult("error: fail to register",false);
                return result;
            }
            AuthorizationToken authToken = new AuthorizationToken(UUID.randomUUID().toString(), r.getUsername());
            User user = new User(r.getUsername(), r.getPassword(), r.getEmail(), r.getFirstName(), r.getLastName(),
                    r.getGender(), UUID.randomUUID().toString());


            uDao.insert(user);
            aDao.insert(authToken);

            db.closeConnection(true);

            //Todo fill the generations baby
            FillGeneration tree;
            tree = new FillGeneration(username, 4);
            tree.fillTree();
            //todo is this right?




            result = new RegisterResult(authToken.getAuthToken(), authToken.getUsername(), user.getPersonID(), true);
            return result;

        }catch (DataAccessException e){
            e.printStackTrace();
            db.closeConnection(false);
            result= new RegisterResult("error: fail to register", false);
            return result;
        }
    }
}
