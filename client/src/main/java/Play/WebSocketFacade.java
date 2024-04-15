package Play;
import chess.ChessGame;
import chess.ChessMove;
import com.google.gson.Gson;
import webSocketMessages.serverMessages.ServerMessage;
import webSocketMessages.userCommands.*;
import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

//need to extend Endpoint for websocket to work properly
public class WebSocketFacade extends Endpoint {

  Session session;
  ServerMessageHandler notificationHandler;
  int gameID;
  DrawChessGame artist;
  LoggedIn loggedIn;


  public WebSocketFacade(String url, int gameeID, DrawChessGame artistt, LoggedIn loggedInn) throws Exception {
    try {
      artist = artistt;
      loggedIn = loggedInn;
      gameID = gameeID;
      url = url.replace("http", "ws");
      URI socketURI = new URI(url + "/connect");
      this.notificationHandler = new ServerMessageHandler();

      WebSocketContainer container = ContainerProvider.getWebSocketContainer();
      this.session = container.connectToServer(this, socketURI);

      //set message handler
      this.session.addMessageHandler(new MessageHandler.Whole<String>() {
        @Override
        public void onMessage(String message) {
          ServerMessage notification = new Gson().fromJson(message, ServerMessage.class);
          if(notification.getServerMessageType() == ServerMessage.ServerMessageType.LOAD_GAME){
            redraw();
          }
          notificationHandler.notify(notification);
        }
      });
    } catch (DeploymentException | IOException | URISyntaxException ex) {
      DataAccessException exception = new DataAccessException(ex.getMessage());
      exception.addStatusCode(500);
      throw exception;
    }
  }

  //Endpoint requires this method, but you don't have to do anything
  @Override
  public void onOpen(Session session, EndpointConfig endpointConfig) {
  }

  public void redraw(){
    try{loggedIn.listGames(false);
    artist.updateGame(loggedIn.gameMap.get(gameID).game());
    artist.main(true);}
    catch(Exception e){
      System.out.print("Couldn't redraw game at this time");
      System.out.print(e.getMessage());
    }
  }

  public void makeMove(String auth, String username, int gameID, ChessMove chessMove, ChessGame.TeamColor playerColor) throws Exception {

    try {
      var action = new MakeMoveCommand(auth, username, gameID, UserGameCommand.CommandType.MAKE_MOVE, playerColor, chessMove);
      this.session.getBasicRemote().sendText(new Gson().toJson(action));
    } catch (IOException ex) {
      DataAccessException exception = new DataAccessException(ex.getMessage());
      exception.addStatusCode(500);
      throw exception;
    }
  }
  public void joinGameObserver(String auth, String username, int gameeID) throws DataAccessException {
    try {
      gameID = gameeID;
      var action = new JoinObserver(auth, username, gameID, UserGameCommand.CommandType.JOIN_OBSERVER, null, null);
      this.session.getBasicRemote().sendText(new Gson().toJson(action));
    } catch (IOException ex) {
      DataAccessException exception = new DataAccessException(ex.getMessage());
      exception.addStatusCode(500);
      throw exception;
    }
  }

  public void joinGamePlayer(String auth, String username, int gameeID, ChessGame.TeamColor teamColor) throws DataAccessException {
    try {
      gameID = gameeID;
      var action = new JoinPlayerCommand(auth, username, gameID, UserGameCommand.CommandType.JOIN_PLAYER, teamColor, null);
      this.session.getBasicRemote().sendText(new Gson().toJson(action));
    } catch (IOException ex) {
      DataAccessException exception = new DataAccessException(ex.getMessage());
      exception.addStatusCode(500);
      throw exception;
    }
  }

  public void resign(String auth, String username, int gameeID, ChessGame.TeamColor playerColor) throws Exception {
    try {
      var action = new ResignCommand(auth, username, gameeID, UserGameCommand.CommandType.RESIGN, playerColor, null);
      this.session.getBasicRemote().sendText(new Gson().toJson(action));
    } catch (IOException ex) {
      DataAccessException exception = new DataAccessException(ex.getMessage());
      exception.addStatusCode(500);
      throw exception;
    }}

  public void leaveGame(String auth, String username, int gameeID, ChessGame.TeamColor playerColor) throws Exception {
    try {
      var action = new LeaveCommand(auth, username, gameeID, UserGameCommand.CommandType.LEAVE, playerColor, null);
      this.session.getBasicRemote().sendText(new Gson().toJson(action));
      gameID = 0;
      this.session.close();
    } catch (IOException ex) {
      DataAccessException exception = new DataAccessException(ex.getMessage());
      exception.addStatusCode(500);
      throw exception;
    }
  }

}
