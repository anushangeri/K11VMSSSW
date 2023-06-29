package net.javatutorial.tutorials;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
//    
//    public static void main(String[] args) throws Exception {
//        
//        Connection connection = getConnection();
//        
//        Statement stmt = connection.createStatement();
//        stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
//        stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
//        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
//        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
//        while (rs.next()) {
//            System.out.println("Read from DB: " + rs.getTimestamp("tick"));
//        }
//    }
//    
    public static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
//    	String dbUrl = System.getenv("JDBC_DATABASE_URL");
//        return DriverManager.getConnection(dbUrl);
    }
    
    /**
     * close the given connection, statement and resultset
     *
     * @param conn the connection object to be closed
     * @param stmt the statement object to be closed
     * @param rs the resultset object to be closed
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.WARNING,
                    "Unable to close ResultSet", ex);
        }
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.WARNING,
                    "Unable to close Statement", ex);
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.WARNING,
                    "Unable to close Connection", ex);
        }
    }
}