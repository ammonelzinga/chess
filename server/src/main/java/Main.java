import chess.*;
import server.Server;


//public static void main(String[] args)
public class Main {
    public static void main(String[] args) {
        var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        System.out.println("♕ 240 Chess Server: " + piece);
        new Main().run();
    }

    private void run(){
        //var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        System.out.print("hello");
        Server server = new Server();
        int port = 8080;
        //System.out.println("♕ 240 Chess Server: " + piece);
        server.run(port);
        System.out.print("okayy");
    }
}