package Play;
import webSocketMessages.serverMessages.*;
public class ServerMessageHandler {
    void notify(ServerMessage serverMessage){
      System.out.println("going to notify this local machine");
      System.out.println(serverMessage.getMessage());
    }
  }

