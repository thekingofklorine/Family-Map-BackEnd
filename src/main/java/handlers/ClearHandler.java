package handlers;
import java.io.*;
import java.net.*;

import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import results.ClearResult;
import services.ClearService;
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
public class ClearHandler implements HttpHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {



    Gson gson = new Gson();
    ReadWrite writeString = new ReadWrite();

    try {
      if (exchange.getRequestMethod().toLowerCase().equals("post")) {
        ClearService service = new ClearService();
        ClearResult result=service.clear();
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
      } catch (IOException e) {
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
      exchange.getResponseBody().close();
      e.printStackTrace();
    }
  }
}



