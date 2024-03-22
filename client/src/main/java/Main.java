import chess.*;
import server.Server;

public class Main {
    public static void main(String[] args) {
        var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        System.out.println("♕ 240 Chess Client: " + piece);
        System.out.println("Welcome to 240 chess. Type Help to get started");
        Server server = new Server();
        int port = 8080;
        //System.out.println("♕ 240 Chess Server: " + piece);
        server.run(port);
        LoggedOut stageLoggedOut = new LoggedOut("http://localhost:8080");
        try{stageLoggedOut.main(args);}
        catch(Exception e){
            System.out.print(e.getMessage());
        }
        server.stop();
    }
}