package handlers;
import java.io.*;
import java.net.*;

import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import request.LoadRequest;
import results.LoadResult;
import results.LoginResult;
import services.LoadService;
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

//Todo: this is just copied over from Login, must switch over
public class LoadHandler implements HttpHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {


    Gson gson = new Gson();
    ReadWrite readString = new ReadWrite();
    ReadWrite writeString = new ReadWrite();

    try {
      if (exchange.getRequestMethod().equalsIgnoreCase("post")) {
        // Get the request body input stream
        InputStream reqBody = exchange.getRequestBody();
        // Read JSON string from the input stream
        String reqData = readString.readString(reqBody);

        LoadRequest request = gson.fromJson(reqData, LoadRequest.class);
        LoadService service = new LoadService();
        LoadResult result = service.load(request);

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
      // Some kind of internal error has occurred inside the serverold.java (not the
      // client's fault), so we return an "internal serverold.java error" status code
      // to the client.
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
      LoginResult result = new LoginResult("fail to load", false);
      OutputStream resBody = exchange.getResponseBody();
      String gsonString = gson.toJson(result);
      writeString.writeString(gsonString, resBody);
      resBody.close();
      e.printStackTrace();
    }
  }
}

