package io.v.jenkins.plugins.vanadium_test_results_analyzer;

import java.io.PrintStream;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class Util {
  
  private static final String CONSOLE_LOG_PREFIX = "[Vanadium Test Results Analyzer]";

  // Establishes a connection to the given database server.
  static Connection getConnection(String serverIP, String rootPassword, String database)
      throws SQLException {
    DriverManager.setLoginTimeout(10);
    String url =
        "jdbc:mysql://"
            + serverIP
            + (database != null ? ("/" + database) : "")
            + "?useSSL=false&serverTimezone=UTC";
    return DriverManager.getConnection(url, "root", rootPassword);
  }
  
    static boolean getConnectionForMongo(String serverIP, String userName, String rootPassword, String database) throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(serverIP, 27017);
        DB db = mongoClient.getDB(database);
        char a[] = rootPassword.toCharArray();
        boolean auth = db.authenticate(userName, a);
        return auth;
    }
  
  // Logs the given message to the console with a prefix.
  static void logToConsole(PrintStream ps, String msg) {
    ps.print(String.format("%s: %s", CONSOLE_LOG_PREFIX, msg));
  }
}
