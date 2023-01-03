package handlers;
import java.io.*;
import java.net.*;

import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import results.FillResult;
import services.FillService;
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
public class FillHandler implements HttpHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {


    Gson gson = new Gson();
    ReadWrite readString = new ReadWrite();
    ReadWrite writeString = new ReadWrite();

    try {
      if (exchange.getRequestMethod().equalsIgnoreCase("post")) {

        FillService service = new FillService();
        String urlString = exchange.getRequestURI().toString();
        String[] urlArray = urlString.split("/");
        String username = urlArray[2];
        int generation = 0;

        if(urlArray.length == 2){
          FillResult result = new FillResult("error: bad url", false);
          OutputStream resBody = exchange.getResponseBody();
          String gsonString = gson.toJson(result);
          writeString.writeString(gsonString, resBody);
          resBody.close();
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }
        else if(urlArray.length == 3){
          generation = 4;
        }else{
          generation = Integer.parseInt(urlArray[3]);
        }

        FillResult result = service.fill(username,generation);

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
      FillResult result = new FillResult("error: bad url", false);
      OutputStream resBody = exchange.getResponseBody();
      String gsonString = gson.toJson(result);
      writeString.writeString(gsonString, resBody);
      resBody.close();
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
      e.printStackTrace();
    }
  }
}
