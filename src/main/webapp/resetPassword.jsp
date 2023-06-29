<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="org.apache.commons.collections.IteratorUtils"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="net.javatutorial.entity.*"%>
<%@page import="net.javatutorial.DAO.DropdownListManagerDAO"%>
<%@include file="loginVMSCSS.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.time.*"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
<!-- password check styling -->	
<style type="text/css">
/* The message box is shown when the user clicks on the password field */
#message {
  display:none;
  background: #f1f1f1;
  color: #000;
  position: relative;
  padding: 20px;
  margin-top: 10px;
}

#message p {
  padding: 10px 35px;
  font-size: 18px;
}

/* Add a green text color and a checkmark when the requirements are right */
.valid {
  color: green;
}

.valid:before {
  position: relative;
  left: -35px;
  content: "✔";
}

/* Add a red text color and an "x" when the requirements are wrong */
.invalid {
  color: red;
}

.invalid:before {
  position: relative;
  left: -35px;
  content: "✖";
}
</style>
<script>
	function validateForm() {
		var idNo = document.forms["checkNRIC"]["idNo"].value;
		var first = idNo.charAt(0);
		var isDigitFirst = (first >= '0' && first <= '9');
		var second = idNo.charAt(1);
		var isDigitSecond = (second >= '0' && second <= '9');
		var third = idNo.charAt(2);
		var isDigitThird = (third >= '0' && third <= '9');
		var forth = idNo.charAt(3);
		var isDigitForth = (forth >= '0' && forth <= '9');
		var n = idNo.length;
		if (idNo != "K11ADMIN" && (!(n >= 4) ||
				!isDigitFirst || !isDigitSecond || !isDigitThird || isDigitForth))  {
			alert("PDPA Compliance: Enter ONLY last 3 digit and letter of ID Number. E.g. 409J ");
			return false;
		}
		if (idNo != "K11ADMIN" && (!(n >= 4) ||
				!isDigitFirst || !isDigitSecond || !isDigitThird || !isDigitForth))  {
			alert("PDPA Compliance: Enter ONLY last 4 digit of Passport No. E.g. 4456");
			return false;
		}
	}
	function showPassword() {
		  var x = document.getElementById("psw");
		  var y = document.getElementById("oldpassword");
		  if (x.type === "password") {
		    x.type = "text";
		  } else {
		    x.type = "password";
		  }
		  if (y.type === "password") {
		    y.type = "text";
		  } else {
		    y.type = "password";
		  }
	}
</script>
</head>
<body>
	<%
	String idNo = "";
	if (request.getSession(false).getAttribute("idNo") != null) {
	 	idNo = (String) request.getSession(false).getAttribute("idNo");
	 }
	%>
	<div class="container body-content">
		<div class="page-header">
			<label class="heading">Change Password</label> <br>
			<b>How to use:</b> Please enter old and new password. Make sure to login first.
			<center>
				<form action="resetPassword" method="post" onsubmit="return validateForm()">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="idNo">ID Number: </label> <input type="text"
								class="form-control" name="idNo" value="<%=idNo %>" readonly required>
						</div>
						<div class="form-group col-md-4">
							<label for="oldpassword">Old Password</label> <input type="password" class="form-control" id="oldpassword"
								name="oldpassword" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
								title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
								required>
						</div>
						<div class="form-group col-md-4">
							<label for="psw">New Password</label> <input type="password" class="form-control" id="psw"
								name="psw" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
								title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
								required><input type="checkbox" onclick="showPassword()">Show Password
						</div>
					</div>
					<div class="form-row">
						<button type="submit" class="btn btn-primary btn-lg active">Submit
							Record</button>
							
						<a href="/index.jsp" class="btn btn-warning btn-lg active" role="button"
							aria-pressed="true">Back</a>
					</div>
					<br> <br>
				</form>
				<div id="message">
					  <h3>Password must contain the following:</h3>
					  <p id="letter" class="invalid">A <b>lowercase</b> letter</p>
					  <p id="capital" class="invalid">A <b>capital (uppercase)</b> letter</p>
					  <p id="number" class="invalid">A <b>number</b></p>
					  <p id="length" class="invalid">Minimum <b>8 characters</b></p>
				</div>
		
			</center>
		</div>
	</div>
	<script>
		//script must be loaded at the end for the password check to work
		var myInput = document.getElementById("psw");
		var letter = document.getElementById("letter");
		var capital = document.getElementById("capital");
		var number = document.getElementById("number");
		var length = document.getElementById("length");
		
		// When the user clicks on the password field, show the message box
		myInput.onfocus = function() {
		  document.getElementById("message").style.display = "block";
		}
		
		// When the user clicks outside of the password field, hide the message box
		myInput.onblur = function() {
		  document.getElementById("message").style.display = "none";
		}
		
		// When the user starts to type something inside the password field
		myInput.onkeyup = function() {
		  // Validate lowercase letters
		  var lowerCaseLetters = /[a-z]/g;
		  if(myInput.value.match(lowerCaseLetters)) {  
		    letter.classList.remove("invalid");
		    letter.classList.add("valid");
		  } else {
		    letter.classList.remove("valid");
		    letter.classList.add("invalid");
		  }
		  
		  // Validate capital letters
		  var upperCaseLetters = /[A-Z]/g;
		  if(myInput.value.match(upperCaseLetters)) {  
		    capital.classList.remove("invalid");
		    capital.classList.add("valid");
		  } else {
		    capital.classList.remove("valid");
		    capital.classList.add("invalid");
		  }
		
		  // Validate numbers
		  var numbers = /[0-9]/g;
		  if(myInput.value.match(numbers)) {  
		    number.classList.remove("invalid");
		    number.classList.add("valid");
		  } else {
		    number.classList.remove("valid");
		    number.classList.add("invalid");
		  }
		  
		  // Validate length
		  if(myInput.value.length >= 8) {
		    length.classList.remove("invalid");
		    length.classList.add("valid");
		  } else {
		    length.classList.remove("valid");
		    length.classList.add("invalid");
		  }
		}
	</script>
</body>
</html>