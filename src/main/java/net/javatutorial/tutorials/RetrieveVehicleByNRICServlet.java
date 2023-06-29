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
import net.javatutorial.DAO.VehMSManagerDAO;
import net.javatutorial.entity.Dropdown;
import net.javatutorial.entity.Site;
import net.javatutorial.entity.Vehicle;

/**
 * Servlet implementation class RetrieveVehicleByNRICServlet
 * To retrieve latest vehicle record to populate in addVehicle.jsp
 * If the vehicle object is empty, then user will fill themselves - new user
 */
public class RetrieveVehicleByNRICServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usertype = (String) request.getSession(false).getAttribute("usertype");
		String idNo = (String) request.getSession(false).getAttribute("idNo");

		//from client login view
		String idNoFromClient = request.getParameter("idNo");
		
		//from view button - this is just to view a record
		String vehicleId = request.getParameter("vehicleId");
		String status = request.getParameter("status");
		
		ArrayList<Vehicle> vList = null;
		Vehicle v = null;
		
		if(usertype == null && vehicleId == null) {
			if(!StringUtils.isEmpty(idNo)) {
				vList = VehMSManagerDAO.retrieveByNameIDPopulate(idNo);
				if(vList != null && vList.size() > 0) {
					v = vList.get(0);
				}
			}
		}
		else if(vehicleId != null && status != null && status.equals("readonly")){
			v = VehMSManagerDAO.retrieveByVehicleId(vehicleId);
			
		}
		else {
			vList = VehMSManagerDAO.retrieveByNameIDPopulate(idNoFromClient);
			if(vList != null && vList.size() > 0) {
				v = vList.get(0);
			}
		}
		//getting all the dropdown
		ArrayList<Dropdown> vehiclePurpose = DropdownListManagerDAO.retrieveByDropdownKey("VEHICLE_PURPOSE");
		ArrayList<Dropdown> containerSize = DropdownListManagerDAO.retrieveByDropdownKey("VEHICLE_SIZE");
		ArrayList<Site> siteDropdown = SiteManagerDAO.retrieveAll();
		
		request.setAttribute("status", status);
		request.setAttribute("vehicleLatRec", v);
		request.setAttribute("containerSize", containerSize);
		request.setAttribute("vehiclePurpose", vehiclePurpose);
		request.setAttribute("siteDropdown", siteDropdown);
		
        RequestDispatcher rd = request.getRequestDispatcher("addVehicle.jsp");
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
