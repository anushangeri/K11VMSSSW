package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import net.javatutorial.entity.Visitor;
import net.javatutorial.tutorials.Main;

public class VMSManagerDAO {
	
	public static String addVisitor(Visitor v){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();

	        stmt.executeUpdate("INSERT INTO VMS "
	        		+  "(VMS_ID, NAME, COMPANY_NAME, SITE, ID_TYPE, ID_NO, MOBILE_NO, VEHICLE_NO, HOST_NAME, HOST_CONTACT, VISTOR_CARD_ID, COVID_DECLARE, "
	        		+ " REMARKS, VISIT_PURPOSE, TEMPERATURE, APPROVING_OFFICER, TIME_IN_DT, "
	        		+ " CREATED_BY, LAST_MODIFIED_BY, CREATED_BY_DT, LAST_MODIFIED_BY_DT)" + 
	        		"   VALUES ('" +v.getVmsId()+ "','" +v.getName()+ "','" +v.getCompanyName()+ "','" 
	        		+v.getSite()+ "','" +v.getIdType()+ "','" 
	        		+v.getIdNo()+ "','" +v.getMobileNo()+ "','" +v.getVehicleNo()+ "','" +v.getHostName()+ "','" 
	        		+v.getHostNo()+ "','" +v.getVisitorCardId()+ "','" +v.getCovidDeclare()+ "','" +v.getRemarks()+ "','" 
	        		+v.getVisitPurpose()+ "','" +v.getTemperature()+ "','" +v.getApprovingOfficer()+ "','" +v.getTimeInDt()+ "','"
	        		+v.getCreatedBy()+ "','" +v.getLastModifiedBy()+ "','" +v.getCreatedByDt()+ "','" +v.getLastModifiedByDt()+ "'"
	        		+ ");");
	        rs = stmt.executeQuery("SELECT NAME FROM VMS;");
	        while (rs.next()) {
	        	message = "Read from DB: " + rs.getString(1);
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
		message = "Successful";
		return message;
	}
	public static String updateVisitorTimeOut(Visitor v){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();

	        stmt.executeUpdate("SET TIMEZONE = 'Singapore'; "
	        		+ "UPDATE VMS "
	        		+  "SET TIME_OUT_DT = NOW(), " 
	        		+ "LAST_MODIFIED_BY = '" + v.getLastModifiedBy() + "',"
	    	    	+ "LAST_MODIFIED_BY_DT = '" + v.getLastModifiedByDt() + "'"	 
	        		+ "   WHERE VMS_ID = '" + v.getVmsId() + "';");
	        rs = stmt.executeQuery("SELECT NAME FROM VMS WHERE VMS_ID ='" + v.getVmsId() +"';");
	        while (rs.next()) {
	        	message = "Successfully updated: " + rs.getString(1);
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
	public static String updateVisitorRemarks(Visitor v){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();

	        stmt.executeUpdate("SET TIMEZONE = 'Singapore'; "
	        		+ "UPDATE VMS "
	        		+ "SET REMARKS = '" + v.getRemarks() + "' ," 
	        		+ "LAST_MODIFIED_BY = '" + v.getLastModifiedBy() + "',"
	    	    	+ "LAST_MODIFIED_BY_DT = '" + v.getLastModifiedByDt() + "'"	 
	        		+ " WHERE VMS_ID = '" + v.getVmsId() + "';");
	        rs = stmt.executeQuery("SELECT NAME FROM VMS WHERE VMS_ID ='" + v.getVmsId() +"';");
	        while (rs.next()) {
	        	message = "Successfully updated: " + rs.getString(1);
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
	public static String updateVisitorApprovingOfficer(Visitor v){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();

	        stmt.executeUpdate("SET TIMEZONE = 'Singapore'; "
	        		+ "UPDATE VMS "
	        		+ "SET APPROVING_OFFICER = '" + v.getApprovingOfficer() + "', " 
	        		+ "LAST_MODIFIED_BY = '" + v.getLastModifiedBy() + "',"
	    	    	+ "LAST_MODIFIED_BY_DT = '" + v.getLastModifiedByDt() + "'"	 
	        		+ " WHERE VMS_ID = '" + v.getVmsId() + "';");
	        rs = stmt.executeQuery("SELECT NAME FROM VMS WHERE VMS_ID ='" + v.getVmsId() +"';");
	        while (rs.next()) {
	        	message = "Successfully updated: " + rs.getString(1);
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
	
	public static String updateStandardVisitorTimeOutDt(Timestamp timestamp, Timestamp systemDate, Timestamp startTimestamp, Timestamp endTimestamp){
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String message = "";
		
		String sql = "SET TIMEZONE = 'Singapore'; "
				+ " UPDATE VMS SET TIME_OUT_DT = '" + timestamp + "' , "
				+ " LAST_MODIFIED_BY = ?, "
				+ " LAST_MODIFIED_BY_DT = '" + systemDate + "' "
				+ " WHERE TIME_IN_DT >= '" + startTimestamp + "' "
				+ " AND TIME_IN_DT < '" + endTimestamp + "' "
				+ " AND TIME_OUT_DT IS NULL ;";
		
		try {
			connection = Main.getConnection();
			pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "SYSTEM");

            // Execute the update
            int rowsAffected = pstmt.executeUpdate();
            message = "Rows affected: " + rowsAffected;
	        
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
        	Main.close(connection, pstmt, rs);
        }
		
		return message;
	}
	
	public static int getNextVal(){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		int result = 1;
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();
//	        stmt.executeUpdate("SELECT count(*) FROM EMPLOYEES;");
	        rs = stmt.executeQuery("SELECT MAX(CAST(VMS_ID AS INTEGER)) FROM VMS;");
	        if(rs != null) {
	        	while (rs.next()) {
		        	if(rs.getString(1) != null && !rs.getString(1).isEmpty()) {
		        		result = Integer.parseInt(rs.getString(1)) + 1;
		        	}
	                
	            }
	        }
	        
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
        	Main.close(connection, stmt, rs);
        }
		return result;
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
            		"              APPROVING_OFFICER, TIME_IN_DT, TIME_OUT_DT,"
            		+ " CREATED_BY,CREATED_BY_DT,  LAST_MODIFIED_BY, LAST_MODIFIED_BY_DT "
            		+ " FROM VMS ORDER BY TIME_IN_DT DESC; ";
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
            			rs.getString(19),
            			rs.getTimestamp(20),
            			rs.getString(21),
            			rs.getTimestamp(22));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static ArrayList<Visitor> retrieveAllCurrentDay(Timestamp timestamp) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        Visitor v = null;
        ArrayList<Visitor> vList = new ArrayList<Visitor>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT VMS_ID, NAME, " + 
            		"              COMPANY_NAME, SITE, ID_TYPE, ID_NO, MOBILE_NO, \r\n" + 
            		"              VEHICLE_NO, HOST_NAME,\r\n" + 
            		"              HOST_CONTACT, VISTOR_CARD_ID, COVID_DECLARE, REMARKS, VISIT_PURPOSE, TEMPERATURE, \r\n" + 
            		"              APPROVING_OFFICER, TIME_IN_DT, TIME_OUT_DT,"
            		+ " CREATED_BY,CREATED_BY_DT,  LAST_MODIFIED_BY, LAST_MODIFIED_BY_DT "
            		+ " FROM VMS WHERE DATE(TIME_IN_DT) = DATE(CAST('" + timestamp + "' AS TIMESTAMP))"
            		+ " ORDER BY TIME_IN_DT DESC; ";
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
            			rs.getString(19),
            			rs.getTimestamp(20),
            			rs.getString(21),
            			rs.getTimestamp(22));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }

	public static ArrayList<Visitor> retrieveAllLast10Days(Timestamp timestamp) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        Visitor v = null;
        ArrayList<Visitor> vList = new ArrayList<Visitor>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT VMS_ID, NAME, " + 
            		"              COMPANY_NAME, SITE, ID_TYPE, ID_NO, MOBILE_NO, \r\n" + 
            		"              VEHICLE_NO, HOST_NAME,\r\n" + 
            		"              HOST_CONTACT, VISTOR_CARD_ID, COVID_DECLARE, REMARKS, VISIT_PURPOSE, TEMPERATURE, \r\n" + 
            		"              APPROVING_OFFICER, TIME_IN_DT, TIME_OUT_DT,"
            		+ " CREATED_BY,CREATED_BY_DT,  LAST_MODIFIED_BY, LAST_MODIFIED_BY_DT "
            		+ " FROM VMS WHERE DATE(TIME_IN_DT) >= DATE(CAST('" + timestamp + "' AS TIMESTAMP)) - 10"
            		+ " ORDER BY TIME_IN_DT DESC; ";
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
            			rs.getString(19),
            			rs.getTimestamp(20),
            			rs.getString(21),
            			rs.getTimestamp(22));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }

	
	public static ArrayList<Visitor> retrieveBySite(String[] site) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        Visitor v = null;
        ArrayList<Visitor> vList = new ArrayList<Visitor>();
        try {
        	connection = Main.getConnection();
        	String sites = String.join("','", site);
            String sql = "SELECT VMS_ID, NAME,\r\n" + 
            		"              COMPANY_NAME, SITE, ID_TYPE, ID_NO, MOBILE_NO, \r\n" + 
            		"              VEHICLE_NO, HOST_NAME,\r\n" + 
            		"              HOST_CONTACT, VISTOR_CARD_ID, COVID_DECLARE, REMARKS, VISIT_PURPOSE, TEMPERATURE, \r\n" + 
            		"              APPROVING_OFFICER, TIME_IN_DT, TIME_OUT_DT, "
            		+ " CREATED_BY,CREATED_BY_DT,  LAST_MODIFIED_BY, LAST_MODIFIED_BY_DT "
            		+ " FROM VMS WHERE SITE IN ('" + sites + "') ORDER BY TIME_IN_DT DESC;";
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
            			rs.getString(19),
            			rs.getTimestamp(20),
            			rs.getString(21),
            			rs.getTimestamp(22));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	
	public static ArrayList<Visitor> retrieveByNRIC(String idNo) {
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
            		"              APPROVING_OFFICER, TIME_IN_DT, TIME_OUT_DT, "
            		+ " CREATED_BY,CREATED_BY_DT,  LAST_MODIFIED_BY, LAST_MODIFIED_BY_DT "
            		+ " FROM VMS WHERE ID_NO ='" + idNo + "' ORDER BY TIME_IN_DT DESC;";
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
            			rs.getString(19),
            			rs.getTimestamp(20),
            			rs.getString(21),
            			rs.getTimestamp(22));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static ArrayList<Visitor> retrieveByNameIDPopulate(String idNo) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        Visitor v = null;
        ArrayList<Visitor> vList = new ArrayList<Visitor>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT VMS_ID, NAME, \r\n" + 
            		"              COMPANY_NAME, SITE, ID_TYPE, ID_NO, MOBILE_NO, \r\n" + 
            		"              VEHICLE_NO, HOST_NAME, \r\n" + 
            		"              HOST_CONTACT, VISTOR_CARD_ID, COVID_DECLARE, REMARKS, VISIT_PURPOSE, TEMPERATURE, \r\n" + 
            		"              APPROVING_OFFICER, TIME_IN_DT, TIME_OUT_DT, "
            		+ " CREATED_BY, CREATED_BY_DT,  LAST_MODIFIED_BY, LAST_MODIFIED_BY_DT "
            		+ " FROM VMS "
            		+ " WHERE ID_NO ='" + idNo + "'"
    				+ " ORDER BY TIME_IN_DT DESC";
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
            			rs.getString(19),
            			rs.getTimestamp(20),
            			rs.getString(21),
            			rs.getTimestamp(22));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static Visitor retrieveByVmsId(String vmsId) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        Visitor v = null;
        try {
        	connection = Main.getConnection();
            String sql = "SELECT VMS_ID, NAME,\r\n" + 
            		"              COMPANY_NAME, SITE, ID_TYPE, ID_NO, MOBILE_NO, \r\n" + 
            		"              VEHICLE_NO, HOST_NAME,\r\n" + 
            		"              HOST_CONTACT, VISTOR_CARD_ID, COVID_DECLARE, REMARKS, VISIT_PURPOSE, TEMPERATURE, \r\n" + 
            		"              APPROVING_OFFICER, TIME_IN_DT, TIME_OUT_DT, "
            		+ " CREATED_BY, CREATED_BY_DT,  LAST_MODIFIED_BY, LAST_MODIFIED_BY_DT "
            		+ " FROM VMS WHERE VMS_ID ='" + vmsId + "' ORDER BY TIME_IN_DT DESC;";
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
            			rs.getString(19),
            			rs.getTimestamp(20),
            			rs.getString(21),
            			rs.getTimestamp(22));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return v;
    }
	
	public static String deleteAll() {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        String message = "All records deleted - No visitor records available";
        try {
        	connection = Main.getConnection();
            String sql = "DELETE FROM VMS WHERE TIME_IN_DT <= GETDATE() - 30;";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return message;
    }
}
