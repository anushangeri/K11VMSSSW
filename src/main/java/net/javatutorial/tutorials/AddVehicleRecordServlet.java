package net.javatutorial.tutorials;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javatutorial.DAO.VehMSManagerDAO;
import net.javatutorial.entity.Vehicle;

/**
 * Servlet implementation class AddEmployeeServlet
 */
public class AddVehicleRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nextVal = VehMSManagerDAO.getNextVal();
		
		String vehicleId = "" + nextVal;
		String name = request.getParameter("name").trim();
		String companyName = request.getParameter("companyName").trim();
		String idType = null;
		String idNo = request.getParameter("idNo");
		String mobileNo = request.getParameter("processedMobileNo");
		String primeMoverNo = request.getParameter("primeMoverNo");
		String containerNo = request.getParameter("containerNo");
		String loadedNoLoaded = request.getParameter("loadedNoLoaded");
		//String covidDec = request.getParameter("coviddeclaration");
		String lorryChetNumber = request.getParameter("lorryChetNumber");
		String deliveryNoticeNumber = request.getParameter("deliveryNoticeNumber");
		String visitPurpose = request.getParameter("visitPurpose");
		//String temperature = request.getParameter("temperature");
		String sealNo = request.getParameter("sealNo");
		String containerSize = request.getParameter("containerSize");
		String remarks = request.getParameter("remarks");
		String site = request.getParameter("site"); // aka warehouse name
		
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());

		String createdBy = (String) request.getSession(false).getAttribute("idNo");
		
		Vehicle v = new Vehicle( vehicleId,  name,  companyName, idType, idNo,  mobileNo,  primeMoverNo,
				containerNo,  loadedNoLoaded, null, lorryChetNumber, deliveryNoticeNumber,  
				visitPurpose, null, sealNo, containerSize,  remarks , 0, site, timestamp, createdBy, timestamp, createdBy, timestamp);

		String message = VehMSManagerDAO.addVisitor(v);
		
		ArrayList<String> responseObj = new ArrayList<String>();
		responseObj.add(message + " " + name);
		request.setAttribute("responseObj", responseObj);
		// Redirect to view vehicle servlet to query all the vehicle again.
		response.sendRedirect("/vehms");
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
