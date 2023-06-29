package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.javatutorial.entity.Dropdown;
import net.javatutorial.tutorials.Main;

public class DropdownListManagerDAO {
	
	public static String addDropdown(Dropdown v){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();

	        stmt.executeUpdate("INSERT INTO DROPDOWN "
	        		+ "(DROPDOWN_ID, DROPDOWN_KEY, DROPDOWN_VALUE, CREATED_DT, LAST_MODIFIED_DT)" + 
	        		"  VALUES ('" +v.getDropdownId()+ "','" +v.getDropdownKey()+ "','" +v.getDropdownValue()+ "','" 
	        		+v.getCreatedDt()+ "','" +v.getLastModifiedDt()+ "');");
	        rs = stmt.executeQuery("SELECT DROPDOWN_ID FROM DROPDOWN WHERE DROPDOWN_ID = "+ "'" +v.getDropdownId()+ "';");
	        while (rs.next()) {
	        	message = "Read from DB: " + rs.getTimestamp("tick");
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
	
	public static String updateDropdownList(Dropdown v){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();

	        stmt.executeUpdate("SET TIMEZONE = 'Singapore'; "
	        		+ "UPDATE DROPDOWN "
	        		+  "SET DROPDOWN_KEY = '" + v.getDropdownKey() + "', "
	        		+  "DROPDOWN_VALUE = '" + v.getDropdownValue() + "', "
	        		+  "LAST_MODIFIED_DT = NOW() "
	        		+ "   WHERE DROPDOWN_ID = '" + v.getDropdownId() + "';");
	        rs = stmt.executeQuery("SELECT DROPDOWN_ID FROM DROPDOWN WHERE DROPDOWN_ID ='" + v.getDropdownId() +"';");
	        while (rs.next()) {
	        	message = "Read from DB: " + rs.getTimestamp("tick");
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
	
	public static int getNextVal(){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		int result = 1;
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();
//	        stmt.executeUpdate("SELECT count(*) FROM EMPLOYEES;");
	        rs = stmt.executeQuery("SELECT MAX(CAST(DROPDOWN_ID AS INTEGER)) FROM DROPDOWN;");
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
	
	public static ArrayList<Dropdown> retrieveAll() {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        Dropdown v = null;
        ArrayList<Dropdown> vList = new ArrayList<Dropdown>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT DROPDOWN_ID, DROPDOWN_KEY, DROPDOWN_VALUE, CREATED_DT, LAST_MODIFIED_DT \r\n"
            		+ "FROM DROPDOWN ORDER BY LAST_MODIFIED_DT DESC; ";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	v = new Dropdown(rs.getString(1), 
            			rs.getString(2),
            			rs.getString(3),
            			rs.getTimestamp(4),
            			rs.getTimestamp(5));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static ArrayList<Dropdown> retrieveByDropdownId(String dropdownId) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        Dropdown v = null;
        ArrayList<Dropdown> vList = new ArrayList<Dropdown>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT DROPDOWN_ID, DROPDOWN_KEY, DROPDOWN_VALUE, CREATED_DT, LAST_MODIFIED_DT \r\n" + 
            		"FROM DROPDOWN \r\n"
            		+ " WHERE DROPDOWN_ID ='" + dropdownId + "' ORDER BY LAST_MODIFIED_DT DESC;";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	v = new Dropdown(rs.getString(1), 
            			rs.getString(2),
            			rs.getString(3),
            			rs.getTimestamp(4),
            			rs.getTimestamp(5));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static ArrayList<Dropdown> retrieveByDropdownKey(String dropdownKey) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        Dropdown v = null;
        ArrayList<Dropdown> vList = new ArrayList<Dropdown>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT DROPDOWN_ID, DROPDOWN_KEY, DROPDOWN_VALUE, CREATED_DT, LAST_MODIFIED_DT \r\n" + 
            		"FROM DROPDOWN \r\n"
            		+ " WHERE DROPDOWN_KEY ='" + dropdownKey + "' ORDER BY DROPDOWN_VALUE ASC;";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	v = new Dropdown(rs.getString(1), 
            			rs.getString(2),
            			rs.getString(3),
            			rs.getTimestamp(4),
            			rs.getTimestamp(5));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static String deleteByDropdownId(String dropdownId) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        String message = "All records deleted - No site records available";
        try {
        	connection = Main.getConnection();
            String sql = "DELETE FROM DROPDOWN WHERE DROPDOWN_ID ='" + dropdownId + "'";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return message;
    }
	
	public static String deleteAll() {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        String message = "All records deleted - No dropdown records available";
        try {
        	connection = Main.getConnection();
            String sql = "DELETE FROM DROPDOWN WHERE LAST_MODIFIED_DT <= GETDATE() - 30;";
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
