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
 * Servlet implementation class UpdateVisitorRecordServlet
 */
public class UpdateVisitorApprovingOfficerRecordServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lastModifiedBy = (String) request.getSession(false).getAttribute("idNo");
		
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());
		
		String vmsId = (String) request.getParameter("vmsId");
		Visitor v = null;
		String message = "VMS ID of visitor is unavailable, please add visitor record.";
		if(vmsId != null && !StringUtils.isEmpty(vmsId)) {
			//retrieve Visitor object
			v = VMSManagerDAO.retrieveByVmsId(vmsId);
			//retrieve approving officer ID from session
			String idNo = (String) request.getSession(false).getAttribute("idNo");
			v.setApprovingOfficer(idNo);
			v.setLastModifiedBy(lastModifiedBy);
			v.setLastModifiedByDt(timestamp);
			message = VMSManagerDAO.updateVisitorApprovingOfficer(v);
			
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
