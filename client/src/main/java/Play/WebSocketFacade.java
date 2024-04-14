package Play;
import chess.ChessGame;
import chess.ChessMove;
import com.google.gson.Gson;
import dataAccess.DataAccessException;
import webSocketMessages.serverMessages.ServerMessage;
import webSocketMessages.userCommands.*;
//import exception.ResponseException;
//import webSocketMessages.Action;
//import webSocketMessages.Notification;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

//need to extend Endpoint for websocket to work properly
public class WebSocketFacade extends Endpoint {

  Session session;
  ServerMessageHandler notificationHandler;


  public WebSocketFacade(String url) throws Exception {
    try {
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

  public void makeMove(String auth, String username, int gameID, ChessMove chessMove, ChessGame.TeamColor playerColor) throws Exception {
    System.out.println("WebFacade about to try joinGameObserver");
    try {
      var action = new MakeMoveCommand(auth, username, gameID, UserGameCommand.CommandType.MAKE_MOVE, playerColor, chessMove);
      this.session.getBasicRemote().sendText(new Gson().toJson(action));
      System.out.println("sent text to web socket server");
    } catch (IOException ex) {
      DataAccessException exception = new DataAccessException(ex.getMessage());
      exception.addStatusCode(500);
      System.out.println(exception.getMessage());
      throw exception;
    }
  }
  public void joinGameObserver(String auth, String username, int gameID) throws DataAccessException {
    System.out.println("WebFacade about to try joinGameObserver");
    try {
      var action = new JoinObserver(auth, username, gameID, UserGameCommand.CommandType.JOIN_OBSERVER, null, null);
      this.session.getBasicRemote().sendText(new Gson().toJson(action));
      System.out.println("sent text to web socket server");
    } catch (IOException ex) {
      DataAccessException exception = new DataAccessException(ex.getMessage());
      exception.addStatusCode(500);
      System.out.println(exception.getMessage());
      throw exception;
    }
  }

  public void joinGamePlayer(String auth, String username, int gameID, ChessGame.TeamColor teamColor) throws DataAccessException {
    System.out.println("WebFacade about to try joinGamePlayer");
    try {
      var action = new JoinPlayerCommand(auth, username, gameID, UserGameCommand.CommandType.JOIN_PLAYER, teamColor, null);
      this.session.getBasicRemote().sendText(new Gson().toJson(action));
      System.out.println("sent text to web socket server");
    } catch (IOException ex) {
      DataAccessException exception = new DataAccessException(ex.getMessage());
      exception.addStatusCode(500);
      System.out.println(exception.getMessage());
      throw exception;
    }
  }

  public void leaveGame(String auth, String username, int gameID) throws Exception {
    try {
      var action = new LeaveCommand(auth, username, gameID, UserGameCommand.CommandType.LEAVE, null, null);
      this.session.getBasicRemote().sendText(new Gson().toJson(action));
      System.out.println("sent text to web socket server");
      this.session.close();
    } catch (IOException ex) {
      DataAccessException exception = new DataAccessException(ex.getMessage());
      exception.addStatusCode(500);
      System.out.println(exception.getMessage());
      throw exception;
    }
  }

  /*public void leavePetShop(String visitorName) throws DataAccessException {
    try {
      var action = new Action(Action.Type.EXIT, visitorName);
      this.session.getBasicRemote().sendText(new Gson().toJson(action));
      this.session.close();
    } catch (IOException ex) {
      throw new ResponseException(500, ex.getMessage());
    }
  }*/

}
