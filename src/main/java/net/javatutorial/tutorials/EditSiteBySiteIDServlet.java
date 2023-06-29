package net.javatutorial.tutorials;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import net.javatutorial.DAO.SiteManagerDAO;
import net.javatutorial.entity.Site;

/**
 * Servlet implementation class EditSiteBySiteIDServlet
 * To retrieve latest site record to populate in addSite.jsp
 * If the site object is empty, then user will fill themselves - new site
 */
public class EditSiteBySiteIDServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usertype = (String) request.getSession(false).getAttribute("usertype");
		String idNo = (String) request.getSession(false).getAttribute("idNo");
		String idType = (String) request.getSession(false).getAttribute("idType");

		//from edit option in siteManager.jsp
		String siteId = request.getParameter("siteId");
				
		ArrayList<Site> vList = null;
		Site v = null;
		
		if(usertype.equals("ADMIN")) {
			if(!StringUtils.isEmpty(idNo)) {
				vList = SiteManagerDAO.retrieveBySiteId(siteId);
				if(vList != null && vList.size() > 0) {
					v = vList.get(0);
				}
			}
		}
		request.setAttribute("siteRecord", v);
        RequestDispatcher rd = request.getRequestDispatcher("addSite.jsp");
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
