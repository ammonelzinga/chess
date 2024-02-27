package server;

import spark.*;

public class Server {

    UserHandler userHandler = new UserHandler();

    public int run(int desiredPort) {
        System.out.print("running server");
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.
        Spark.post("/user", (req, res) -> userHandler.registerUser(req, res));



        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
