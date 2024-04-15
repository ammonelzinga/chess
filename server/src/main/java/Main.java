import chess.*;
import server.Server;
import dataAccess.*;
import model.*;


import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        System.out.println("♕ 240 Chess Server: " + piece);
        new Main().run();
    }

    private void run(){
        System.out.print("helloo");
        Server server = new Server();
        int port = 8080;
        server.run(port);
        System.out.print("okayy");


    }
}