package webSocketMessages.userCommands;

import chess.ChessGame;
import chess.ChessMove;

public class JoinObserver extends UserGameCommand{
  public JoinObserver(String authToken, String username, int gameID, UserGameCommand.CommandType type, ChessGame.TeamColor playerColorr, ChessMove move) {
    super(authToken, username, gameID, type, playerColorr, move );}

}
