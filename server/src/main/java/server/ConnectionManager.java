package server;
import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import webSocketMessages.serverMessages.ServerMessage;
//import webSocketMessages.Notification;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionManager {
  public final ConcurrentHashMap<Integer, ArrayList<Connection>> connectionMap = new ConcurrentHashMap<>();

  public void add(String username, int gameID, Session session) {
    System.out.println("gonna add");
    System.out.println(username);
    var connection = new Connection(username, session);
    if(connectionMap.containsKey(gameID)){
      connectionMap.get(gameID).add(connection);
    }
    else{
      ArrayList<Connection> connectionList = new ArrayList<>();
      connectionList.add(connection);
      connectionMap.put(gameID, connectionList);
    }
  }

  public void remove(String username, int gameID, Session session) {
    if(connectionMap.containsKey(gameID)){
      Connection connection = new Connection(username, session);
      connectionMap.get(gameID).remove(connection.username);
      connectionMap.get(gameID).remove(connection.session);
      connectionMap.get(gameID).remove(connection);
  }}

  public void broadcastRootUser(String username, ServerMessage notification, int gameID) throws IOException {
    var removeList = new ArrayList<Connection>();
    if(connectionMap.get(gameID) != null){
      for (var c : connectionMap.get(gameID)) {
        System.out.println(c);
        if (c.session.isOpen()) {
          if (c.username.equals(username)) {
            c.send(new Gson().toJson(notification));
          }} else {
          removeList.add(c);
        }}
      for (var c : removeList) {
        connectionMap.get(gameID).remove(c.username);
        connectionMap.get(gameID).remove(c.session);
        connectionMap.get(gameID).remove(c);
      }}}

  public void broadcastRootUserError(String username, ServerMessage notification, int gameID, Session session) throws IOException {
    var removeList = new ArrayList<Connection>();
    if(connectionMap.get(gameID) != null){
      for (var c : connectionMap.get(gameID)) {
        System.out.println(c);
        if (c.session.isOpen()) {
          if (c.session.equals(session)) {
            c.send(new Gson().toJson(notification));
          }} else {
          removeList.add(c);
        }}
      for (var c : removeList) {
        connectionMap.get(gameID).remove(c.session);
        connectionMap.get(gameID).remove(c.session);
        connectionMap.get(gameID).remove(c);
      }}}

  public void broadcast(String excludeUsername, ServerMessage notification, int gameID) throws IOException {
    System.out.println("going to try broadcasting");
    System.out.print(connectionMap.size());
    var removeList = new ArrayList<Connection>();
    if(connectionMap.get(gameID) != null){
    for (var c : connectionMap.get(gameID)) {
      System.out.println(".......");
      System.out.println(c);
      System.out.println(c.session);
      System.out.println(c.username);
      if (c.session.isOpen()) {
        System.out.println("seeing an open session");
        if (!c.username.equals(excludeUsername)) {
          c.send(new Gson().toJson(notification));
        }
      } else {
        System.out.println("This is not an open session");
        removeList.add(c);

      }
    }

    // Clean up any connections that were left open.
    for (var c : removeList) {
      connectionMap.get(gameID).remove(c.username);
      connectionMap.get(gameID).remove(c.session);
      connectionMap.get(gameID).remove(c);
    }
  }}
}
