package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import net.javatutorial.tutorials.*;

public class VMSTblDAO {

	public static String createVmsTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
//	        stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
	        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS VMS (\r\n" + 
	        		"   VMS_ID VARCHAR (100)  NOT NULL,\r\n" + 
	        		"   NAME VARCHAR (100)  NOT NULL,\r\n" + 
	        		"   COMPANY_NAME VARCHAR (100)  NULL,\r\n" + 
	        		"   SITE VARCHAR (100)   NULL, \r\n" + 
	        		"   ID_TYPE VARCHAR (100)   NULL, \r\n" + 
	        		"   ID_NO VARCHAR (100)   NULL, \r\n" + 
	        		"   MOBILE_NO  VARCHAR (100) NOT NULL,   \r\n" + 
	        		"   VEHICLE_NO  VARCHAR (100)  NULL,\r\n" + 
	        		"   HOST_NAME  VARCHAR (100) NOT NULL, \r\n" +  
	        		"   HOST_CONTACT  VARCHAR (100) NOT NULL,\r\n" + 
	        		"   VISTOR_CARD_ID  VARCHAR (100) NOT NULL,\r\n" + 
	        		"   COVID_DECLARE  VARCHAR (100) NOT NULL,\r\n" + 
	        		"   VISIT_PURPOSE  VARCHAR (100) NOT NULL,\r\n" + 
	        		"   TEMPERATURE  VARCHAR (100) NOT NULL,\r\n" + 
	        		"   REMARKS  VARCHAR (100) NULL,\r\n" + 
	        		"   APPROVING_OFFICER  VARCHAR (100) NULL,\r\n" + 
	        		"   TIME_IN_DT TIMESTAMP  NOT NULL DEFAULT NOW(),\r\n" + 
	        		"   TIME_OUT_DT TIMESTAMP   NULL, \r\n" + 
	        		"   CREATED_BY  VARCHAR (100) NULL,\r\n" + 
	        		"   LAST_MODIFIED_BY  VARCHAR (100) NULL,\r\n" + 
	        		"   CREATED_BY_DT TIMESTAMP  NOT NULL DEFAULT NOW(),\r\n" + 
	        		"   LAST_MODIFIED_BY_DT TIMESTAMP  NOT NULL DEFAULT NOW() \r\n" + 
	        		");");
//	        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
//	        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
//	        while (rs.next()) {
//	        	responseObj = responseObj + "Read from DB: " + rs.getTimestamp("tick");
//	        }
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
	public static String deleteVmsTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
//	        stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
	        stmt.executeUpdate("DROP TABLE VMS;");
//	        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
//	        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
//	        while (rs.next()) {
//	        	responseObj = responseObj + "Read from DB: " + rs.getTimestamp("tick");
//	        }
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
	
	public static String updateVmsTbl(){
		Connection connection;
		String message = "Successful";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
//	        stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
	        stmt.executeUpdate("ALTER TABLE VMS \r\n" + 
	        		"ADD COLUMN CREATED_BY  VARCHAR (100) NULL, \r\n" + 
	        		"ADD COLUMN LAST_MODIFIED_BY  VARCHAR (100) NULL, \r\n" + 
	        		"ADD COLUMN CREATED_BY_DT TIMESTAMP  NOT NULL DEFAULT NOW(), \r\n" + 
	        		"ADD COLUMN LAST_MODIFIED_BY_DT TIMESTAMP  NOT NULL DEFAULT NOW() \r\n" + 
	        		 ";");
//	        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
//	        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
//	        while (rs.next()) {
//	        	responseObj = responseObj + "Read from DB: " + rs.getTimestamp("tick");
//	        }
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
