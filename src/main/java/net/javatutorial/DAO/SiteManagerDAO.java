package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.javatutorial.entity.Site;
import net.javatutorial.tutorials.Main;

public class SiteManagerDAO {
	
	public static String addSite(Site v){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();

	        stmt.executeUpdate("INSERT INTO SITE "
	        		+ "(SITE_ID, SITE_NAME, COMPANY_NAME, CREATED_DT, LAST_MODIFIED_DT)" + 
	        		"  VALUES ('" +v.getSiteId()+ "','" +v.getSiteName()+ "','" +v.getCompanyName()+ "','" 
	        		+v.getCreatedDt()+ "','" +v.getLastModifiedDt()+ "');");
	        rs = stmt.executeQuery("SELECT LAST(NAME) FROM SITE;");
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
	
	public static String updateSiteDetails(Site v){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();

	        stmt.executeUpdate("SET TIMEZONE = 'Singapore'; "
	        		+ "UPDATE SITE "
	        		+  "SET SITE_NAME = '" + v.getSiteName() + "', "
	        		+  "COMPANY_NAME = '" + v.getCompanyName() + "', "
	        		+  "LAST_MODIFIED_DT = NOW() "
	        		+ "   WHERE SITE_ID = '" + v.getSiteId() + "';");
	        rs = stmt.executeQuery("SELECT SITE_NAME FROM SITE WHERE SITE_ID ='" + v.getSiteId() +"';");
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
	        rs = stmt.executeQuery("SELECT MAX(CAST(SITE_ID AS INTEGER)) FROM SITE;");
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
	
	public static ArrayList<Site> retrieveAll() {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        Site v = null;
        ArrayList<Site> vList = new ArrayList<Site>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT SITE_ID, SITE_NAME, COMPANY_NAME, CREATED_DT, LAST_MODIFIED_DT \r\n"
            		+ "FROM SITE ORDER BY LAST_MODIFIED_DT DESC; ";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	v = new Site(rs.getString(1), 
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
	
	public static ArrayList<Site> retrieveBySiteId(String siteId) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        Site v = null;
        ArrayList<Site> vList = new ArrayList<Site>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT SITE_ID, SITE_NAME, COMPANY_NAME, CREATED_DT, LAST_MODIFIED_DT \r\n" + 
            		"FROM SITE \r\n"
            		+ " WHERE SITE_ID ='" + siteId + "' ORDER BY LAST_MODIFIED_DT DESC;";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	v = new Site(rs.getString(1), 
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
	
	public static String deleteBySiteId(String siteId) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        String message = "All records deleted - No site records available";
        try {
        	connection = Main.getConnection();
            String sql = "DELETE FROM SITE WHERE SITE_ID ='" + siteId + "'";
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
        String message = "All records deleted - No site records available";
        try {
        	connection = Main.getConnection();
            String sql = "DELETE FROM SITE WHERE LAST_MODIFIED_DT <= GETDATE() - 30;";
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
