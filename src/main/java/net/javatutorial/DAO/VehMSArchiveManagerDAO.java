package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.javatutorial.entity.Vehicle;
import net.javatutorial.tutorials.Main;
/*
 * Archiving Logic: daily batch job will move data from main table to archive table, 
 * so main table will have only 30 days worth of data.
 * 
 * Monthly batch job will run to retrieve all data in archive table and email to client 
 * and delete data from archive table leaving only 30 days
 */
public class VehMSArchiveManagerDAO {
	
	public static String moveVehicle(){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();

	        stmt.executeUpdate("INSERT INTO VEHMS_ARCHIVED "
	        		+ "(VEHICLE_ID, NAME, COMPANY_NAME, ID_TYPE, ID_NO, MOBILE_NO, PRIME_MOVER_NO, CONTAINER_NO, "
	        		+ " LOADED_FLAG, COVID_DECLARE_FLAG, LORRY_CHET_NO, DELIVERY_NOTICE_NO,"
	        		+ " VISIT_PURPOSE, TEMPERATURE, SEAL_NO, CONTAINER_SIZE, REMARKS, WAREHOUSE_LEVEL, SITE, WAREHOUSE_APPROVER,"
	        		+ " TIME_IN_DT, TIME_OUT_DT, ARCHIVED_DT, CREATED_BY, LAST_MODIFIED_BY, CREATED_BY_DT, LAST_MODIFIED_BY_DT )" + 
	        		"  SELECT VEHICLE_ID, NAME, COMPANY_NAME, ID_TYPE, ID_NO, MOBILE_NO, PRIME_MOVER_NO, CONTAINER_NO, "
	        		+ " LOADED_FLAG, COVID_DECLARE_FLAG, LORRY_CHET_NO, DELIVERY_NOTICE_NO,"
	        		+ " VISIT_PURPOSE, TEMPERATURE, SEAL_NO, CONTAINER_SIZE, REMARKS, WAREHOUSE_LEVEL, SITE, WAREHOUSE_APPROVER, "
	        		+ " TIME_IN_DT, TIME_OUT_DT, NOW(), CREATED_BY, LAST_MODIFIED_BY, CREATED_BY_DT, LAST_MODIFIED_BY_DT "
	        		+ " FROM VEHMS WHERE TIME_IN_DT <= (CURRENT_DATE - INTERVAL '30 days');"
	        		+ " DELETE FROM VEHMS WHERE TIME_IN_DT <= (CURRENT_DATE - INTERVAL '30 days');");
	        rs = stmt.executeQuery("SELECT LAST(NAME) FROM VEHMS_ARCHIVED;");
	        while (rs.next()) {
	        	message = "Archived vehicle records successful : " + rs.getTimestamp("tick");
	        }
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			message = "" + e;
			//e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			message = "" + e;
		}
		finally {
        	Main.close(connection, stmt, rs);
        }
		message = "successfull" ;
		return message;
	}
		
	public static ArrayList<Vehicle> retrieveAll() {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        Vehicle v = null;
        ArrayList<Vehicle> vList = new ArrayList<Vehicle>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT VEHICLE_ID, NAME, COMPANY_NAME, ID_TYPE, ID_NO, MOBILE_NO, PRIME_MOVER_NO, "
            		+ "CONTAINER_NO, LOADED_FLAG, COVID_DECLARE_FLAG, LORRY_CHET_NO, DELIVERY_NOTICE_NO, \r\n" 
            		+ "VISIT_PURPOSE, TEMPERATURE, SEAL_NO, CONTAINER_SIZE, REMARKS, WAREHOUSE_LEVEL, SITE, WAREHOUSE_APPROVER,"
            		+ "TIME_IN_DT, TIME_OUT_DT, ARCHIVED_DT, CREATED_BY,  CREATED_BY_DT, LAST_MODIFIED_BY, LAST_MODIFIED_BY_DT  \r\n"
            		+ "FROM VEHMS_ARCHIVED ORDER BY TIME_IN_DT DESC; ";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	v = new Vehicle(rs.getString(1), 
            			rs.getString(2),
            			rs.getString(3),
            			rs.getString(4),
            			rs.getString(5),
            			rs.getString(6),
            			rs.getString(7),
            			rs.getString(8),
            			rs.getString(9),
            			rs.getString(10),
            			rs.getString(11),
            			rs.getString(12),
            			rs.getString(13),
            			rs.getString(14),
            			rs.getString(15),
            			rs.getString(16),
            			rs.getString(17),
            			rs.getInt(18),
            			rs.getString(19),
            			rs.getString(20),
            			rs.getTimestamp(21),
            			rs.getTimestamp(22),
            			rs.getTimestamp(23),
            			rs.getString(24),
            			rs.getTimestamp(25),
            			rs.getString(26),
            			rs.getTimestamp(27));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static String deleteAll() {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        String message = "No message";
        try {
        	connection = Main.getConnection();
            String sql = "DELETE FROM VEHMS_ARCHIVED WHERE TIME_IN_DT <= (CURRENT_DATE - INTERVAL '30 days');";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            message = "Delete vehicle successful - only one month worth of records in DB";
            
        } catch (Exception e) {
            e.printStackTrace();
            message = "Exception: " + e;
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return message;
    }
}
