package webSocketMessages.userCommands;

import chess.ChessGame;
import chess.ChessMove;

public class ResignCommand extends UserGameCommand {
  public ResignCommand(String authToken, String username, int gameID, UserGameCommand.CommandType type, ChessGame.TeamColor playerColorr, ChessMove move) {
    super(authToken, username, gameID, type, playerColorr, move );}
}
