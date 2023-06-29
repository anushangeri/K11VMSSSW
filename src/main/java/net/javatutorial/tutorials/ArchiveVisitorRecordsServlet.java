package net.javatutorial.tutorials;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.VMSArchiveManagerDAO;

/**
 * Servlet implementation class ArchiveVisitorRecordsServlet
 */
public class ArchiveVisitorRecordsServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message = VMSArchiveManagerDAO.moveVisitor();
//		request.setAttribute("responseObj", message);
//		// Redirect to view visitor servlet to query all the visitors again.
//		response.sendRedirect("index.jsp");
		
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
