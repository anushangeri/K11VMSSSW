package net.javatutorial.tutorials;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleServlet extends HttpServlet {

	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("nricfin");
//		String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        username = username.trim();
//        password = password.trim();
//
//        if (username.equals("admin")) {
//            if (password.equals("blackyellow")) {
//                HttpSession session = request.getSession();
//                session.setAttribute("authenticatedUser", username);
//                response.sendRedirect("admin");
//            } else {
//                request.setAttribute("msg", "Invalid Username/Password");
//                RequestDispatcher rd = request.getRequestDispatcher("LoginUI.jsp");
//                rd.forward(request, response);
//            }
//        }
		String responseObj = "Hello test change" + name;
		Connection connection;
		try {
			connection = Main.getConnection();
			Statement stmt = connection.createStatement();
//	        stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
//	        stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
	        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
	        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
	        while (rs.next()) {
	        	responseObj = responseObj + "Read from DB: " + rs.getTimestamp("tick");
	        }
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			responseObj = responseObj + "Read from DB: " + e;
			//e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			responseObj = responseObj + "Read from DB: " + e;
		}
        
        
		
		request.setAttribute("responseObj", responseObj);
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
	}
	@Override
	public void init() throws ServletException {
		System.out.println("Servlet " + this.getServletName() + " has started");
	}

	@Override
	public void destroy() {
		System.out.println("Servlet " + this.getServletName() + " has stopped");
	}
	
}
