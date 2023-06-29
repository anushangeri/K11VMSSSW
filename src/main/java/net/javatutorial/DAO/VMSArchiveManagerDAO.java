package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.javatutorial.entity.Visitor;
import net.javatutorial.tutorials.Main;

public class VMSArchiveManagerDAO {
	
	public static String moveVisitor(){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();

	        stmt.executeUpdate("INSERT INTO VMS_ARCHIVED "
	        		+  "(VMS_ID, NAME, COMPANY_NAME, SITE, ID_TYPE, ID_NO, MOBILE_NO, VEHICLE_NO, HOST_NAME, HOST_CONTACT, VISTOR_CARD_ID, COVID_DECLARE, "
	        		+ " REMARKS, VISIT_PURPOSE, TEMPERATURE, APPROVING_OFFICER, TIME_IN_DT, TIME_OUT_DT, ARCHIVED_DT, CREATED_BY, LAST_MODIFIED_BY, CREATED_BY_DT, LAST_MODIFIED_BY_DT )" + 
	        		"  SELECT VMS_ID, NAME, COMPANY_NAME, SITE, ID_TYPE, ID_NO, MOBILE_NO, VEHICLE_NO, HOST_NAME, HOST_CONTACT, VISTOR_CARD_ID, COVID_DECLARE, " + 
	        		"	REMARKS, VISIT_PURPOSE, TEMPERATURE, APPROVING_OFFICER, TIME_IN_DT, TIME_OUT_DT, NOW(),"
	        		+ " CREATED_BY, LAST_MODIFIED_BY, CREATED_BY_DT, LAST_MODIFIED_BY_DT "
	        		+ " FROM VMS WHERE TIME_IN_DT <= (CURRENT_DATE - INTERVAL '30 days');"
	        		+ " DELETE FROM VMS WHERE TIME_IN_DT <= (CURRENT_DATE - INTERVAL '30 days');");
	        rs = stmt.executeQuery("SELECT NAME FROM VMS_ARCHIVED ORDER BY VMS_ID DESC LIMIT 1;");
	        while (rs.next()) {
	        	message = "Archived visitor records successful : " + rs.getString(1);
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
		return message;
	}
	
	public static ArrayList<Visitor> retrieveAll() {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        Visitor v = null;
        ArrayList<Visitor> vList = new ArrayList<Visitor>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT VMS_ID, NAME,\r\n" + 
            		"              COMPANY_NAME, SITE, ID_TYPE, ID_NO, MOBILE_NO, \r\n" + 
            		"              VEHICLE_NO, HOST_NAME,\r\n" + 
            		"              HOST_CONTACT, VISTOR_CARD_ID, COVID_DECLARE, REMARKS, VISIT_PURPOSE, TEMPERATURE, \r\n" + 
            		"              APPROVING_OFFICER, TIME_IN_DT, TIME_OUT_DT, ARCHIVED_DT , CREATED_BY,CREATED_BY_DT,  LAST_MODIFIED_BY, LAST_MODIFIED_BY_DT "
            		+ "  FROM VMS_ARCHIVED ORDER BY TIME_IN_DT DESC; ";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	v = new Visitor(rs.getString(1), 
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
            			rs.getTimestamp(17),
            			rs.getTimestamp(18),
            			rs.getTimestamp(19),
            			rs.getString(20),
            			rs.getTimestamp(21),
            			rs.getString(22),
            			rs.getTimestamp(23));
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
            String sql = "DELETE FROM VMS_ARCHIVED WHERE TIME_IN_DT <= (CURRENT_DATE - INTERVAL '30 days');";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            message = "Delete visitor successful - only one month worth of records in DB";
            
        } catch (Exception e) {
            e.printStackTrace();
            message = "Exception: " + e;
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return message;
    }
}
