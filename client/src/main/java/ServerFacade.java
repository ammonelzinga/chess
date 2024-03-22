import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import model.*;
import server.Server;

public class ServerFacade {
    public static void main() throws Exception {
      System.out.print("ServerFacade Main");
    }

    public void run(String url, String method, String body, Class model) throws Exception{
      Server server = new Server();
      int port = 8080;
      //System.out.println("♕ 240 Chess Server: " + piece);
      server.run(port);
      UserData user = new UserData("username", "password", "email");
      //HttpURLConnection http = sendRequest("http://localhost:8080/session", "POST", new Gson().toJson(user));
      HttpURLConnection http = sendRequest(url, method, body);
      receiveResponse(http, model);
    }

    public static HttpURLConnection sendRequest(String url, String method, String body) throws URISyntaxException, IOException {
      URI uri = new URI(url);
      HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
      http.setRequestMethod(method);
      writeRequestBody(body, http);
      http.connect();
      System.out.printf("= Request =========\n[%s] %s\n\n%s\n\n", method, url, body);
      return http;
    }

    public static void writeRequestBody(String body, HttpURLConnection http) throws IOException {
      if (!body.isEmpty()) {
        http.setDoOutput(true);
        try (var outputStream = http.getOutputStream()) {
          outputStream.write(body.getBytes());
        }
      }
    }

    public static void receiveResponse(HttpURLConnection http, Class model) throws IOException {
      var statusCode = http.getResponseCode();
      var statusMessage = http.getResponseMessage();
      Object responseBody = readResponseBody(http, model);
      System.out.printf("= Response =========\n[%d] %s\n\n%s\n\n", statusCode, statusMessage, responseBody);
    }

    public static Object readResponseBody(HttpURLConnection http, Class model) throws IOException {
      Object responseBody = "";
      try (InputStream respBody = http.getInputStream()) {
        InputStreamReader inputStreamReader = new InputStreamReader(respBody);
        responseBody = new Gson().fromJson(inputStreamReader, model);
      }
      System.out.print("Response....");
      System.out.print(responseBody);
      return responseBody;
    }
  }

