package net.javatutorial.tutorials;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.DropdownListManagerDAO;
import net.javatutorial.DAO.SiteManagerDAO;
import net.javatutorial.entity.Dropdown;
import net.javatutorial.entity.Site;

/**
 * Servlet implementation class RetrieveDropdownForAddClientAccountFormServlet
 * To retrieve the relevant dropdown lists for form in addClientAccount.jsp
 * We used to use google sheets - everything is being migrated to heroku db since Nov 2021
 */
public class RetrieveDropdownForAddClientAccountFormServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Site> siteDropdown = SiteManagerDAO.retrieveAll();
		ArrayList<Dropdown> accessType = DropdownListManagerDAO.retrieveByDropdownKey("ACCESS_TYPE");
		ArrayList<Dropdown> idType = DropdownListManagerDAO.retrieveByDropdownKey("ID_TYPE");
		
		request.setAttribute("siteDropdown", siteDropdown);
		request.setAttribute("accessType", accessType);
		request.setAttribute("idType", idType);
        RequestDispatcher rd = request.getRequestDispatcher("addClientAccount.jsp");
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
