package services;

import dao.AuthorizationTokenDAO;
import dao.DataAccessException;
import dao.Database;
import dao.UserDAO;
import model.AuthorizationToken;
import model.User;
import request.LoginRequest;
import results.LoginResult;

import java.util.UUID;


/**
 * tries to log in the user
 */
public class LoginService {

    private final Database db = new Database();


    /**
     *attmepts to login the user
     * @param r: login request
     * @return the result of the login
     */
    public LoginResult login(LoginRequest r){
        String password=r.getPassword();
        String username=r.getUsername();
        LoginResult result;
        try{
            db.openConnection();
            UserDAO uDao = new UserDAO(db.getConnection());
            AuthorizationTokenDAO aDao = new AuthorizationTokenDAO(db.getConnection());
            User user = uDao.find(username);
            if(user == null){
                db.closeConnection(false);
                return new LoginResult("error: fail to login, user does not exist", false);
            }
            if(!user.getPassword().equals(password)){
                db.closeConnection(false);
                return new LoginResult("error: fail to login, bad password", false);
            }
            AuthorizationToken authToken = new AuthorizationToken(UUID.randomUUID().toString(), username);
            aDao.insert(authToken);
            db.closeConnection(true);

            result = new LoginResult(authToken.getAuthToken(), username, user.getPersonID(),true);
            return result;

        }catch (DataAccessException e){
            e.printStackTrace();
            db.closeConnection(false);
            result= new LoginResult("error: fail to login", false);
            return result;
        }
    }
}





