package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import net.javatutorial.tutorials.*;

public class DropdownListTblDAO {

	public static String createDropdownTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DROP TABLE IF EXISTS DROPDOWN; "
	        		+ "CREATE TABLE IF NOT EXISTS DROPDOWN (\r\n" + 
	        		"   DROPDOWN_ID VARCHAR (100)  NOT NULL,\r\n" + 
	        		"   DROPDOWN_KEY VARCHAR (100)  NOT NULL,\r\n" + 
	        		"   DROPDOWN_VALUE VARCHAR (100)  NOT NULL,\r\n"  +
	        		"   CREATED_DT TIMESTAMP  NOT NULL DEFAULT NOW(),\r\n"  +
	        		"   LAST_MODIFIED_DT TIMESTAMP   NULL \r\n"  +
	        		");");
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
	public static String deleteDropdownTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DROP TABLE DROPDOWN;");
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
	
	public static String updateDropdownTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("ALTER TABLE DROPDOWN\r\n" + 
	        		"ADD COLUMN SEAL_NO VARCHAR (100)  NULL,"
	        		+ "ADD COLUMN CONTAINER_SIZE VARCHAR (100)  NULL;");
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
