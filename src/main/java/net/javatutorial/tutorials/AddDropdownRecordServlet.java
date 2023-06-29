package net.javatutorial.tutorials;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.javatutorial.DAO.DropdownListManagerDAO;
import net.javatutorial.DAO.SiteManagerDAO;
import net.javatutorial.entity.Dropdown;


/**
 * Servlet implementation class AddDropdownListRecordServlet
 */
public class AddDropdownRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nextVal = DropdownListManagerDAO.getNextVal();
		
		String dropdownId = "" + nextVal;
		String dropdownKey = request.getParameter("dropdownKey");
		String dropdownValue = request.getParameter("dropdownValue");
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());

				
		Dropdown v = new Dropdown( dropdownId, dropdownKey, dropdownValue, timestamp, timestamp);
		
		String message = DropdownListManagerDAO.addDropdown(v);
		
		
		request.setAttribute("responseObj", message);
		// Redirect to view site servlet to query all the site again.
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
