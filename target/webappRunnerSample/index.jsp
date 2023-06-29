<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="loginVMSCSS.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.URL"%>
<%@page import="com.google.gdata.client.spreadsheet.SpreadsheetService"%>
<%@page import="com.google.gdata.data.spreadsheet.CustomElementCollection"%>
<%@page import="com.google.gdata.data.spreadsheet.ListEntry"%>
<%@page import="com.google.gdata.data.spreadsheet.ListFeed"%>
<%@page import="com.google.gdata.util.ServiceException"%>
<%@page import="net.javatutorial.entity.*"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
<script src="https://drvic10k.github.io/bootstrap-sortable/Scripts/bootstrap-sortable.js" type="text/javascript"></script>
<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
</head>
<body>
	<% session.removeAttribute("usertype"); %>
	<center>
		<b>*Individuals are required to self-identify should they experience any COVID-19 symptoms.</b>
		<form action="vmsCheckNRIC" method="post">
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="idNo">IC Number: </label> <input type="text"
						class="form-control" name="idNo" id="idNo"
						placeholder="SxxxxxxxJ" minlength="8" maxlength="9" required>
				</div>
				<button type="submit" class="btn btn-primary">Check NRIC</button>
			</div>
		</form>
	</center>
</body>
</html>
