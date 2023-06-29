package net.javatutorial.tutorials;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.SiteManagerDAO;;

/**
 * Servlet implementation class DeleteSiteServlet
 * Deletes site using site id
 */
public class DeleteSiteServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String siteId = request.getParameter("siteId");
		String message = SiteManagerDAO.deleteBySiteId(siteId);
		
		request.setAttribute("message", message);
		response.sendRedirect("/retrieveAllSiteRecords");
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
