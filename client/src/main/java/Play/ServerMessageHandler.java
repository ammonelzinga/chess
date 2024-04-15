package Play;
import webSocketMessages.serverMessages.*;
public class ServerMessageHandler {
    void notify(ServerMessage serverMessage){
      System.out.println(serverMessage.getMessage());
    }
  }

