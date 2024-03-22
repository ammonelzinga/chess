import com.google.gson.Gson;
import model.AuthData;
import model.UserData;
import server.Server;

import java.util.Scanner;

public class LoggedOut {
  ServerFacade serverFacade;
  String url;
  public LoggedOut(String URL){
    serverFacade = new ServerFacade();
    url = URL;
  }

  public void main(String[] args) throws Exception {
    boolean stageLoggedOut = true;
    while (stageLoggedOut) {
      System.out.println("Welcome to 240 chess. Type Help to get started");
      Run();
    }
  }
  public void Run() throws Exception {
    Scanner scanner = new Scanner(System.in);
    String line = scanner.nextLine();
    if(line.equals("Help") || line.equals("help")){
      Help();
    }
    if(line.equals("Login") || line.equals("login")){
      Login();
    }
  }
  public void Help() throws Exception {
      System.out.println("Register -- to create a new account");
      System.out.println("Login -- to play chess");
      System.out.println("Quit -- to exit chess");
      System.out.println("Help -- to view possible commands");
      Run();
  }

  public void Quit(String[] args) throws Exception {

  }

  public void Login() throws Exception {
    String username;
    String password;
    System.out.println("Enter your username: ");
    Scanner scanner = new Scanner(System.in);
    username = scanner.nextLine();
    System.out.println("Enter your password: ");
    password = scanner.nextLine();
    System.out.print("Username entered: ");
    System.out.println(username);
    System.out.print("Password enetered: ");
    System.out.print(password);
    UserData userData = new UserData(username, password, "email");
    String sessionUrl = url + "/session";
    serverFacade.run(sessionUrl, "POST", new Gson().toJson(userData), AuthData.class);
  }


  public static void Register(String[] args) throws Exception {

  }
}
