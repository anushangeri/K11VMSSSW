package net.javatutorial.tutorials;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.DropdownListManagerDAO;

/**
 * Servlet implementation class DeleteDropdownbyDropdownIdServlet
 * Deletes dropdown using dropdown id
 */
public class DeleteDropdownbyDropdownIdServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dropdownId = request.getParameter("dropdownId");
		String message = DropdownListManagerDAO.deleteByDropdownId(dropdownId);
		
		request.setAttribute("message", message);
		response.sendRedirect("/retrieveAllDropdownRecords");
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
