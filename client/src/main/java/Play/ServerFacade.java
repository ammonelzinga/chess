package Play;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import model.*;
import org.junit.jupiter.api.BeforeAll;

public class ServerFacade {


    public static void main() throws Exception {
      System.out.print("Play.ServerFacade Main");
    }

    public Object run(String url, String method, boolean hasBody, String body, Class model, boolean hasHeader, String header) throws Exception{
      UserData user = new UserData("username", "password", "email");
      //HttpURLConnection http = sendRequest("http://localhost:8080/session", "POST", new Gson().toJson(user));
      HttpURLConnection http = sendRequest(url, method, hasBody, body, hasHeader, header);
      return receiveResponse(http, model);
    }

    public static HttpURLConnection sendRequest(String url, String method, boolean hasBody, String body, boolean hasHeader, String header) throws URISyntaxException, IOException {
      URI uri = new URI(url);
      HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
      http.setRequestMethod(method);
      writeRequestBody(hasBody, body, http, hasHeader, header);
      http.connect();
      //System.out.printf("= Request =========\n[%s] %s\n\n%s\n\n", method, url, body);
      return http;
    }

    public static void writeRequestBody(boolean hasBody, String body, HttpURLConnection http, boolean hasHeader, String header) throws IOException {
      if (!body.isEmpty()) {
        http.setDoOutput(true);
        if(hasHeader){
          http.addRequestProperty("Authorization", header);
        }
        if(hasBody){
        try (var outputStream = http.getOutputStream()) {
          outputStream.write(body.getBytes());
        }}
      }
    }

    public static Object receiveResponse(HttpURLConnection http, Class model) throws IOException {
      var statusCode = http.getResponseCode();
      var statusMessage = http.getResponseMessage();
      Object responseBody = readResponseBody(http, model);
      //System.out.printf("= Response =========\n[%d] %s\n\n%s\n\n", statusCode, statusMessage, responseBody);
      return responseBody;
    }

    public static Object readResponseBody(HttpURLConnection http, Class model) throws IOException {
      Object responseBody = "";
      try (InputStream respBody = http.getInputStream()) {
        InputStreamReader inputStreamReader = new InputStreamReader(respBody);
        responseBody = new Gson().fromJson(inputStreamReader, model);
      }
      //System.out.print("Response....");
      //System.out.print(responseBody);
      return responseBody;
    }
  }

