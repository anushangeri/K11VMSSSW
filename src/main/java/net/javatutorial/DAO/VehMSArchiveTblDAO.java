package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import net.javatutorial.tutorials.*;

public class VehMSArchiveTblDAO {

	public static String createVehmsArchiveTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS VEHMS_ARCHIVED (\r\n" + 
	        		"   VEHICLE_ID VARCHAR (100)  NOT NULL,\r\n" + 
	        		"   NAME VARCHAR (100)  NOT NULL,\r\n" + 
	        		"   COMPANY_NAME VARCHAR (100)  NULL,\r\n" + 
	        		"   ID_TYPE VARCHAR (100)   NULL, \r\n" + 
	        		"   ID_NO VARCHAR (100)   NULL, \r\n" + 
	        		"   MOBILE_NO  VARCHAR (100) NULL,   \r\n" + 
	        		"   PRIME_MOVER_NO  VARCHAR (100)  NULL,\r\n" + 
	        		"   CONTAINER_NO  VARCHAR (100) NULL, \r\n" +  
	        		"   SEAL_NO  VARCHAR (100) NULL, \r\n" +  
	        		"   CONTAINER_SIZE  VARCHAR (100) NULL, \r\n" +
	        		"   LOADED_FLAG  VARCHAR (100) NULL,\r\n" + 
	        		"   COVID_DECLARE_FLAG  VARCHAR (100) NULL,\r\n" + 
	        		"   LORRY_CHET_NO  VARCHAR (100) NULL,\r\n" + 
	        		"   DELIVERY_NOTICE_NO  VARCHAR (100) NULL,\r\n" + 
	        		"   VISIT_PURPOSE  VARCHAR (100) NULL,\r\n" + 
	        		"   TEMPERATURE  VARCHAR (100) NULL,\r\n" + 
	        		"   REMARKS  VARCHAR (100) NULL,\r\n" + 
	        		"   WAREHOUSE_LEVEL  INT  NULL,\r\n" + 	        
	        		"   SITE  VARCHAR (100) NULL,\r\n" + 	
	        		"   WAREHOUSE_APPROVER  VARCHAR (100) NULL,\r\n" + 	       
	        		"   TIME_IN_DT TIMESTAMP  NOT NULL DEFAULT NOW(),\r\n" + 
	        		"   TIME_OUT_DT TIMESTAMP   NULL, \r\n" + 
	        		"   ARCHIVED_DT TIMESTAMP  NOT NULL DEFAULT NOW(), \r\n" + 
	        		"   CREATED_BY  VARCHAR (100) NULL,\r\n" + 
	        		"   LAST_MODIFIED_BY  VARCHAR (100) NULL,\r\n" + 
	        		"   CREATED_BY_DT TIMESTAMP  NOT NULL DEFAULT NOW(),\r\n" + 
	        		"   LAST_MODIFIED_BY_DT TIMESTAMP  NOT NULL DEFAULT NOW() \r\n" + 
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
	public static String deleteVehmsArchiveTbl(){
		Connection connection;
		String message = "";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("DROP TABLE VEHMS_ARCHIVED;");
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
	
	public static String updateVehmsArchiveTbl(){
		Connection connection;
		String message = "Successful";
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate("ALTER TABLE VEHMS_ARCHIVED \r\n" + 
	        		"ADD COLUMN CREATED_BY  VARCHAR (100) NULL, \r\n" + 
	        		"ADD COLUMN LAST_MODIFIED_BY  VARCHAR (100) NULL, \r\n" + 
	        		"ADD COLUMN CREATED_BY_DT TIMESTAMP  NOT NULL DEFAULT NOW(), \r\n" + 
	        		"ADD COLUMN LAST_MODIFIED_BY_DT TIMESTAMP  NOT NULL DEFAULT NOW() \r\n" + 
	        		 ";");
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
