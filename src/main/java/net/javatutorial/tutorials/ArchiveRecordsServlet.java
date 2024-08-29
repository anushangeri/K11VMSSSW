package net.javatutorial.tutorials;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.javatutorial.DAO.VMSArchiveManagerDAO;
import net.javatutorial.DAO.VMSManagerDAO;
import net.javatutorial.DAO.VehMSArchiveManagerDAO;
import net.javatutorial.DAO.VehMSManagerDAO;
import net.javatutorial.entity.Vehicle;
import net.javatutorial.entity.Visitor;

/**
 * Servlet implementation class ArchiveRecordsServlet
 * 
 * Archiving Logic: daily batch job will move data from main table to archive table, 
 * so main table will have only 30 days worth of data.
 * 
 * Monthly batch job will run to retrieve all data in archive table and email to client 
 * and delete data from archive table leaving only 30 days
 * Monthly batch job will run to retrieve all data in main table and email to client 
 * so client will have previous month and current month data
 *
 */
public class ArchiveRecordsServlet extends HttpServlet {
	private static final long serialVersionUID = -4751096228274971485L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//before we move records over, we need to update timeout
			//entry in the day shift 8am to 8pm
			// Get the current date and time in the specified timezone
	        ZoneId zoneId = ZoneId.of("Singapore");
	        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
	
	        // Calculate the previous day
	        ZonedDateTime previousDate = zonedDateTime.minusDays(1);
	
	        // Set the time range for the previous day (12 AM to 7 PM)
	        ZonedDateTime startOfPreviousDay = previousDate.toLocalDate().atStartOfDay(zoneId);
	        ZonedDateTime endOfPreviousDayAt7PM = previousDate.withHour(19).withMinute(0).withSecond(0).withNano(0);
	        
	        // Convert to Timestamp
	        Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startOfPreviousDay.toString().substring(0, 16).replace("T", " ") + ":00");
	        Timestamp startTimestamp = new Timestamp(startDate.getTime());
	        
	        Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endOfPreviousDayAt7PM.toString().substring(0, 16).replace("T", " ") + ":00");
	        Timestamp endTimestamp = new Timestamp(endDate.getTime());
        
	        // Example: Timestamp for TIME_OUT_DT and LAST_MODIFIED_BY_DT
	        Timestamp timestamp = endTimestamp;
	        Timestamp systemDate = Timestamp.from(ZonedDateTime.now(zoneId).toInstant());
	        
	        System.out.println(timestamp + " , " + systemDate + " , " + startTimestamp + " , " + endTimestamp);
	        
	        String updateRecordsVisitorMessage = VMSManagerDAO.updateStandardVisitorTimeOutDt(timestamp, systemDate, startTimestamp, endTimestamp);
	        String updateRecordsVehicleMessage = VehMSManagerDAO.updateStandardVehicleTimeOutDt(timestamp, systemDate, startTimestamp, endTimestamp);
	        
	        System.out.println("updateRecordsMessage: " + updateRecordsVisitorMessage + " , " + updateRecordsVehicleMessage);
		}
        catch(Exception e) {
            System.out.println("Exception :" + e);
        }
		//archiving visitor records
		String visitorMessage = VMSArchiveManagerDAO.moveVisitor();
		System.out.println(visitorMessage);
		//archiving vehicle records
		String vehicleMessage = VehMSArchiveManagerDAO.moveVehicle();
		System.out.println(vehicleMessage);
		SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		// get the current date
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		// get the calendar last day of this month
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// check to see if the given date really is the last day of this month
		System.out.println( "check to see if the given date really is the last day of this month");
		System.out.println( cal.get(Calendar.DAY_OF_MONTH) == lastDay);
		if(cal.get(Calendar.DAY_OF_MONTH) == lastDay) {
			System.out.println( "it is the last day of the month, so batch job will do archive db clean up and email results");
			
			final String password = new String(System.getenv("EMAIL_PASSWORD"));
			final String excelFileName = new String(System.getenv("FILE_NAME"));
			final String clientRecipients = new String(System.getenv("CLIENT_RECIPIENTS"));
			final String emailSubject = new String(System.getenv("EMAIL_SUBJECT"));
			
			try 
			{ 
				Calendar calPreviousMonth = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
				calPreviousMonth.add(Calendar.MONTH, -1);
				
				//start making email
				//retrieving visitor records from archive table
				ArrayList<Visitor> visitorList = VMSArchiveManagerDAO.retrieveAll();
				System.out.println("retrieved visitors previous month successful");
				
				//retrieving visitor records from archive table
				ArrayList<Vehicle> vehicleList = VehMSArchiveManagerDAO.retrieveAll();
				System.out.println("retrieved vehicles previous month successful");
				
				//retrieving visitor records from main table
				ArrayList<Visitor> visitorListCurrMonth = VMSManagerDAO.retrieveAll();
				System.out.println("retrieved visitors current month successful");
				
				//retrieving visitor records from archive table
				ArrayList<Vehicle> vehicleListCurrMonth = VehMSManagerDAO.retrieveAll();
				System.out.println("retrieved vehicles current month successful");
				
				LocalDate localDate = LocalDate.now(ZoneId.of("GMT+08:00"));
				String fileName = excelFileName + localDate +".xls";
				
				// workbook object
				XSSFWorkbook workbook = new XSSFWorkbook();
				// spreadsheet object
				XSSFSheet visitorspreadsheet = workbook.createSheet(" Visitor Records_" + monthFormat.format(calPreviousMonth.getTime()) + "_" + yearFormat.format(calPreviousMonth.getTime()));
				// creating a row object

				XSSFRow visitorheader = visitorspreadsheet.createRow(0);
				visitorheader.createCell(0).setCellValue("Record ID");
				visitorheader.createCell(1).setCellValue("Visitor Name");
				visitorheader.createCell(2).setCellValue("Visitor Company Name");
				visitorheader.createCell(3).setCellValue("Visited Site");
				visitorheader.createCell(4).setCellValue("Visitor ID Type");
				visitorheader.createCell(5).setCellValue("Visitor ID Number");
				visitorheader.createCell(6).setCellValue("Visitor Mobile No.");
				visitorheader.createCell(7).setCellValue("Visitor Vehicle No.");
				visitorheader.createCell(8).setCellValue("Host Name");
				visitorheader.createCell(9).setCellValue("Host Number");
				visitorheader.createCell(10).setCellValue("Visitor Card ID");
				visitorheader.createCell(11).setCellValue("Remarks");
				visitorheader.createCell(12).setCellValue("Visit Purpose");
				visitorheader.createCell(13).setCellValue("Approving Officer");
				visitorheader.createCell(14).setCellValue("Time In Date");
				visitorheader.createCell(15).setCellValue("Time Out Date");
				visitorheader.createCell(16).setCellValue("Archived Date");
				visitorheader.createCell(17).setCellValue("Created By");
				visitorheader.createCell(18).setCellValue("Created By Date");
				visitorheader.createCell(19).setCellValue("Last Modified By");
				visitorheader.createCell(20).setCellValue("Last Modified By Date");
				
				XSSFRow row;
				int rowid = 1;

				System.out.println("writing the data into the sheets...");
				System.out.println("writing visitor records from previous month..");
				// writing the data into the sheets...
				for (Visitor a : visitorList) {

					row = visitorspreadsheet.createRow(rowid++);

					row.createCell(0).setCellValue(a.getVmsId());
					row.createCell(1).setCellValue(a.getName());
					row.createCell(2).setCellValue(a.getCompanyName());
					row.createCell(3).setCellValue(a.getSite());
					row.createCell(4).setCellValue(a.getIdType());
					row.createCell(5).setCellValue(a.getIdNo());
					row.createCell(6).setCellValue(a.getMobileNo());
					row.createCell(7).setCellValue(a.getVehicleNo());
					row.createCell(8).setCellValue(a.getHostName());
					row.createCell(9).setCellValue(a.getHostNo());
					row.createCell(10).setCellValue(a.getVisitorCardId());
					row.createCell(11).setCellValue(a.getRemarks());
					row.createCell(12).setCellValue(a.getVisitPurpose()); 
					row.createCell(13).setCellValue(a.getApprovingOfficer());
					row.createCell(14).setCellValue(a.getTimeInDt().toString());
					if (a.getTimeOutDt() != null) {
						row.createCell(15).setCellValue(a.getTimeOutDt().toString());
					}
					row.createCell(16).setCellValue(a.getArchivedDt().toString());
					row.createCell(17).setCellValue(a.getCreatedBy());
					row.createCell(18).setCellValue(a.getCreatedByDt().toString());
					row.createCell(19).setCellValue(a.getLastModifiedBy());
					row.createCell(20).setCellValue(a.getLastModifiedByDt().toString());
				}
				
				XSSFSheet vehiclespreadsheet = workbook.createSheet(" Vehicle Records " + monthFormat.format(calPreviousMonth.getTime()) + "_" + yearFormat.format(calPreviousMonth.getTime()));
				// creating a row object

				//covid indicator and temperature is removed
				XSSFRow vehicleheader = vehiclespreadsheet.createRow(0);
				vehicleheader.createCell(0).setCellValue("Record ID");
				vehicleheader.createCell(1).setCellValue("Visitor Name");
				vehicleheader.createCell(2).setCellValue("Visitor Company Name");
				vehicleheader.createCell(3).setCellValue("Visitor ID Type");
				vehicleheader.createCell(4).setCellValue("Visitor ID Number");
				vehicleheader.createCell(5).setCellValue("Visitor Mobile No.");
				vehicleheader.createCell(6).setCellValue("Vehicle PrimeMover No.");
				vehicleheader.createCell(7).setCellValue("Vehicle Container No.");
				vehicleheader.createCell(8).setCellValue("Vehicle Loaded?");
				vehicleheader.createCell(9).setCellValue("Lorry Chet Number");
				vehicleheader.createCell(10).setCellValue("Delivery Notice No.");
				vehicleheader.createCell(11).setCellValue("Visit Purpose");
				vehicleheader.createCell(12).setCellValue("Seal No.");
				vehicleheader.createCell(13).setCellValue("Container Size");
				vehicleheader.createCell(14).setCellValue("Remarks");
				vehicleheader.createCell(15).setCellValue("Warehouse Level");
				vehicleheader.createCell(16).setCellValue("Warehouse Site");
				vehicleheader.createCell(17).setCellValue("Warehouse Approver");
				vehicleheader.createCell(18).setCellValue("Time In Date");
				vehicleheader.createCell(19).setCellValue("Time Out Date");
				vehicleheader.createCell(20).setCellValue("Archived Date");
				vehicleheader.createCell(21).setCellValue("Created By");
				vehicleheader.createCell(22).setCellValue("Created By Date");
				vehicleheader.createCell(23).setCellValue("Last Modified By");
				vehicleheader.createCell(24).setCellValue("Last Modified By Date");
				
				rowid = 1;

				System.out.println("writing vehicle records from previous month...");
				// writing the data into the sheets...
				for (Vehicle a : vehicleList) {

					row = vehiclespreadsheet.createRow(rowid++);

					row.createCell(0).setCellValue(a.getVehicleId());
					row.createCell(1).setCellValue(a.getName());
					row.createCell(2).setCellValue(a.getCompanyName());
					row.createCell(3).setCellValue(a.getIdType());
					row.createCell(4).setCellValue(a.getIdNo());
					row.createCell(5).setCellValue(a.getMobileNo());
					row.createCell(6).setCellValue(a.getPrimeMoverNo());
					row.createCell(7).setCellValue(a.getContainerNo());
					row.createCell(8).setCellValue(a.getLoadedNoLoaded());
					row.createCell(9).setCellValue(a.getLorryChetNumber());
					row.createCell(10).setCellValue(a.getDeliveryNoticeNumber());
					row.createCell(11).setCellValue(a.getVisitPurpose());
					row.createCell(12).setCellValue(a.getSealNo());
					row.createCell(13).setCellValue(a.getContainerNo());
					row.createCell(14).setCellValue(a.getRemarks());
					row.createCell(15).setCellValue(a.getWarehouseLevel());
					row.createCell(16).setCellValue(a.getSite());
					row.createCell(17).setCellValue(a.getWarehouseApprover());
					row.createCell(18).setCellValue(a.getTimeInDt().toString());
					if (a.getTimeOutDt() != null) {
						row.createCell(19).setCellValue(a.getTimeOutDt().toString());
					}
					row.createCell(20).setCellValue(a.getArchivedDt().toString());
					row.createCell(21).setCellValue(a.getCreatedBy());
					row.createCell(22).setCellValue(a.getCreatedByDt().toString());
					row.createCell(23).setCellValue(a.getLastModifiedBy());
					row.createCell(24).setCellValue(a.getLastModifiedByDt().toString());
				}
				
				// spreadsheet object
				XSSFSheet visitorCurrMonthspreadsheet = workbook.createSheet(" Visitor Records_" + monthFormat.format(cal.getTime()) + "_" + yearFormat.format(cal.getTime()));
				// creating a row object

				XSSFRow visitorCurrMonthheader = visitorCurrMonthspreadsheet.createRow(0);
				visitorCurrMonthheader.createCell(0).setCellValue("Record ID");
				visitorCurrMonthheader.createCell(1).setCellValue("Visitor Name");
				visitorCurrMonthheader.createCell(2).setCellValue("Visitor Company Name");
				visitorCurrMonthheader.createCell(3).setCellValue("Visited Site");
				visitorCurrMonthheader.createCell(4).setCellValue("Visitor ID Type");
				visitorCurrMonthheader.createCell(5).setCellValue("Visitor ID Number");
				visitorCurrMonthheader.createCell(6).setCellValue("Visitor Mobile No.");
				visitorCurrMonthheader.createCell(7).setCellValue("Visitor Vehicle No.");
				visitorCurrMonthheader.createCell(8).setCellValue("Host Name");
				visitorCurrMonthheader.createCell(9).setCellValue("Host Number");
				visitorCurrMonthheader.createCell(10).setCellValue("Visitor Card ID");
				visitorCurrMonthheader.createCell(11).setCellValue("Remarks");
				visitorCurrMonthheader.createCell(12).setCellValue("Visit Purpose");
				visitorCurrMonthheader.createCell(13).setCellValue("Approving Officer");
				visitorCurrMonthheader.createCell(14).setCellValue("Time In Date");
				visitorCurrMonthheader.createCell(15).setCellValue("Time Out Date");
				visitorCurrMonthheader.createCell(16).setCellValue("Created By");
				visitorCurrMonthheader.createCell(17).setCellValue("Created By Date");
				visitorCurrMonthheader.createCell(18).setCellValue("Last Modified By");
				visitorCurrMonthheader.createCell(19).setCellValue("Last Modified By Date");
				
				rowid = 1;

				System.out.println("writing visitor records from current month..");
				// writing the data into the sheets...
				for (Visitor a : visitorListCurrMonth) {

					row = visitorCurrMonthspreadsheet.createRow(rowid++);

					row.createCell(0).setCellValue(a.getVmsId());
					row.createCell(1).setCellValue(a.getName());
					row.createCell(2).setCellValue(a.getCompanyName());
					row.createCell(3).setCellValue(a.getSite());
					row.createCell(4).setCellValue(a.getIdType());
					row.createCell(5).setCellValue(a.getIdNo());
					row.createCell(6).setCellValue(a.getMobileNo());
					row.createCell(7).setCellValue(a.getVehicleNo());
					row.createCell(8).setCellValue(a.getHostName());
					row.createCell(9).setCellValue(a.getHostNo());
					row.createCell(10).setCellValue(a.getVisitorCardId());
					row.createCell(11).setCellValue(a.getRemarks());
					row.createCell(12).setCellValue(a.getVisitPurpose()); 
					row.createCell(13).setCellValue(a.getApprovingOfficer());
					row.createCell(14).setCellValue(a.getTimeInDt().toString());
					if (a.getTimeOutDt() != null) {
						row.createCell(15).setCellValue(a.getTimeOutDt().toString());
					}

					row.createCell(16).setCellValue(a.getCreatedBy());
					row.createCell(17).setCellValue(a.getCreatedByDt().toString());
					row.createCell(18).setCellValue(a.getLastModifiedBy());
					row.createCell(19).setCellValue(a.getLastModifiedByDt().toString());
				}
				
				XSSFSheet vehicleCurrMonthspreadsheet = workbook.createSheet(" Vehicle Records " + monthFormat.format(cal.getTime()) + "_" + yearFormat.format(cal.getTime()));
				// creating a row object

				XSSFRow vehicleCurrMonthheader = vehicleCurrMonthspreadsheet.createRow(0);
				vehicleCurrMonthheader.createCell(0).setCellValue("Record ID");
				vehicleCurrMonthheader.createCell(1).setCellValue("Visitor Name");
				vehicleCurrMonthheader.createCell(2).setCellValue("Visitor Company Name");
				vehicleCurrMonthheader.createCell(3).setCellValue("Visitor ID Type");
				vehicleCurrMonthheader.createCell(4).setCellValue("Visitor ID Number");
				vehicleCurrMonthheader.createCell(5).setCellValue("Visitor Mobile No.");
				vehicleCurrMonthheader.createCell(6).setCellValue("Vehicle PrimeMover No.");
				vehicleCurrMonthheader.createCell(7).setCellValue("Vehicle Container No.");
				vehicleCurrMonthheader.createCell(8).setCellValue("Vehicle Loaded?");
				vehicleCurrMonthheader.createCell(9).setCellValue("Lorry Chet Number");
				vehicleCurrMonthheader.createCell(10).setCellValue("Delivery Notice No.");
				vehicleCurrMonthheader.createCell(11).setCellValue("Visit Purpose");
				vehicleCurrMonthheader.createCell(12).setCellValue("Seal No.");
				vehicleCurrMonthheader.createCell(13).setCellValue("Container Size");
				vehicleCurrMonthheader.createCell(14).setCellValue("Remarks");
				vehicleCurrMonthheader.createCell(15).setCellValue("Warehouse Level");
				vehicleCurrMonthheader.createCell(16).setCellValue("Warehouse Site");
				vehicleCurrMonthheader.createCell(17).setCellValue("Warehouse Approver");
				vehicleCurrMonthheader.createCell(18).setCellValue("Time In Date");
				vehicleCurrMonthheader.createCell(19).setCellValue("Time Out Date");
				vehicleCurrMonthheader.createCell(20).setCellValue("Created By");
				vehicleCurrMonthheader.createCell(21).setCellValue("Created By Date");
				vehicleCurrMonthheader.createCell(22).setCellValue("Last Modified By");
				vehicleCurrMonthheader.createCell(23).setCellValue("Last Modified By Date");
				
				rowid = 1;

				System.out.println("writing vehicle records from current month...");
				// writing the data into the sheets...
				for (Vehicle a : vehicleListCurrMonth) {

					row = vehicleCurrMonthspreadsheet.createRow(rowid++);

					row.createCell(0).setCellValue(a.getVehicleId());
					row.createCell(1).setCellValue(a.getName());
					row.createCell(2).setCellValue(a.getCompanyName());
					row.createCell(3).setCellValue(a.getIdType());
					row.createCell(4).setCellValue(a.getIdNo());
					row.createCell(5).setCellValue(a.getMobileNo());
					row.createCell(6).setCellValue(a.getPrimeMoverNo());
					row.createCell(7).setCellValue(a.getContainerNo());
					row.createCell(8).setCellValue(a.getLoadedNoLoaded());
					row.createCell(9).setCellValue(a.getLorryChetNumber());
					row.createCell(10).setCellValue(a.getDeliveryNoticeNumber());
					row.createCell(11).setCellValue(a.getVisitPurpose());
					row.createCell(12).setCellValue(a.getSealNo());
					row.createCell(13).setCellValue(a.getContainerNo());
					row.createCell(14).setCellValue(a.getRemarks());
					row.createCell(15).setCellValue(a.getWarehouseLevel());
					row.createCell(16).setCellValue(a.getSite());
					row.createCell(17).setCellValue(a.getWarehouseApprover());
					row.createCell(18).setCellValue(a.getTimeInDt().toString());
					if (a.getTimeOutDt() != null) {
						row.createCell(19).setCellValue(a.getTimeOutDt().toString());
					}
					row.createCell(20).setCellValue(a.getCreatedBy());
					row.createCell(21).setCellValue(a.getCreatedByDt().toString());
					row.createCell(22).setCellValue(a.getLastModifiedBy());
					row.createCell(23).setCellValue(a.getLastModifiedByDt().toString());
				}
				
				
				System.out.println("writing finished, sending email ...");
				// .xlsx is the format for Excel Sheets...
				// writing the workbook into the file...
				FileOutputStream fos = new FileOutputStream(fileName);
				workbook.write(fos);
				fos.close();
				workbook.close();
				// out.close();

				String to = "k11.sivalingam@gmail.com";// change accordingly
				final String user = "shangeri.sivalingam@k11.com.sg";// change accordingly
				//final String password = "Sh@ngeri94";// change accordingly
				
				
				
				Properties properties = System.getProperties();
				properties.setProperty("mail.smtp.host", "mail.k11.com.sg");
				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.port", "25");

				Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, password);
					}
				});

				try {
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(user));
					
					// Create an ArrayList to hold email addresses
			        List<String> emailList = new ArrayList<>();
			        emailList.add(to);
			        
					if (clientRecipients.contains(",")) {
			            // Split the string by comma and trim any extra whitespace
			            String[] emailAddresses = clientRecipients.split("\\s*,\\s*");
			            
			            // Convert the array to a List using Arrays.asList()
			            emailList.addAll(Arrays.asList(emailAddresses));
			            
			        } else {
			        	// Single recipient
			        	emailList.add(clientRecipients);
			        }
					// Create an array of new InternetAddress object
		            InternetAddress[] addresses = new InternetAddress[emailList.size()];
		            for (int i = 0; i < emailList.size(); i++) {
		                addresses[i] = new InternetAddress(emailList.get(i));
		                
		            }
		            // Add all recipients
		            message.addRecipients(Message.RecipientType.TO, addresses);
					message.setSubject(emailSubject);

					// Create the message part
					BodyPart messageBodyPart = new MimeBodyPart();

					// Fill the message
					messageBodyPart.setText(
							"K11 VMS Records, there is 30 days worth of data in K11 VMS now. "
							+ "The attached excel has visitor & vehicle records that exceed 30 days.");

					Multipart multipart = new MimeMultipart();
					multipart.addBodyPart(messageBodyPart);

					// Part two is attachment
					messageBodyPart = new MimeBodyPart();
					DataSource source = new FileDataSource(fileName);

					// Now use your ByteArrayDataSource as
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(fileName);
					multipart.addBodyPart(messageBodyPart);

					// 6) set the multiplart object to the message object
					message.setContent(multipart);

					// 7) send message
					Transport.send(message);

					System.out.println("email sent....");
				} catch (MessagingException ex) {
					ex.printStackTrace();
				}
				String visitorDeleteRecords = VMSArchiveManagerDAO.deleteAll();
				String vehicleDeleteRecords = VehMSArchiveManagerDAO.deleteAll();
				System.out.println(visitorDeleteRecords);
				System.out.println(vehicleDeleteRecords);	

				
			}
			catch (Exception e)
			{ 
				e.printStackTrace(); 
			} 
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
