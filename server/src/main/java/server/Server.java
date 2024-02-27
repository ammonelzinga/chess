package server;

import dataAccess.*;
import service.GameService;
import service.GeneralService;
import service.UserService;
import spark.*;
import dataAccess.DataAccessException;
import com.google.gson.Gson;

public class Server {

    UserDAO userDAO = new MemoryUserDAO();
    AuthDAO authDAO = new MemoryAuthDAO();
    GameDAO gameDAO = new MemoryGameDAO();
    UserService userService = new UserService(userDAO, authDAO);
    GeneralService generalService = new GeneralService(userDAO, authDAO, gameDAO);
    GameService gameService = new GameService(userDAO, authDAO, gameDAO);
    UserHandler userHandler = new UserHandler(userDAO, authDAO, gameDAO, userService, generalService, gameService);
    GameHandler gameHandler = new GameHandler(userDAO, authDAO, gameDAO, userService, generalService, gameService);

    public int run(int desiredPort) {
        System.out.print("running server");
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.
        Spark.post("/user", (req, res) -> userHandler.registerUser(req, res));
        Spark.exception(DataAccessException.class, this::exceptionHandler);
        Spark.delete("/db", (req, res) -> userHandler.clearAll(req, res));
        Spark.post("/session", (req, res) -> userHandler.login(req, res));
        Spark.delete("/session", (req, res) -> userHandler.logout(req, res));
        Spark.post("/game", (req, res) -> gameHandler.createGame(req, res));
        Spark.get("/game", (req, res) -> gameHandler.listGames(req, res));

        Spark.awaitInitialization();
        return Spark.port();
    }
    private Object exceptionHandler(DataAccessException ex, Request req, Response res){
        res.status(ex.getStatusCode());
        return new Gson().toJson(new ErrorRecord(ex.getMessage()));
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
