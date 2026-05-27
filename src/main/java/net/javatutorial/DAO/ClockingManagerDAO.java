package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import net.javatutorial.entity.Clocking;
import net.javatutorial.tutorials.Main;

public class ClockingManagerDAO {
	
	public static String addClocking(Clocking v){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();
			
			stmt.executeUpdate("INSERT INTO CLOCKING " +
					"(CLOCKING_ID, CLOCKING_POINT_NAME, SITE_NAME, CREATED_DT, LAST_MODIFIED_DT, CREATEDBY, LASTMODIFIEDBY) " +
					"VALUES ('" + v.getClockingId() + "', '" + v.getClockingPointName() + "', '" + 
					v.getSiteName() + "', '" + v.getCreatedDt() + "', '" + v.getLastModifiedDt() + "', '" +
					v.getCreatedBy() + "', '" + v.getLastModifiedBy() + "');");

			// Example of how to fetch the inserted ID or record (if needed)
			rs = stmt.executeQuery("SELECT CLOCKING_ID FROM CLOCKING WHERE CLOCKING_ID = '" + v.getClockingId() + "';");

	        while (rs.next()) {
	        	message = "Clocking point successfully added!";
	        }
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			message = "ERROR: " + e;
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
	
	public static String updateClockingDetails(Clocking v) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();

	        stmt.executeUpdate("SET TIMEZONE = 'Singapore'; " +
	            "UPDATE CLOCKING SET " +
	            "CLOCKING_POINT_NAME = '" + v.getClockingPointName() + "', " +
	            "SITE_NAME = '" + v.getSiteName() + "', " +
	            "LAST_MODIFIED_DT = NOW() " +
	            "WHERE CLOCKING_ID = '" + v.getClockingId() + "';");

	        rs = stmt.executeQuery("SELECT CLOCKING_POINT_NAME FROM CLOCKING WHERE CLOCKING_ID ='" + v.getClockingId() + "';");
	        while (rs.next()) {
	        	message = "Read from DB: " + rs.getString("CLOCKING_POINT_NAME");
	        }
		} catch (URISyntaxException | SQLException e) {
			message = e.toString();
		} finally {
	    	Main.close(connection, stmt, rs);
	    }
		message = "Successful";
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
	        rs = stmt.executeQuery("SELECT MAX(CAST(CLOCKING_ID AS INTEGER)) FROM CLOCKING;");
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
	
	public static ArrayList<Clocking> retrieveAllClockings() {
	    PreparedStatement pstmt = null;
	    Connection connection = null;
	    ResultSet rs = null;
	    Clocking v = null;
	    ArrayList<Clocking> vList = new ArrayList<>();
	    try {
	        connection = Main.getConnection();
	        String sql = "SELECT CLOCKING_ID, CLOCKING_POINT_NAME, SITE_NAME, CREATED_DT, LAST_MODIFIED_DT, CREATEDBY, LASTMODIFIEDBY " +
	                     "FROM CLOCKING ORDER BY LAST_MODIFIED_DT DESC;";
	        pstmt = connection.prepareStatement(sql);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	            v = new Clocking(
	                rs.getString(1),
	                rs.getString(2),
	                rs.getString(3),
	                rs.getString(6),
	                rs.getTimestamp(4),
	                rs.getTimestamp(5),
	                rs.getString(7)
	            );
	            vList.add(v);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        Main.close(connection, pstmt, rs);
	    }
	    return vList;
	}

	public static ArrayList<Clocking> retrieveAllCurrentDay(Timestamp timestamp) {
	    PreparedStatement pstmt = null;
	    Connection connection = null;
	    ResultSet rs = null;
	    Clocking v = null;
	    ArrayList<Clocking> vList = new ArrayList<>();
	    try {
	        connection = Main.getConnection();
	        String sql = "SELECT CLOCKING_ID, CLOCKING_POINT_NAME, SITE_NAME, CREATED_DT, LAST_MODIFIED_DT, CREATEDBY, LASTMODIFIEDBY " +
	                     "FROM CLOCKING WHERE DATE(CREATED_DT) = DATE(CAST(? AS TIMESTAMP)) ORDER BY CREATED_DT DESC;";
	        pstmt = connection.prepareStatement(sql);
	        pstmt.setTimestamp(1, timestamp);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	            v = new Clocking(
	                rs.getString(1),
	                rs.getString(2),
	                rs.getString(3),
	                rs.getString(6),
	                rs.getTimestamp(4),
	                rs.getTimestamp(5),
	                rs.getString(7)
	            );
	            vList.add(v);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        Main.close(connection, pstmt, rs);
	    }
	    return vList;
	}

	public static ArrayList<Clocking> retrieveAllLast10Days(Timestamp timestamp) {
	    PreparedStatement pstmt = null;
	    Connection connection = null;
	    ResultSet rs = null;
	    Clocking v = null;
	    ArrayList<Clocking> vList = new ArrayList<>();
	    try {
	        connection = Main.getConnection();
	        String sql = "SELECT CLOCKING_ID, CLOCKING_POINT_NAME, SITE_NAME, CREATED_DT, LAST_MODIFIED_DT, CREATEDBY, LASTMODIFIEDBY " +
	                     "FROM CLOCKING WHERE DATE(CREATED_DT) >= DATE(CAST(? AS TIMESTAMP)) - INTERVAL '10 days' ORDER BY CREATED_DT DESC;";
	        pstmt = connection.prepareStatement(sql);
	        pstmt.setTimestamp(1, timestamp);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	            v = new Clocking(
	                rs.getString(1),
	                rs.getString(2),
	                rs.getString(3),
	                rs.getString(6),
	                rs.getTimestamp(4),
	                rs.getTimestamp(5),
	                rs.getString(7)
	            );
	            vList.add(v);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        Main.close(connection, pstmt, rs);
	    }
	    return vList;
	}

	public static ArrayList<Clocking> retrieveAllLast30Days(Timestamp timestamp) {
	    PreparedStatement pstmt = null;
	    Connection connection = null;
	    ResultSet rs = null;
	    Clocking v = null;
	    ArrayList<Clocking> vList = new ArrayList<>();
	    try {
	        connection = Main.getConnection();
	        String sql = "SELECT CLOCKING_ID, CLOCKING_POINT_NAME, SITE_NAME, CREATED_DT, LAST_MODIFIED_DT, CREATEDBY, LASTMODIFIEDBY " +
	                     "FROM CLOCKING WHERE DATE(CREATED_DT) >= DATE(CAST(? AS TIMESTAMP)) - INTERVAL '30 days' ORDER BY CREATED_DT DESC;";
	        pstmt = connection.prepareStatement(sql);
	        pstmt.setTimestamp(1, timestamp);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	            v = new Clocking(
	                rs.getString(1),
	                rs.getString(2),
	                rs.getString(3),
	                rs.getString(6),
	                rs.getTimestamp(4),
	                rs.getTimestamp(5),
	                rs.getString(7)
	            );
	            vList.add(v);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        Main.close(connection, pstmt, rs);
	    }
	    return vList;
	}

	public static ArrayList<Clocking> retrieveClockingById(String clockingId) {
	    PreparedStatement pstmt = null;
	    Connection connection = null;
	    ResultSet rs = null;
	    Clocking v = null;
	    ArrayList<Clocking> vList = new ArrayList<>();
	    try {
	        connection = Main.getConnection();
	        String sql = "SELECT CLOCKING_ID, CLOCKING_POINT_NAME, SITE_NAME, CREATED_DT, LAST_MODIFIED_DT, CREATEDBY, LASTMODIFIEDBY " +
	                     "FROM CLOCKING WHERE CLOCKING_ID = ? ORDER BY LAST_MODIFIED_DT DESC;";
	        pstmt = connection.prepareStatement(sql);
	        pstmt.setString(1, clockingId);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	            v = new Clocking(
	                rs.getString(1),
	                rs.getString(2),
	                rs.getString(3),
	                rs.getString(6),
	                rs.getTimestamp(4),
	                rs.getTimestamp(5),
	                rs.getString(7)
	            );
	            vList.add(v);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        Main.close(connection, pstmt, rs);
	    }
	    return vList;
	}

	
	public static String deleteClockingById(String clockingId) {
		PreparedStatement pstmt = null;
		Connection connection = null;
		String message = "Record deleted";
		try {
			connection = Main.getConnection();
			String sql = "DELETE FROM CLOCKING WHERE CLOCKING_ID = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, clockingId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			message = "Error: " + e.getMessage();
		} finally {
			Main.close(connection, pstmt, null);
		}
		return message;
	}

	
	public static String deleteAll() {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        String message = "All records deleted - No clocking records available";
        try {
        	connection = Main.getConnection();
            String sql = "DELETE FROM CLOCKING WHERE LAST_MODIFIED_DT <= GETDATE() - 30;";
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
