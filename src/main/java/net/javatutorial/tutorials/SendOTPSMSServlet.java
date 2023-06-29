package net.javatutorial.tutorials;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendOTPSMSServlet
 */
public class SendOTPSMSServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


//		int nextVal = 1; // this value does not matter since we are not adding visitor yet
//
//		String vmsId = "" + nextVal;
//		String name = request.getParameter("name").trim();
//		String companyName = request.getParameter("companyName").trim();
//		String site = request.getParameter("siteVisiting").trim();
//		String idType = null;
//		String idNo = request.getParameter("idNo");
		String mobileNo = request.getParameter("processedMobileNo").trim();
//		String vehicleNo = request.getParameter("vehicleNo");
//		String hostName = request.getParameter("hostName");
//		String hostNo = request.getParameter("hostNo");
//		String visitorCardId = request.getParameter("visitorCardId");
//		String covidDec = "";
//		String visitPurpose = request.getParameter("visitPurpose");
//		String temperature = "";
//		String remarks = request.getParameter("remarks");
//		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore"));
//		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());
//
//		String officerIdNo = request.getParameter("officerIdNo");

		String otpGenerated = request.getParameter("otpGenerated");
		sendOTP(mobileNo, otpGenerated);

//		Visitor v = new Visitor(vmsId, name, companyName, site, idType, idNo, mobileNo, vehicleNo, hostName, hostNo,
//				visitorCardId, covidDec, remarks, visitPurpose, temperature, officerIdNo, timestamp);

//		ArrayList<Site> siteDropdown = SiteManagerDAO.retrieveAll();
//		ArrayList<Dropdown> visitPurposes = DropdownListManagerDAO.retrieveByDropdownKey("VISIT_PURPOSE");

//		request.setAttribute("visitorLatRec", v);
//		request.setAttribute("siteDropdown", siteDropdown);
//		request.setAttribute("visitPurpose", visitPurposes);
		request.setAttribute("otpGenerated", otpGenerated);
		RequestDispatcher rd = request.getRequestDispatcher("addVisitor.jsp");
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

	@SuppressWarnings({ "unused", "deprecation" })
	public static void sendOTP(String mobileNo, String otp) {
		try {
			
			String command = "curl -X POST -d to=+"+mobileNo+"&message=K11-VMS-OTP:"+otp+" -H Accept: application/json https://d5f0629a-0abd-400f-9059-7a996b7da98a:QKnJYGZLd7Rrx2UQyzrqvg@api.blower.io/messages";
			System.out.println(command);
			Process process = Runtime.getRuntime().exec(command);
			System.out.println("OTP send");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
