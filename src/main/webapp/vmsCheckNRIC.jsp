<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="loginVMSCSS.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.URL"%>
<%@page import="net.javatutorial.entity.*"%>
<%@page import="net.javatutorial.DAO.*"%>
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
	href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
	rel="stylesheet">

</head>
<body>
	<%
		session.removeAttribute("usertype");
		session.removeAttribute("name");
	%>
	<center>
		<form name="checkNRIC" action="vmsCheckNRIC" method="post">
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="name">Visitor or Vehicle Driver Name: </label> <input type="text"
						class="form-control" name="name"
						oninput="this.value = this.value.toUpperCase()" required>
				</div>
				<div class="form-group col-md-6">
					<label for="idNo">ID Number: </label> <input type="text"
						class="form-control" name="idNo" id="idNo" placeholder="xxxx" oninput="this.value = this.value.toUpperCase()"
						minlength="4" maxlength="15" required>
				</div>
				<input type="hidden" id="recordType" name="recordType" value=<%=request.getParameter("recordType")%>>
				<button type="submit" class="btn btn-primary">Check NRIC</button>
			</div>
		</form>
	</center>
</body>
</html>