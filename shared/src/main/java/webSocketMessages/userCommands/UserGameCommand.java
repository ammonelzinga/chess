package webSocketMessages.userCommands;

import chess.ChessGame;
import chess.ChessMove;

import java.util.Objects;

/**
 * Represents a command a user can send the server over a websocket
 *
 * Note: You can add to this class, but you should not alter the existing
 * methods.
 */
public class UserGameCommand {

  public UserGameCommand(String authToken, String username, int gameID, CommandType type, ChessGame.TeamColor playerColor, ChessMove move) {
    this.authToken = authToken;
    this.username = username;
    this.gameID = gameID;
    this.commandType = type;
    this.playerColor = playerColor;
    this.move = move;
  }

  public enum CommandType {
    JOIN_PLAYER,
    JOIN_OBSERVER,
    MAKE_MOVE,
    LEAVE,
    RESIGN
  }

  protected CommandType commandType;

  private final String authToken;
  private final String username;
  private final ChessMove move;
  private final int gameID;
  private final ChessGame.TeamColor playerColor;

  public String getAuthString() {
    return authToken;
  }
  public String getUsername() {
    return username;
  }

  public ChessMove getMove(){
    return move;
  }

  public int getGameID() {
    return gameID;
  }

  public ChessGame.TeamColor getPlayerColor() {return playerColor;}
  public CommandType getCommandType() {
    return this.commandType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof UserGameCommand))
      return false;
    UserGameCommand that = (UserGameCommand) o;
    return getCommandType() == that.getCommandType() && Objects.equals(getAuthString(), that.getAuthString());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCommandType(), getAuthString());
  }
}
