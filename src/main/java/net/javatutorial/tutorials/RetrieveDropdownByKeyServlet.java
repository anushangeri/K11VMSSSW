package net.javatutorial.tutorials;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.DropdownListManagerDAO;
import net.javatutorial.entity.Dropdown;

/**
 * Servlet implementation class RetrieveDropdownByKeyServlet
 * To retrieve latest dropdown for display based on key
 */
public class RetrieveDropdownByKeyServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dropdownKey = request.getParameter("dropdownKey");
				
		ArrayList<Dropdown> vList = null;
		RequestDispatcher rd = null;
		
		if(dropdownKey == null) {
			request.setAttribute("message", "error in retrieve dropdown for: " + dropdownKey);
	        rd = request.getRequestDispatcher("index.jsp");
		}
		else {
			vList = DropdownListManagerDAO.retrieveByDropdownKey(dropdownKey);
			request.setAttribute("dropdownList", vList);
	        rd = request.getRequestDispatcher("vmsCheckNRIC.jsp");
		}
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
