package handlers;
import java.io.*;
import java.net.*;

import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import request.LoginRequest;
import results.LoginResult;
import services.LoginService;
import utility.ReadWrite;


/*
      The ClaimRouteHandler is the HTTP handler that processes
      incoming HTTP requests that contain the "/routes/claim" URL path.

      Notice that ClaimRouteHandler implements the HttpHandler interface,
      which is define by Java.  This interface contains only one method
      named "handle".  When the HttpServer object (declared in the Server class)
      receives a request containing the "/routes/claim" URL path, it calls
      ClaimRouteHandler.handle() which actually processes the request.
  */
public class LoginHandler implements HttpHandler {


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

        LoginRequest request = gson.fromJson(reqData, LoginRequest.class);
        LoginService service = new LoginService();
        LoginResult result = service.login(request);



        if(result.getSuccess()){
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
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
      LoginResult result = new LoginResult("error: fail to login", false);
      OutputStream resBody = exchange.getResponseBody();
      String gsonString = gson.toJson(result);
      writeString.writeString(gsonString, resBody);
      resBody.close();
      e.printStackTrace();
    }
  }
}

