import request.RegisterRequest;
import results.RegisterResult;
import services.RegisterService;

public class mainTest {
  public static void main(String[] args){
    RegisterRequest request = new RegisterRequest("evan", "123", "e@gmail", "Evan",
            "Bearman", "m");
    RegisterService service = new RegisterService();
    RegisterResult result = service.register(request);
    System.out.println(result.getAuthToken());

//    LoginRequest loginRequest = new LoginRequest("evan", "123");
//    LoginService loginService = new LoginService();
//    LoginResult loginResult = loginService.login(loginRequest);
//    System.out.println(loginResult.getAuthToken());

//    ClearService clear= new ClearService();
//    ClearResult clearResult = clear.clear();
//    System.out.println(clearResult.getMessage());

  }
}
