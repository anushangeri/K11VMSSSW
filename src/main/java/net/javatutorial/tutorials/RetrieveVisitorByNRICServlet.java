package net.javatutorial.tutorials;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import net.javatutorial.DAO.DropdownListManagerDAO;
import net.javatutorial.DAO.SiteManagerDAO;
import net.javatutorial.DAO.VMSManagerDAO;
import net.javatutorial.entity.Dropdown;
import net.javatutorial.entity.Site;
import net.javatutorial.entity.Visitor;

/**
 * Servlet implementation class RetrieveVisitorByNRICServlet
 * To retrieve latest visitor record to populate in addVisitor.jsp
 * If the visitor object is empty, then look at the google sheets
 */
public class RetrieveVisitorByNRICServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usertype = (String) request.getSession(false).getAttribute("usertype");
		String idNo = (String) request.getSession(false).getAttribute("idNo");
		
		//from client login view
		String idNoFromClient = request.getParameter("idNo");
				
		//from client login view
		String vmsId = request.getParameter("vmsId");
		String status = request.getParameter("status");
		
		ArrayList<Visitor> vList = null;
		Visitor v = null;
		
		if(usertype == null && vmsId == null) {
			if(!StringUtils.isEmpty(idNo)) {
				vList = VMSManagerDAO.retrieveByNameIDPopulate(idNo);
				if(vList != null && vList.size() > 0) {
					v = vList.get(0);
				}
			}
		}
		else if(vmsId != null && status != null && status.equals("readonly")){
			v = VMSManagerDAO.retrieveByVmsId(vmsId);
			
		}
		else {
			vList = VMSManagerDAO.retrieveByNameIDPopulate(idNoFromClient);
			if(vList != null && vList.size() > 0) {
				v = vList.get(0);
			}
			
		}
		ArrayList<Site> siteDropdown = SiteManagerDAO.retrieveAll();
		ArrayList<Dropdown> visitPurpose = DropdownListManagerDAO.retrieveByDropdownKey("VISIT_PURPOSE");
		
		request.setAttribute("visitorLatRec", v);
		request.setAttribute("siteDropdown", siteDropdown);
		request.setAttribute("visitPurpose", visitPurpose);
		request.setAttribute("status", status);
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

}
