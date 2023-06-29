<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="org.apache.commons.collections.IteratorUtils"%>
<%@page import="com.google.gdata.data.spreadsheet.CellEntry"%>
<%@page import="com.google.gdata.data.spreadsheet.Cell"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="net.javatutorial.entity.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="loginVMSCSS.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.time.*"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.google.gdata.client.spreadsheet.SpreadsheetService"%>
<%@page import="com.google.gdata.data.spreadsheet.CustomElementCollection"%>
<%@page import="com.google.gdata.data.spreadsheet.ListEntry"%>
<%@page import="com.google.gdata.data.spreadsheet.ListFeed"%>
<%@page import="com.google.gdata.util.ServiceException"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css">
<style type="text/css"></style>
<script type="text/javascript"
	src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>

<script type="text/javascript">
	var dt = new Date();
	document.getElementById("currTime").innerHTML = dt.toLocaleTimeString();
</script>
</head>
<body>
	<div class="container body-content">
		<div class="page-header">
			<label class="heading">Visitor Management System</label> <br> <b>How
				to use:</b> Please enter Visitor Details. <br> <br>
			<center>
				<%
					String userInput = "SxxxxxxxJ";
					Visitor v = null;
					if (request.getAttribute("visitorLatRec") != null) {
						v = (Visitor) request.getAttribute("visitorLatRec");
					}
					if (request.getSession(false).getAttribute("usertype") != null) {
						userInput = (String) request.getSession(false).getAttribute("usertype");
					}
				%>
				<br>
				<form action="addVisitor" method="post">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="firstName">First Name: </label> <input type="text"
								class="form-control" name="firstName"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getFirstName())%>" required>
						</div>
						<div class="form-group col-md-6">
							<label for="lastName">Last Name: </label> <input type="text"
								class="form-control" name="lastName"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getLastName())%>" required>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="idNo">IC Number: </label> <input type="text"
								class="form-control" name="idNo"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? userInput : v.getIdNo())%>" maxlength="9" required>
						</div>
						<div class="form-group col-md-6">
							<label for="mobileNo">Mobile: </label> <input type="text"
								class="form-control" name="mobileNo"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getMobileNo())%>" required>
						</div>
					</div>	
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="vehicleNo">Vehicle Number: </label> <input
								type="text" class="form-control" name="vehicleNo"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getVehicleNo())%>" required>
						</div>
						<div class="form-group col-md-6">
							<label for="hostName">Host Name: </label> <input type="text"
								class="form-control" name="hostName"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getHostName())%>" required>
						</div>
						<div class="form-group col-md-6">
							<label for="hostNo">Host Number: </label> <input type="text"
								class="form-control" name="hostNo"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getHostNo())%>" required>
						</div>
						<div class="form-group col-md-6">
							<label for="visitorCardId">Visitor Card ID: </label> <input
								type="text" class="form-control" name="visitorCardId"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getVisitorCardId())%>" required>
						</div>
					</div>
					<div class="form-row checkbox">
						<input type="checkbox" id="coviddeclaration" 
						name="coviddeclaration" value="Yes">
  							<label for="coviddeclaration"> 
  							I confirm that I am NOT experiencing any of the following symptoms: <br>
							• fever (feeling hot to the touch, a temperature of 37.8 degrees Celsius or higher)<br>
							• new onset of cough (continuous, more than usual)<br>
							• difficulty breathing<br>
							<b>*Individuals are required to self-identify should they experience any COVID-19 symptoms.</b></label>
					</div>
					<div class="form-row">
						<button type="submit" class="btn btn-primary btn-lg active">Submit Record</button> 
						<a href="/vms" class="btn btn-warning btn-lg active" role="button" aria-pressed="true">Back</a>
					</div>
					<br>
					<br>
				</form>
			</center>
		</div>
	</div>

</body>
</html>