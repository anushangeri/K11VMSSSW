package net.javatutorial.DAO;

import java.net.URISyntaxException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import net.javatutorial.entity.ClientAccount;
import net.javatutorial.tutorials.Main;

public class ClientAccountManagerDAO {
	
	public static String addClientAccount(ClientAccount v){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String message = "Error occurred when adding client.";
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();
			Array arraySites = connection.createArrayOf("text", v.getSite());
	        stmt.executeUpdate("INSERT INTO CLIENTACCOUNT "
	        		+  "(ACCOUNT_ID, NAME, SITE, ID_TYPE, ID_NO, PASSWORD, SALT, ACCESS_TYPE, CREATED_DT, MODIFIED_DT)" + 
	        		"   VALUES ('" +v.getAccountId()+ "','" +v.getName()+ "','" +arraySites+ "','" +v.getIdType()+ "','" 
	        		+v.getIdNo()+ "','" +v.getPassword()+ "','" +v.getSalt()+ "','" +v.getAccessType()+ "','" +v.getCreatedDt()+ "','" +v.getModifiedDt()+"')");
	        rs = stmt.executeQuery("SELECT MAX(account_id) FROM CLIENTACCOUNT;");
	        while (rs.next()) {
	        	message = "Successfully Added Client Account.";
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
	public static String updateClientAccountPassword(ClientAccount v){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		String message = "";
		try {
			connection = Main.getConnection();
			stmt = connection.createStatement();

	        stmt.executeUpdate("SET TIMEZONE = 'Singapore'; "
	        		+ "UPDATE CLIENTACCOUNT "
	        		+  "SET MODIFIED_DT = NOW(),"
	        		+ "PASSWORD = '" +v.getPassword()+"',"
	        		+ "SALT = '" +v.getSalt()+"'" +
	        		"   WHERE ACCOUNT_ID = '" + v.getAccountId() + "';");
	        rs = stmt.executeQuery("SELECT LAST(NAME) FROM CLIENTACCOUNT WHERE ACCOUNT_ID ='" + v.getAccountId() +"';");
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
	        rs = stmt.executeQuery("SELECT MAX(CAST(ACCOUNT_ID AS INT)) FROM CLIENTACCOUNT;");
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

	public static ArrayList<ClientAccount> retrieveByNameIDandPassword(String idNo, String password) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        ClientAccount v = null;
        ArrayList<ClientAccount> vList = new ArrayList<ClientAccount>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT ACCOUNT_ID, NAME, SITE, \r\n" + 
            		"              ID_TYPE, ID_NO, PASSWORD, SALT, ACCESS_TYPE, CREATED_DT, MODIFIED_DT \r\n"
            		+ " FROM CLIENTACCOUNT "
            		+ " WHERE ID_NO ='" + idNo + "' AND PASSWORD = '" +password+"' \r\n"
    				+ " ORDER BY MODIFIED_DT DESC";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	Array a = rs.getArray(3);
            	v = new ClientAccount(rs.getString(1), 
            			rs.getString(2),
            			(String[]) a.getArray(),
            			rs.getString(4),
            			rs.getString(5),
            			rs.getString(6),
            			rs.getString(7),
            			rs.getString(8),
            			rs.getTimestamp(9),
            			rs.getTimestamp(10));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }

	public static ArrayList<ClientAccount> retrieveByID(String idNo) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        ClientAccount v = null;
        ArrayList<ClientAccount> vList = new ArrayList<ClientAccount>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT ACCOUNT_ID, NAME, SITE, \r\n" + 
            		"              ID_TYPE, ID_NO, PASSWORD, SALT, ACCESS_TYPE, CREATED_DT, MODIFIED_DT \r\n"
            		+ " FROM CLIENTACCOUNT "
            		+ " WHERE ID_NO ='" + idNo + "' \r\n"
    				+ " ORDER BY MODIFIED_DT DESC";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	Array a = rs.getArray(3);
            	v = new ClientAccount(rs.getString(1), 
            			rs.getString(2),
            			(String[]) a.getArray(),
            			rs.getString(4),
            			rs.getString(5),
            			rs.getString(6),
            			rs.getString(7),
            			rs.getString(8),
            			rs.getTimestamp(9),
            			rs.getTimestamp(10));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static ArrayList<ClientAccount> retrieveAll() {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        ClientAccount v = null;
        ArrayList<ClientAccount> vList = new ArrayList<ClientAccount>();
        try {
        	connection = Main.getConnection();
            String sql = "SELECT ACCOUNT_ID, NAME, SITE, \r\n" + 
            		"              ID_TYPE, ID_NO, PASSWORD, SALT, ACCESS_TYPE, CREATED_DT, MODIFIED_DT \r\n"
            		+ " FROM CLIENTACCOUNT "
    				+ " ORDER BY MODIFIED_DT DESC";
            pstmt = connection.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
            	Array a = rs.getArray(3);
            	v = new ClientAccount(rs.getString(1), 
            			rs.getString(2),
            			(String[])a.getArray(),
            			rs.getString(4),
            			rs.getString(5),
            			rs.getString(6),
            			rs.getString(7),
            			rs.getString(8),
            			rs.getTimestamp(9),
            			rs.getTimestamp(10));
                vList.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	Main.close(connection, pstmt, rs);
        }
        return vList;
    }
	
	public static String deleteRecordByAccountId(String accountId) {
        PreparedStatement pstmt = null;
        Connection connection = null;
        ResultSet rs = null;
        String message = "All records deleted - No client account records available";
        try {
        	connection = Main.getConnection();
            String sql = "DELETE FROM CLIENTACCOUNT WHERE ACCOUNT_ID ='" + accountId + "'";
            message = "Successful";
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
        String message = "All records deleted - No client account records available";
        try {
        	connection = Main.getConnection();
            String sql = "DELETE FROM CLIENTACCOUNT WHERE CREATED_DT <= GETDATE() - 30;";
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
