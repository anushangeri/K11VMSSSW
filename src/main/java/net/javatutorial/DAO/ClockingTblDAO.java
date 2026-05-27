package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import net.javatutorial.tutorials.*;

public class ClockingTblDAO {

	public static String createClockingTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS CLOCKING (\r\n"
	        		+ "    CLOCKING_ID VARCHAR(100) NOT NULL PRIMARY KEY,\r\n"
	        		+ "    CLOCKING_POINT_NAME VARCHAR(100) NOT NULL,\r\n"
	        		+ "    SITE_NAME VARCHAR(100) NOT NULL,\r\n"
	        		+ "    CREATED_DT TIMESTAMP NOT NULL DEFAULT NOW(),\r\n"
	        		+ "    LAST_MODIFIED_DT TIMESTAMP NULL,\r\n"
	        		+ "    CREATEDBY VARCHAR(100) NULL,\r\n"
	        		+ "    LASTMODIFIEDBY VARCHAR(100) NULL\r\n"
	        		+ ");");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			message = "" + e;
			//e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			message = "" + e;
		}
		message = "Successful";
		return message;
	}
	public static String deleteClockingTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DROP TABLE CLOCKING;");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			message = "" + e;
			//e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			message = "" + e;
		}
		message = "Successful";
		return message;
	}
	
	// update query when needed and deploy
	public static String updateClockingTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("ALTER TABLE CLOCKING " +
	                "ADD COLUMN CREATEDBY VARCHAR(100) NULL, " +
	                "ADD COLUMN LASTMODIFIEDBY VARCHAR(100) NULL;");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			message = "" + e;
			//e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			message = "" + e;
		}
		message = "Successful";
		return message;
	}
}
