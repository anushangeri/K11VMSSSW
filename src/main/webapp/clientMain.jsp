<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="loginVMSCSS.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.URL"%>
<%@page import="net.javatutorial.entity.*"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
<script
	src="https://drvic10k.github.io/bootstrap-sortable/Scripts/bootstrap-sortable.js"
	type="text/javascript"></script>
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
	rel="stylesheet">
</head>
<body>
	<% 
	String message = (String) request.getAttribute("responseObj");
	if (message != null && !StringUtils.isEmpty(message)) {
	%>
	<label class="heading"><%=message%> </label><br>
	<%} %>
	<center> <br> <br>
		<div class="container">
			<form action="vms" method="get" >
				<button type="submit" id="removeBackground">
					<div class="card bg-dark text-white">
						<div class="card-body font-size-percent">
							View Visitor Records<br> 
							Select this.

						</div>
					</div>
				</button>
			</form>
			<br>
			<form action="vehms" method="get" >
				<button type="submit" id="removeBackground">
					<div class="card bg-warning text-white">
						<div class="card-body font-size-percent">
							View Vehicle Records<br>
							Select this.
						</div>
					</div>
				</button>
			</form>
			<br>
		</div>
	</center>
	<div class="container body-content">
		<center>
			<a href="clientLogin.jsp" class="btn btn-warning btn-lg active"
				role="button" aria-pressed="true">Back</a>
		</center>
	</div>
	<div class="container body-content">
		<center>
			<!-- Create client account is for K11 Admin only -->
			<%if (request.getSession(false).getAttribute("usertype") != null) {
				String userInput = (String) request.getSession(false).getAttribute("usertype");
				if (userInput.toUpperCase().equals("ADMIN")){ %>
					<a href="/retrieveDropdownForClientForm" class="btn btn-warning btn-lg active"
					role="button" aria-pressed="true">Create Client Record</a>
					<a href="/retrieveAllClientRecords" class="btn btn-warning btn-lg active"
					role="button" aria-pressed="true">Manage Client Records</a>
					<br>
					
					<a href="addSite.jsp" class="btn btn-warning btn-lg active"
					role="button" aria-pressed="true">Create Site Record</a>
					<a href="/retrieveAllSiteRecords" class="btn btn-warning btn-lg active"
					role="button" aria-pressed="true">Manage Site Records</a>
					<br>
					
					<a href="addDropdown.jsp" class="btn btn-warning btn-lg active"
					role="button" aria-pressed="true">Create Dropdown Record</a>
					<a href="/retrieveAllDropdownRecords" class="btn btn-warning btn-lg active"
					role="button" aria-pressed="true">Manage Dropdown Records</a>
				<%	
				}
			%>
			<% 
			}
			%>
		</center>
	</div>
</body>
</html>
