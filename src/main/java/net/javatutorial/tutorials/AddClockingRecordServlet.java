package net.javatutorial.tutorials;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.javatutorial.DAO.ClockingManagerDAO;
import net.javatutorial.entity.Clocking;

public class AddClockingRecordServlet extends HttpServlet {
    private static final long serialVersionUID = -4751096228274971485L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int nextVal = ClockingManagerDAO.getNextVal();

            String clockingId = String.valueOf(nextVal);
            String clockingPointName = request.getParameter("clockingPointName");
            String siteName = request.getParameter("siteName");

            if (clockingPointName == null || siteName == null || clockingPointName.isEmpty() || siteName.isEmpty()) {
                response.sendRedirect("clockingMain.jsp?error=Missing+parameters");
                return;
            }

            // --- Get officer details from session ---
            HttpSession session = request.getSession(true);
            String officerName = null;
            String officerNric = null;
            String createdBy = null;
            String lastModifiedBy = null;

            if (session != null) {
                officerName = (String) session.getAttribute("officerName");
                officerNric = (String) session.getAttribute("officerNric");

                if (officerName == null || officerNric == null || officerName.isEmpty() || officerNric.isEmpty()) {
                    response.sendRedirect("clockingMain.jsp?error=Missing+officer+details");
                    return;
                } else {
                    createdBy = officerName + " - " + officerNric;
                    lastModifiedBy = createdBy;
                }
            }

            ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Singapore"));
            Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());

            // --- Updated constructor: Clocking with createdBy & lastModifiedBy ---
            Clocking clocking = new Clocking(
                clockingId,
                clockingPointName,
                siteName,
                createdBy,
                timestamp,
                timestamp,
                lastModifiedBy
            );

            String message = ClockingManagerDAO.addClocking(clocking);

            if (message.toLowerCase().contains("error") || message.toLowerCase().contains("exception")) {
                response.sendRedirect("clockingMain.jsp?error=" + message);
            } else {
                // Keep track of records in session
                if (session != null) {
                    @SuppressWarnings("unchecked")
                    List<Clocking> records = (List<Clocking>) session.getAttribute("clockingRecords");
                    if (records == null) {
                        records = new ArrayList<>();
                    }
                    records.add(clocking);
                    session.setAttribute("clockingRecords", records);
                }

                response.sendRedirect("clockingMain.jsp?success=1");
            }

        } catch (Exception e) {
            e.printStackTrace(); // log for server
            response.sendRedirect("clockingMain.jsp?error=" + e.getMessage());
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
