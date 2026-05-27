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

import net.javatutorial.DAO.ClockingManagerDAO;
import net.javatutorial.entity.Clocking;

/**
 * Servlet implementation class ViewClockingRecordsServlet
 */
public class ViewClockingRecordsServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usertype = (String) request.getSession(false).getAttribute("usertype");
		String idNo = (String) request.getSession(false).getAttribute("idNo");
		String name = (String) request.getSession(false).getAttribute("name");
		String recordsToReceive = (String) request.getParameter("recordsToReceive");
		
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore")) ;
		Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());
		
		String message = "No clocking records available for: " + name;
		ArrayList<Clocking> vList = null;
		if(!StringUtils.isEmpty(idNo)) {
			if(!StringUtils.isEmpty(usertype) && usertype != null
					&& (usertype.equals("ADMIN") || usertype.equals("OFFICER") || usertype.equals("MANAGEMENT"))) {
				if(!(StringUtils.isEmpty(recordsToReceive) || recordsToReceive == null) && recordsToReceive.equals("currdate")) {
					vList = ClockingManagerDAO.retrieveAllCurrentDay(timestamp);
					System.out.println(timestamp + " : " + vList.toString());
					message = "List of clocking records";
					request.setAttribute("vList", vList);
				}
				else if((StringUtils.isEmpty(recordsToReceive) || recordsToReceive == null) || recordsToReceive.equals("10days") ) {
					vList = ClockingManagerDAO.retrieveAllLast10Days(timestamp);
					System.out.println(timestamp + " : " + vList.toString());
					message = "List of clocking records";
					request.setAttribute("vList", vList);
				}
				else if(!(StringUtils.isEmpty(recordsToReceive) || recordsToReceive == null) && recordsToReceive.equals("30days") ) {
					vList = ClockingManagerDAO.retrieveAllLast30Days(timestamp);
					System.out.println(timestamp + " : " + vList.toString());
					message = "List of clocking records";
					request.setAttribute("vList", vList);
				}
				else if(!(StringUtils.isEmpty(recordsToReceive) || recordsToReceive == null) && recordsToReceive.equals("all") ) {
					vList = ClockingManagerDAO.retrieveAllClockings();
					System.out.println(timestamp + " : " + vList.toString());
					message = "List of clocking records";
					request.setAttribute("vList", vList);
				}
				else {
					vList = ClockingManagerDAO.retrieveAllClockings();
					message = "List of clocking records";
					request.setAttribute("vList", vList);
				}
				if(vList == null || vList.size() == 0) {
					message = "No clocking records available";
				}
			}
			
		}
		
		request.setAttribute("message", message);
        RequestDispatcher rd = request.getRequestDispatcher("viewClocking.jsp");
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
