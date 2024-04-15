package server;
import chess.ChessGame;
import chess.ChessMove;
import com.google.gson.Gson;
//import dataaccess.DataAccess;
//import exception.ResponseException;
import dataAccess.GameDAO;
import model.GameData;
import org.eclipse.jetty.server.Authentication;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import webSocketMessages.serverMessages.ServerMessage;
import webSocketMessages.userCommands.JoinPlayerCommand;
import webSocketMessages.userCommands.UserGameCommand;
//import webSocketMessages.Action;
//import webSocketMessages.Notification;

import java.io.IOException;
import java.util.Timer;


@WebSocket
public class WebSocketHandler {
  private GameDAO gameDAO;
  public WebSocketHandler(GameDAO gameD){
    gameDAO = gameD;
  }
  private final ConnectionManager connectionManager = new ConnectionManager();

  @OnWebSocketMessage
  public void onMessage(Session session, String message) throws IOException {
    UserGameCommand action = new Gson().fromJson(message, UserGameCommand.class);
    switch (action.getCommandType()) {
      case MAKE_MOVE:
        makeMove(action.getUsername(), action.getGameID(), session, action.getPlayerColor(), action.getMove());
        break;
      case JOIN_OBSERVER:
        joinGameObserver(action.getUsername(), action.getGameID(), session);
        System.out.println("server received join observer mesage");
        break;
      case JOIN_PLAYER:
        joinGamePlayer(action.getUsername(), action.getGameID(), session, action.getPlayerColor());
        break;
      case LEAVE:
        leave(action.getUsername(), action.getGameID(), session);
        break;
      case RESIGN:
        resign(action.getUsername(), action.getGameID(), session, action.getPlayerColor());
        break;
    }
  }

  private void resign(String username, int gameID, Session session,ChessGame.TeamColor playerColor) throws IOException {
    try {
      GameData gameData=gameDAO.getGame(gameID);
      String opponentName=gameData.whiteUsername();
      ChessGame.TeamColor color=ChessGame.TeamColor.WHITE;
      if (playerColor == ChessGame.TeamColor.WHITE) {
        color=ChessGame.TeamColor.BLACK;
        opponentName=gameData.blackUsername();
      }
      gameData.game().updateGameOver(true);
        String message=username + " playing as " + playerColor + " has resigned! " + opponentName + " playing as " + color
                + " has won the game!!!";
        notifyGamePoint(username, gameID, session, playerColor, message);
      gameDAO.updateGame(gameID, gameData);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

    private void notifyGamePoint(String username, int gameID, Session session, ChessGame.TeamColor playerColor, String message) throws IOException {
    var notification = new ServerMessage(ServerMessage.ServerMessageType.NOTIFICATION, message);
    connectionManager.broadcast("majolmajol", notification, gameID);
  }
  private void makeMove(String username, int gameID, Session session,ChessGame.TeamColor playerColor, ChessMove move) throws IOException {
    try{
      GameData gameData = gameDAO.getGame(gameID);
      String opponentName = gameData.whiteUsername();
      ChessGame.TeamColor color = ChessGame.TeamColor.WHITE;
      if(playerColor == ChessGame.TeamColor.WHITE){
        color = ChessGame.TeamColor.BLACK;
        opponentName = gameData.blackUsername();
      }
      System.out.println(gameData);
      gameData.game().makeMove(move);
      gameDAO.updateGame(gameID, gameData);
      String message = "game updated";
      var notification = new ServerMessage(ServerMessage.ServerMessageType.LOAD_GAME, message);
      connectionManager.broadcast("majolmajol", notification, gameID);
      makeMoveNotify(username, gameID, session, playerColor, move);
      if(gameData.game().isInCheckmate(color)){
        String messageTwo = opponentName + " playing as " + color + " is in checkmate! " + username + " playing as " + playerColor
                + " has won the game!!!";
        notifyGamePoint(username, gameID, session, playerColor, messageTwo);
      } else if (gameData.game().isInCheck(color)) {
        String messageTwo = opponentName + " playing as " + color + " is in check! Good move " + username + " playing as " + playerColor;
        notifyGamePoint(username, gameID, session, playerColor, messageTwo);
      }
      else if (gameData.game().isInStalemate(color)) {
        String messageTwo = "Wow it's a stalemate! Looks like " + username + " and " + opponentName + " are evenly matched!";
        notifyGamePoint(username, gameID, session, playerColor, messageTwo);
      }
    }
    catch(Exception e){
      System.out.println(e.getMessage());
      var message = "Error - sorry that is illegal.";
      var notification = new ServerMessage(ServerMessage.ServerMessageType.ERROR, message);
      connectionManager.broadcastRootUser(username, notification, gameID);
    }


  }
  private void makeMoveNotify(String username, int gameID, Session session, ChessGame.TeamColor playerColor, ChessMove move) throws IOException {
    var message = String.format("%s as ", username);
    message +=playerColor.toString();
    message += " moved from ";
    message += move.wordsToString();
    var notification = new ServerMessage(ServerMessage.ServerMessageType.NOTIFICATION, message);
    connectionManager.broadcast(username, notification, gameID);
  }
  private void joinGamePlayer(String username, int gameID, Session session, ChessGame.TeamColor playerColor) throws IOException {
    connectionManager.add(username, gameID, session);
    var message = String.format("%s is now playing as ", username);
    message +=playerColor.toString();
    var notification = new ServerMessage(ServerMessage.ServerMessageType.NOTIFICATION, message);
    connectionManager.broadcast(username, notification, gameID);
  }
  private void joinGameObserver(String username, int gameID, Session session) throws IOException {
    connectionManager.add(username, gameID, session);
    var message = String.format("%s has joined as an observer", username);
    var notification = new ServerMessage(ServerMessage.ServerMessageType.NOTIFICATION, message);
    connectionManager.broadcast(username, notification, gameID);
  }

  private void leave(String username, int gameID, Session session) throws IOException {
    connectionManager.remove(username, gameID, session);
    var message = String.format("%s left the game", username);
    var notification = new ServerMessage(ServerMessage.ServerMessageType.NOTIFICATION, message);
    connectionManager.broadcast(username, notification, gameID);
  }

}
