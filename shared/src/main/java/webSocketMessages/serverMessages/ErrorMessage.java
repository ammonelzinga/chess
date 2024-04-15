package webSocketMessages.serverMessages;

public class ErrorMessage extends ServerMessage{
  String errorMessage;
  public ErrorMessage(ServerMessageType type, String message, String errorMessage){
    super(type, message);
    this.errorMessage = errorMessage;
  }
}
