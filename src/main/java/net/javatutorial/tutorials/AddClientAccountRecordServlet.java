package net.javatutorial.tutorials;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.ClientAccountManagerDAO;
import net.javatutorial.entity.ClientAccount;

/**
 * Servlet implementation class AddClientAccountServlet
 */
public class AddClientAccountRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nextVal = ClientAccountManagerDAO.getNextVal();
		
		String accountId = "" + nextVal;
		String name = request.getParameter("name").trim();
		String[] site = request.getParameterValues("sites[]");
		String idType = request.getParameter("idType");
		String idNo = request.getParameter("idNo");
		String password= request.getParameter("psw");
		String accessType= request.getParameter("accessType");
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());
		
		ArrayList<ClientAccount> vList = ClientAccountManagerDAO.retrieveByID(idNo);
		String message = "This user already exists. Please verify.";
		if(vList.size() == 0 ) {
			//hashing the password
			String salt = PasswordUtils.generateSalt(512).get();
			String hashedPassword = PasswordUtils.hashPassword(password, salt).get();
					
			ClientAccount v = new ClientAccount( accountId,  name, site, idType, idNo,  hashedPassword, salt, accessType, timestamp, timestamp);
			
			message = ClientAccountManagerDAO.addClientAccount(v);
			
			request.setAttribute("responseObj", message);
			response.sendRedirect("/retrieveAllClientRecords");
		}
		else {
			request.setAttribute("responseObj", message);
			RequestDispatcher rd = request.getRequestDispatcher("clientMain.jsp");
	        rd.forward(request, response);
		}
		
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
