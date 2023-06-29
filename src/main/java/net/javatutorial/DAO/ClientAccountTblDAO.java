package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import net.javatutorial.tutorials.*;

public class ClientAccountTblDAO {

	public static String createClientAccountTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
//	        stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
	        stmt.executeUpdate("DROP TABLE IF EXISTS CLIENTACCOUNT;"
	        		+ "CREATE TABLE IF NOT EXISTS CLIENTACCOUNT (\r\n" + 
	        		"   ACCOUNT_ID VARCHAR (100)  NOT NULL,\r\n" + 
	        		"   NAME VARCHAR (100)  NOT NULL,\r\n" + 
	        		"   SITE TEXT []   NULL, \r\n" + 
	        		"   ID_TYPE VARCHAR (100)   NULL, \r\n" + 
	        		"   ID_NO VARCHAR (100)   NULL, \r\n" + 
	        		"   PASSWORD  VARCHAR (255) NOT NULL,   \r\n" + 
	        		"   SALT  VARCHAR (8000) NOT NULL,   \r\n" + 
	        		"   ACCESS_TYPE VARCHAR (100)   NULL, \r\n" + 
	        		"   CREATED_DT TIMESTAMP  NOT NULL DEFAULT NOW(),\r\n" + 
	        		"   MODIFIED_DT TIMESTAMP   NULL \r\n" + 
	        		"); "
	        		);
//	        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
//	        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
//	        while (rs.next()) {
//	        	responseObj = responseObj + "Read from DB: " + rs.getTimestamp("tick");
//	        }
	        message = "Successful";
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			message = "" + e;
			//e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			message = "" + e;
		}
		return message;
	}
	public static String deleteClientAccountTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
//	        stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
	        stmt.executeUpdate("DROP TABLE CLIENTACCOUNT;");
//	        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
//	        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
//	        while (rs.next()) {
//	        	responseObj = responseObj + "Read from DB: " + rs.getTimestamp("tick");
//	        }
	        message = "Successful";
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			message = "" + e;
			//e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			message = "" + e;
		}
		return message;
	}
	
	public static String updateClientAccountTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
//	        stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
	        stmt.executeUpdate("ALTER TABLE CLIENTACCOUNT\r\n" + 
	        		"ALTER COLUMN SITE TYPE TEXT [];");
//	        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
//	        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
//	        while (rs.next()) {
//	        	responseObj = responseObj + "Read from DB: " + rs.getTimestamp("tick");
//	        }
	        message = "Successful";
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			message = "" + e;
			//e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			message = "" + e;
		}
		return message;
	}
}
