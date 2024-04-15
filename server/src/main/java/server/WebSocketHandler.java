package server;
import chess.ChessGame;
import chess.ChessMove;
import com.google.gson.Gson;
import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import model.GameData;
import org.eclipse.jetty.server.Authentication;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import service.GameService;
import webSocketMessages.serverMessages.ErrorMessage;
import webSocketMessages.serverMessages.LoadGame;
import webSocketMessages.serverMessages.ServerMessage;
import webSocketMessages.userCommands.JoinPlayerCommand;
import webSocketMessages.userCommands.UserGameCommand;
import java.io.IOException;

import static chess.ChessGame.TeamColor.BLACK;
import static chess.ChessGame.TeamColor.WHITE;


@WebSocket
public class WebSocketHandler {
  private GameDAO gameDAO;
  private AuthDAO authDAO;
  private String username;
  private GameService gameService;
  public WebSocketHandler(GameDAO gameD, AuthDAO authD, GameService gameService){
    gameDAO = gameD;
    authDAO = authD;
  }
  private final ConnectionManager connectionManager = new ConnectionManager();

  @OnWebSocketMessage
  public void onMessage(Session session, String message) throws IOException {
    UserGameCommand action = new Gson().fromJson(message, UserGameCommand.class);
    try{verifyAuth(action.getAuthString(), action.getUsername(), action.getGameID(), session);
      System.out.print("got through auth");
      username = authDAO.getAuth(action.getAuthString()).username();
    switch (action.getCommandType()) {
      case MAKE_MOVE:
        makeMove(username, action.getGameID(), session, getColor(action.getGameID()), action.getMove());
        break;
      case JOIN_OBSERVER:
        joinGameObserver(username, action.getGameID(), session);
        System.out.println("server received join observer mesage");
        break;
      case JOIN_PLAYER:
        try{
          verifyAuth(action.getAuthString(), username, action.getGameID(), session);
        joinGamePlayer(username, action.getGameID(), session, action.getPlayerColor());}
        catch(Exception e){
          sendError(username, action.getGameID(), session);
        }
        break;
      case LEAVE:
        leave(username, action.getGameID(), session);
        break;
      case RESIGN:
        resign(username, action.getGameID(), session, getColor(action.getGameID()));
        break;
    }}
    catch(Exception e){
      connectionManager.add(username, action.getGameID(), session);
      sendError(username, action.getGameID(), session);
      }
    }

    private ChessGame.TeamColor getColor(int gameID){
      ChessGame.TeamColor playerColor = null;
      try{
      GameData tempGameData = gameDAO.getGame(gameID);
      if(tempGameData.whiteUsername() != null){
        if(username.equals(tempGameData.whiteUsername())){
          playerColor = WHITE;
        }}
      if(tempGameData.blackUsername() != null){
        if(username.equals(tempGameData.blackUsername())){
          playerColor = BLACK;
        }}}
      catch(Exception e){
        System.out.print(e.getMessage());
      }
      return playerColor;
  }
  private boolean verifyAuth(String auth, String username, int gameID, Session session) throws Exception{
    if(authDAO.checkAuth(auth) != true){
      try{var message = "Error - invalid authentication!";
      var notification = new ErrorMessage(ServerMessage.ServerMessageType.ERROR, message, message);
      connectionManager.broadcastRootUser(username, notification, gameID);
      return false;}
      catch(Exception e){
        System.out.print(e.getMessage());
        return false;}
    }
    else{return true;}
  }
  private void resign(String username, int gameID, Session session,ChessGame.TeamColor playerColor) throws IOException {
    if(playerColor == null){
      sendError(username, gameID, session);
      return;
    }
    try {
      GameData gameData=gameDAO.getGame(gameID);
      if(gameData.game().getGameOverStatus()){
        var message = "Error - sorry that is illegal.";
        var notification = new ErrorMessage(ServerMessage.ServerMessageType.ERROR, message, message);
        connectionManager.broadcastRootUser(username, notification, gameID);
        return;
      }
      String opponentName=gameData.whiteUsername();
      ChessGame.TeamColor color=WHITE;
      if (playerColor == WHITE) {
        color=BLACK;
        opponentName=gameData.blackUsername();
      }
      gameData.game().updateGameOver(true);
        String message=username + " playing as " + playerColor + " has resigned! " + opponentName + " playing as " + color
                + " has won the game!!!";
        notifyGamePoint(username, gameID, session, playerColor, message);
      gameDAO.updateGame(gameID, gameData);
    } catch (Exception e) {
      sendError(username, gameID, session);
    }
  }

    private void notifyGamePoint(String username, int gameID, Session session, ChessGame.TeamColor playerColor, String message) throws IOException {
    var notification = new ServerMessage(ServerMessage.ServerMessageType.NOTIFICATION, message);
    connectionManager.broadcast("majolmajol", notification, gameID);
  }
  private void makeMove(String username, int gameID, Session session,ChessGame.TeamColor playerColor, ChessMove move) throws IOException {
    if(playerColor == null){
      sendError(username, gameID, session);
      return;
    }
    try{
      GameData gameData = gameDAO.getGame(gameID);
      if(playerColor != gameData.game().getTeamTurn()){
        sendError(username, gameID, session);
        return;
      }
      String opponentName = gameData.whiteUsername();
      ChessGame.TeamColor color = WHITE;
      if(playerColor == WHITE){
        color = BLACK;
        opponentName = gameData.blackUsername();
      }
      System.out.println(gameData);
      gameData.game().makeMove(move);
      gameDAO.updateGame(gameID, gameData);
      String message = "game updated";
      var notification = new LoadGame(ServerMessage.ServerMessageType.LOAD_GAME, message, message);
      connectionManager.broadcast("majolmajol", notification, gameID);
      makeMoveNotify(username, gameID, session, move);
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
      sendError(username, gameID, session);
    }


  }
  private void makeMoveNotify(String username, int gameID, Session session, ChessMove move) throws IOException {
    var message = String.format("%s as ", username);
    message += " moved from ";
    message += move.wordsToString();
    var notification = new ServerMessage(ServerMessage.ServerMessageType.NOTIFICATION, message);
    connectionManager.broadcast(username, notification, gameID);
  }
  private void joinGamePlayer(String username, int gameID, Session session, ChessGame.TeamColor playerColor) throws IOException {
    try{
      String color = "";
    connectionManager.add(username, gameID, session);
    if(playerColor == WHITE){
      color = "WHITE";
    }
    if(playerColor == BLACK){
      color = "BLACK";
    }
    if((username.equals(gameDAO.getGame(gameID).whiteUsername()) == false && color == "WHITE") ||
      (username.equals(gameDAO.getGame(gameID).blackUsername()) == false && color == "BLACK")){
    gameService.joinGame(username, color, gameID);}
    var message = String.format("%s is now playing as ", username);
    message +=playerColor.toString();
    loadNotify(username, message, gameID);}
    catch(Exception e){
      sendError(username, gameID, session);
    }
  }

  private void loadNotify(String username, String message, int gameID){
    try{var loadGame = new LoadGame(ServerMessage.ServerMessageType.LOAD_GAME, message, message);
    connectionManager.broadcastRootUser(username, loadGame, gameID);
    var notification = new ServerMessage(ServerMessage.ServerMessageType.NOTIFICATION, message);
    connectionManager.broadcast(username, notification, gameID);}
    catch(Exception e){
      System.out.print(e.getMessage());
    }
  }

  private void sendError(String username, int gameID, Session session){
    try{
      var message = "Error - sorry that is illegal.";
    var notification = new ErrorMessage(ServerMessage.ServerMessageType.ERROR, message, message);
    if(username!= null){
    connectionManager.broadcastRootUser(username, notification, gameID);}
    else{
      connectionManager.broadcastRootUserError(username, notification, gameID, session);}
    }
    catch(Exception e){
      System.out.print(e.getMessage());
    }
  }
  private void joinGameObserver(String username, int gameID, Session session) throws IOException {
    connectionManager.add(username, gameID, session);
    try{GameData gameData = gameDAO.getGame(gameID);
    if(gameData == null) {
      sendError(username, gameID, session);
    }
    else{
    var message = String.format("%s has joined as an observer", username);
    loadNotify(username, message, gameID);}}
    catch(Exception e){
      sendError(username, gameID, session);
    }
  }

  private void leave(String username, int gameID, Session session) throws IOException {
    try{
      connectionManager.remove(username, gameID, session);
      GameData gameData = gameDAO.getGame(gameID);
      if(gameData.whiteUsername() != null){
      if(username.equals(gameData.whiteUsername())){
        GameData newGameData = new GameData(gameData.gameID(), null, gameData.blackUsername(), gameData.gameName(), gameData.game());
        gameDAO.updateGame(gameID, newGameData);}
      }
      if(gameData.blackUsername() != null){
        if(username.equals(gameData.blackUsername())){
        GameData newGameData = new GameData(gameData.gameID(), gameData.whiteUsername(), null, gameData.gameName(), gameData.game());
        gameDAO.updateGame(gameID, newGameData);}
      }
    var message = String.format("%s left the game", username);
    var notification = new ServerMessage(ServerMessage.ServerMessageType.NOTIFICATION, message);
    connectionManager.broadcast(username, notification, gameID);}
    catch(Exception e){
      connectionManager.remove(username, gameID, session);
      var message = String.format("%s left the game", username);
      var notification = new ServerMessage(ServerMessage.ServerMessageType.NOTIFICATION, message);
      connectionManager.broadcast(username, notification, gameID);}
  }

}
