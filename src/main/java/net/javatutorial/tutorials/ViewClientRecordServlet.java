package net.javatutorial.tutorials;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import net.javatutorial.DAO.ClientAccountManagerDAO;
import net.javatutorial.entity.ClientAccount;

/**
 * Servlet implementation class ViewVehicleRecordServlet
 */
public class ViewClientRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usertype = (String) request.getSession(false).getAttribute("usertype");
		String idNo = (String) request.getSession(false).getAttribute("idNo");
		String name = (String) request.getSession(false).getAttribute("name");
		String message = "No client accounts available";
		ArrayList<ClientAccount> vList = null;
		if(usertype != null && usertype.toUpperCase().equals("ADMIN")) {
			vList = ClientAccountManagerDAO.retrieveAll();
			message = "List of client accounts";
			request.setAttribute("vList", vList);
		}
		
		request.setAttribute("message", message);
        RequestDispatcher rd = request.getRequestDispatcher("clientManager.jsp");
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
