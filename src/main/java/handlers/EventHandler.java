package handlers;
import java.io.*;
import java.net.*;

import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import dao.AuthorizationTokenDAO;
import dao.DataAccessException;
import dao.Database;
import results.EventResult;
import services.EventService;
import utility.ReadWrite;

/*
	The ListGamesHandler is the HTTP handler that processes
	incoming HTTP requests that contain the "/games/list" URL path.

	Notice that ListGamesHandler implements the HttpHandler interface,
	which is define by Java.  This interface contains only one method
	named "handle".  When the HttpServer object (declared in the Server class)
	receives a request containing the "/games/list" URL path, it calls
	ListGamesHandler.handle() which actually processes the request.
*/

public class EventHandler implements HttpHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {


    Gson gson = new Gson();
    ReadWrite readString = new ReadWrite();
    ReadWrite writeString = new ReadWrite();

    try {

      if (exchange.getRequestMethod().toLowerCase().equals("get")) {
        EventResult result;
        Headers reqHeaders = exchange.getRequestHeaders();
        if (reqHeaders.containsKey("Authorization")) {
          String authToken=reqHeaders.getFirst("Authorization");
          Database db = new Database();

          db.openConnection();
          AuthorizationTokenDAO aDao = new AuthorizationTokenDAO(db.getConnection());
          if(aDao.findWithToken(authToken) == null){
            db.closeConnection(false);
            result = new EventResult(false, "error: bad auth Token" );
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            OutputStream resBody = exchange.getResponseBody();
            String gsonString =gson.toJson(result);
            writeString.writeString(gsonString, resBody);
            resBody.close();
            return;
          }
          db.closeConnection(true);


          EventService service = new EventService();
          String urlString = exchange.getRequestURI().toString();
          String[] urlArray = urlString.split("/");
          if(urlArray.length == 2){
            result = service.eventArray(authToken);
          }else{
            result = service.event(urlArray[2], authToken);
          }

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
    }catch (IOException e) {
      // Some kind of internal error has occurred inside the server(not the
      // client's fault), so we return an "internal servererror" status code
      // to the client.
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
      // Since the server is unable to complete the request, the client will
      // not receive the list of games, so we close the response body output stream,
      // indicating that the response is complete.
      exchange.getResponseBody().close();

      // Display/log the stack trace
      e.printStackTrace();
    } catch (DataAccessException e) {
      throw new RuntimeException(e);
    }
  }
}