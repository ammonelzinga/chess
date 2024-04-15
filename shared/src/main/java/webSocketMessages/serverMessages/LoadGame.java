package webSocketMessages.serverMessages;

public class LoadGame extends ServerMessage {
  String game;
  public LoadGame(ServerMessageType type, String message, String game){
    super(type, message);
    this.game = game;
  }
}
