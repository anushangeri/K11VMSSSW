package net.javatutorial.tutorials;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

/**
 * This servlet checks if idType and idNumber matches K11 Staff or K11 Admin
 */
public class VMSCheckNRICServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		ArrayList<String> responseObj = new ArrayList<String>();
		RequestDispatcher rd = null;
		
		String name = request.getParameter("name").trim();
		String idNo = request.getParameter("idNo").trim();
		String recordType = request.getParameter("recordType");
		
		try {
			//make idNo uppercase
        	if(idNo != null && !idNo.isEmpty() ){
        		idNo = idNo.toUpperCase();
        	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		boolean loginsuccessful = false;
				
		if(!StringUtils.isEmpty(idNo)) {
			loginsuccessful = true;
			session.setAttribute("name", name);
        	session.setAttribute("idNo", idNo);
		}
		if(loginsuccessful) {
			responseObj.add("Login successful.");
			request.setAttribute("responseObj", responseObj);
			// Redirect to view visitor servlet to query all the visitors again.
			if(recordType.equals("visitorRecord")) {
				response.sendRedirect("/vms");
			}
			else if(recordType.equals("vehicleRecord")){
				response.sendRedirect("/vehms");
			}
			else{
				responseObj.add("NRIC/FIN is invalid. Please try again: " + idNo);
				request.setAttribute("responseObj", responseObj);
				rd = request.getRequestDispatcher("vmsCheckNRIC.jsp");
				rd.forward(request, response);
			}
				
		}
		else{
			responseObj.add("NRIC/FIN is invalid. Please try again: " + idNo);
			request.setAttribute("responseObj", responseObj);
			rd = request.getRequestDispatcher("vmsCheckNRIC.jsp");
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

