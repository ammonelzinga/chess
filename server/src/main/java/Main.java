import chess.*;
import server.Server;
import dataAccess.*;
import model.*;


import java.sql.SQLException;

//public static void main(String[] args)
public class Main {
    public static void main(String[] args) {
        var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        System.out.println("♕ 240 Chess Server: " + piece);
        new Main().run();
    }

    private void run(){
        /*GameDAO sqlgameD = new sqlGameDAO();
        ChessGame newGame=new ChessGame();
        ChessBoard board = new ChessBoard();
        board.resetBoard();
        newGame.setBoard(board);
        GameData game = new GameData(2, "ammon", "emma", "awesome", newGame);
        try{sqlgameD.createGame(game);}
            catch(Exception e){
                System.out.print(e.getMessage());
            }
        try{GameData gameData = sqlgameD.getGame(2); System.out.print(gameData.toString());
        System.out.print(gameData.game().getBoard().toString());}
        catch(DataAccessException e){
            System.out.print(e.getMessage());
        }*/
        //UserDAO sqlUserDao = new sqlUserDAO();
        /*UserData newUser = new UserData("Eeyore", "dismal", "eeyore@fakeemail.com");
        UserDAO sqlUserDao = new sqlUserDAO();
        try{sqlUserDao.createUser(newUser);}
        catch(DataAccessException e){
            System.out.print(e.getMessage());
        }
        try{UserData currentUser = sqlUserDao.getUser("Rabbit");
            System.out.print(currentUser);}
            //System.out.print(currentUser.username());
            //System.out.print(currentUser.password());
            //System.out.print(currentUser.email());}
        catch(DataAccessException e){
            System.out.print(e.getMessage());
        }*/
        /*try{sqlUserDao.clearAllUserData();}
        catch(DataAccessException e){
            System.out.print(e.getMessage());
        }*/

        //var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        System.out.print("helloo");
        Server server = new Server();
        int port = 8080;
        //System.out.println("♕ 240 Chess Server: " + piece);
        server.run(port);
        System.out.print("okayy");


    }
}