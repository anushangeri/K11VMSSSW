package net.javatutorial.tutorials;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.ClientAccountManagerDAO;
import net.javatutorial.DAO.ClientAccountTblDAO;
import net.javatutorial.entity.ClientAccount;

/**
 * Servlet implementation class ManageTblServlet
 * To create table Vehicle
 */
public class CClientAccountTblServlet extends HttpServlet {
	
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String responseObj = ClientAccountTblDAO.createClientAccountTbl();
		
		String accountId = "1";
		String name = "K11ADMIN";
		String[] site = {};
		String idType = "NRIC";
		String idNo = "K11ADMIN";
		String password= "P@ssw0rd";
		String accessType= "ADMIN";
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());

		//hashing the password
		String salt = PasswordUtils.generateSalt(512).get();
		String hashedPassword = PasswordUtils.hashPassword(password, salt).get();
				
		ClientAccount v = new ClientAccount( accountId,  name, site, idType, idNo,  hashedPassword, salt, accessType, timestamp, timestamp);
		
		responseObj = ClientAccountManagerDAO.addClientAccount(v);
		
		request.setAttribute("responseObj", responseObj);
        RequestDispatcher rd = request.getRequestDispatcher("managedatabase.jsp");
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
