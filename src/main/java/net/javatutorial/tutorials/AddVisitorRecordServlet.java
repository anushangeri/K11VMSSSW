package net.javatutorial.tutorials;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import net.javatutorial.DAO.VMSManagerDAO;
import net.javatutorial.entity.Visitor;

/**
 * Servlet implementation class AddEmployeeServlet
 */
public class AddVisitorRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nextVal = VMSManagerDAO.getNextVal();
		
		String vmsId = "" + nextVal;
		String name = request.getParameter("name").trim();
		String companyName = request.getParameter("companyName").trim();
		String site = request.getParameter("siteVisiting").trim();
		String idType = null;
		String idNo = request.getParameter("idNo");
		String mobileNo = request.getParameter("processedMobileNo");
		String vehicleNo = request.getParameter("vehicleNo");
		String hostName = request.getParameter("hostName");
		String hostNo = request.getParameter("processedHostNo");
		String visitorCardId = request.getParameter("visitorCardId");
		String covidDec = "";
		String visitPurpose = request.getParameter("visitPurpose");
		String temperature = "";
		String remarks = request.getParameter("remarks");
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());

		String createdBy = (String) request.getSession(false).getAttribute("idNo");
		
		//Step 1: verify officer login (if parameters not empty) and visitPurpose = GovtAgency
		String officerIdNo = request.getParameter("officerIdNo");
		
		Visitor v = null;
		String message = "Something went wrong or OTP was wrong, please try again.";
		if(officerIdNo != null && !StringUtils.isEmpty(officerIdNo) && visitPurpose.equals("GOVERNMENT AGENCY")) {
			//Step 2: add visitor
			v = new Visitor( vmsId,  name,  companyName, site, idType, idNo,  mobileNo,  vehicleNo,
					 hostName,  hostNo,  visitorCardId, covidDec, remarks, visitPurpose,  
					 temperature, officerIdNo , timestamp, createdBy, timestamp, createdBy, timestamp);
			message = VMSManagerDAO.addVisitor(v);
		}
		else if(!visitPurpose.equals("GOVERNMENT AGENCY")) {
			//Step 2: add visitor if not GOVT AGENCY
			v = new Visitor( vmsId,  name,  companyName, site, idType, idNo,  mobileNo,  vehicleNo,
					 hostName,  hostNo,  visitorCardId, covidDec, remarks, visitPurpose,  
					 temperature, null , timestamp, createdBy, timestamp, createdBy, timestamp);
			message = VMSManagerDAO.addVisitor(v);
		}
		request.setAttribute("message", message);
		// Redirect to view visitor servlet to query all the visitors again.
		response.sendRedirect("/vms");
		
		
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
