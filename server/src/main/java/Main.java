import chess.*;
import server.Server;
import dataAccess.*;
import model.*;
//public static void main(String[] args)
public class Main {
    public static void main(String[] args) {
        var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        System.out.println("♕ 240 Chess Server: " + piece);
        new Main().run();
    }

    private void run(){
        UserData newUser = new UserData("Eeyore", "dismal", "eeyore@fakeemail.com");
        UserDAO sqlUserDao = new sqlUserDAO();
        try{sqlUserDao.createUser(newUser);}
        catch(DataAccessException e){
            System.out.print(e.getMessage());
        }
        try{UserData currentUser = sqlUserDao.getUser("Rabbit");
            System.out.print(currentUser.username());
            System.out.print(currentUser.password());
            System.out.print(currentUser.email());}
        catch(DataAccessException e){
            System.out.print(e.getMessage());
        }

        //var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        /*System.out.print("hello");
        Server server = new Server();
        int port = 8080;
        //System.out.println("♕ 240 Chess Server: " + piece);
        server.run(port);
        System.out.print("okayy");*/

    }
}