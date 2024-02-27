import chess.*;
import server.Server;

public class Main {
    public static void main(String[] args) {
        //var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        Server server = new Server();
        int port = 8080;
        //System.out.println("♕ 240 Chess Server: " + piece);
        server.run(port);
        System.out.print("okayy");
    }
}