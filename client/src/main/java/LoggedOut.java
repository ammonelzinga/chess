import java.util.Scanner;

public class LoggedOut {

  public static void main(String[] args) throws Exception {
    boolean stageLoggedOut = true;
    while (stageLoggedOut) {
      System.out.println("Welcome to 240 chess. Type Help to get started");
      Run();
    }
  }
  public static void Run() throws Exception {
    Scanner scanner = new Scanner(System.in);
    String line = scanner.nextLine();
    if(line.equals("Help") || line.equals("help")){
      Help();
    }
    if(line.equals("Login") || line.equals("login")){
      Login();
    }
  }
  public static void Help() throws Exception {
      System.out.println("Register -- to create a new account");
      System.out.println("Login -- to play chess");
      System.out.println("Quit -- to exit chess");
      System.out.println("Help -- to view possible commands");
      Run();
  }

  public static void Quit(String[] args) throws Exception {

  }

  public static void Login() throws Exception {
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
  }


  public static void Register(String[] args) throws Exception {

  }
}
