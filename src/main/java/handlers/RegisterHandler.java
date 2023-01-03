package handlers;
import java.io.*;
import java.net.*;

import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import request.RegisterRequest;
import results.LoginResult;
import results.RegisterResult;
import services.RegisterService;
import utility.ReadWrite;



public class RegisterHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {



      Gson gson = new Gson();
      ReadWrite readString = new ReadWrite();
      ReadWrite writeString = new ReadWrite();

      try {
        if (exchange.getRequestMethod().equalsIgnoreCase("post")) {
          // Get the request body input stream
          InputStream reqBody = exchange.getRequestBody();
          String reqData = readString.readString(reqBody);

          RegisterRequest request = gson.fromJson(reqData, RegisterRequest.class);
          RegisterService service = new RegisterService();
          RegisterResult result = service.register(request);

          if(result.isSuccess()){
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);
          }
          else {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
          }
          OutputStream resBody = exchange.getResponseBody();
          String gsonString =gson.toJson(result);
          writeString.writeString(gsonString, resBody);
          resBody.close();
        }
      }
      catch (IOException e) {
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        LoginResult result = new LoginResult("error: fail to register", false);
        OutputStream resBody = exchange.getResponseBody();
        String gsonString = gson.toJson(result);
        writeString.writeString(gsonString, resBody);
        resBody.close();
        e.printStackTrace();
      }
    }
}
