package handlers;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.file.Files;

/* todo get / http/1.1
* todo parses index and finds references for favicon.ico, and css/mains.css file
* todo makes request to retrieve those two files
*
* ignore everything but get requests
*/
public class FileHandler implements HttpHandler{

    @Override
    public void handle(HttpExchange exchange) throws IOException {

      try {
        if (exchange.getRequestMethod().equalsIgnoreCase("get")) {
          //get the url
          String urlPath = exchange.getRequestURI().toString();
          // if url path is null or "/" then set the path to "/index.html"
          if(urlPath == null || urlPath.equals("/")) {
            urlPath = "/index.html";
          }

          String filePath = "web" + urlPath;
          File file = new File(filePath);
          if(file.exists()){
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);
            OutputStream resBody = exchange.getResponseBody();
            Files.copy(file.toPath(), resBody);
            resBody.close();
          }
          else {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
            urlPath = "web/HTML/404.html";

            OutputStream resBody = exchange.getResponseBody();
            Files.copy(new File(urlPath).toPath(), resBody);
            resBody.close();
          }



        }
      }
      catch (IOException e) {
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
        e.printStackTrace();
      }
    }








}
