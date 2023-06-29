<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="org.apache.commons.collections.IteratorUtils"%>
<%@page import="com.google.gdata.data.spreadsheet.CellEntry"%>
<%@page import="com.google.gdata.data.spreadsheet.Cell"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="net.javatutorial.entity.*"%>
<%@include file="loginVMSCSS.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.time.*"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.google.gdata.client.spreadsheet.SpreadsheetService"%>
<%@page
	import="com.google.gdata.data.spreadsheet.CustomElementCollection"%>
<%@page import="com.google.gdata.data.spreadsheet.ListEntry"%>
<%@page import="com.google.gdata.data.spreadsheet.ListFeed"%>
<%@page import="com.google.gdata.util.ServiceException"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div class="container body-content">
		<div class="page-header">
			<label class="heading">Dropdown Management System</label> <br>
			<b>How to use:</b> Please enter Site Details.
			<% 
				Dropdown v = null;
				String recordType = "create";
			 	if (request.getAttribute("dropdownRecord") != null) {
			 		v = (Dropdown) request.getAttribute("dropdownRecord");
			 		recordType = "update";
			 	}
			%>
			<center>
			<% if(recordType.equals("create")){ %>
				<form action="addDropdown" method="post">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="dropdownKey">Dropdown Key: </label> <input type="text"
								class="form-control" name="dropdownKey"
								oninput="this.value = this.value.toUpperCase()" required>
						</div>
						<div class="form-group col-md-6">
							<label for="dropdownValue">Dropdown Value: </label> <input type="text"
								class="form-control" name="dropdownValue"
								oninput="this.value = this.value.toUpperCase()" required>
						</div>
					</div>
					<div class="form-row">
						<button type="submit" class="btn btn-primary btn-lg active">Submit
							Record</button>
							
						<a href="clientMain.jsp" class="btn btn-warning btn-lg active" role="button"
							aria-pressed="true">Back</a>
					</div>
					<br> <br>
				</form>
			<%} 
			else{%>
				<form action="updateDropdown" method="post">
					<div class="form-row">
						<input type="hidden" id="dropdownId" name="dropdownId" value="<%=v.getDropdownId()%>">
						<div class="form-group col-md-6">
							<label for="dropdownKey">Dropdown Key: </label> <input type="text"
								class="form-control" name="dropdownKey"
								oninput="this.value = this.value.toUpperCase()" 
								value="<%=((v == null) ? "" : v.getDropdownKey())%>" required>
						</div>
						<div class="form-group col-md-6">
							<label for="dropdownValue">Dropdown Value: </label> <input type="text"
								class="form-control" name="dropdownValue"
								oninput="this.value = this.value.toUpperCase()" 
								value="<%=((v == null) ? "" : v.getDropdownValue())%>" required>
						</div>
					</div>
					<div class="form-row">
						<button type="submit" class="btn btn-primary btn-lg active">Update
							Record</button>
							
						<a href="clientMain.jsp" class="btn btn-warning btn-lg active" role="button"
							aria-pressed="true">Back</a>
					</div>
					<br> <br>
				</form>
			<%} %>
			</center>
		</div>
	</div>
</body>
</html>