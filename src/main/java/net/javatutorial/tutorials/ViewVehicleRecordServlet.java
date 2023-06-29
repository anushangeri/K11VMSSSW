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

import org.apache.commons.lang3.StringUtils;

import net.javatutorial.DAO.VehMSManagerDAO;
import net.javatutorial.entity.Vehicle;

/**
 * Servlet implementation class ViewVehicleRecordServlet
 */
public class ViewVehicleRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usertype = (String) request.getSession(false).getAttribute("usertype");
		String idNo = (String) request.getSession(false).getAttribute("idNo");
		String name = (String) request.getSession(false).getAttribute("name");
		String[] siteInCharge = (String[]) request.getSession(false).getAttribute("siteInCharge");
		String recordsToReceive = (String) request.getParameter("recordsToReceive");
		
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());
		
		String message = "No vehicle / gate pass records available for: " + name;
		ArrayList<Vehicle> vList = null;
		if(!StringUtils.isEmpty(idNo)) {
			if(!StringUtils.isEmpty(usertype) && usertype != null
					&& (usertype.equals("ADMIN") || usertype.equals("OFFICER") || usertype.equals("MANAGEMENT"))) {
				if((StringUtils.isEmpty(recordsToReceive) || recordsToReceive == null) || recordsToReceive.equals("currdate")) {
					vList = VehMSManagerDAO.retrieveAllCurrentDay(timestamp);
					System.out.println(timestamp + " : " + vList.toString());
					message = "List of vehicle / gate pass records";
					request.setAttribute("vList", vList);
				}
				else if(!(StringUtils.isEmpty(recordsToReceive) || recordsToReceive == null) && recordsToReceive.equals("10days") ) {
					vList = VehMSManagerDAO.retrieveAllLast10Days(timestamp);
					System.out.println(timestamp + " : " + vList.toString());
					message = "List of vehicle / gate pass records";
					request.setAttribute("vList", vList);
				}
				else if(!(StringUtils.isEmpty(recordsToReceive) || recordsToReceive == null) && recordsToReceive.equals("all") ) {
					vList = VehMSManagerDAO.retrieveAll();
					System.out.println(timestamp + " : " + vList.toString());
					message = "List of vehicle / gate pass records";
					request.setAttribute("vList", vList);
				}
				else {
					vList = VehMSManagerDAO.retrieveAll();
					message = "List of vehicle / gate pass records";
					request.setAttribute("vList", vList);
				}
				if(vList == null || vList.size() == 0) {
					message = "No vehicle / gate pass records available";
				}
			}
			else if(!StringUtils.isEmpty(usertype) && usertype != null && siteInCharge != null && siteInCharge.length > 0
					&& (usertype.equals("CLIENT") || usertype.equals("WAREHOUSE"))) {
				vList = VehMSManagerDAO.retrieveBySite(siteInCharge);
				message = "List of vehicle / gate pass records";
				request.setAttribute("vList", vList);
				if(vList == null || vList.size() == 0) {
					message = "No vehicle / gate pass records available";
				}
			}
			else{
				vList = VehMSManagerDAO.retrieveByNRIC(idNo);
				message = "List of vehicle / gate pass records for " + name;
				request.setAttribute("vList", vList);
				if(vList == null || vList.size() == 0) {
					message = "No vehicle / gate pass records available for " + name;
				}
			}
		}
		
		request.setAttribute("message", message);
        RequestDispatcher rd = request.getRequestDispatcher("vehms.jsp");
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
