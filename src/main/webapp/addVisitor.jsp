<%@page import="com.google.gdata.util.common.base.StringUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="loginVMSCSS.jsp"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="org.apache.commons.collections.IteratorUtils"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="net.javatutorial.entity.*"%>
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
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css">
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.12/js/intlTelInput.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.12/css/intlTelInput.css"
	rel="stylesheet" />

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/intlTelInput.min.js"></script>

<script>
function validateForm() {
	var tempAsStr = document.forms["addVisitor"]["temperature"].value;
	var tempAsInt = parseFloat(tempAsStr);
	if (isNaN(tempAsInt) || tempAsInt < 36 || tempAsInt > 37.5)  {
		alert("COVID Alert: Invalid temperature or temperature is high. Please go home if you are sick.");
		return false;
	}
	//remember to add  onsubmit="return validateForm()" in the form to use this function
}
function showDiv(divId, element)
{
    document.getElementById(divId).style.display = element.value == "GOVERNMENT AGENCY" ? 'block' : 'none';
    document.getElementById(divId).style.display == 'block' ? document.getElementById("officerIdNo").setAttribute("required", "") : document.getElementById("officerIdNo").removeAttribute("required");
    
}
processedMobileNo = document.querySelector("#processedMobileNo");
processedHostNo = document.querySelector("#processedHostNo");
function getProcessedMobileNoOnLoad(event) {
	event.preventDefault();
	
	const phoneNumber = document.forms["addVisitor"]["mobileNo"].value;
	processedMobileNo.value = phoneNumber; 
	
	const phoneNumberHostNo = document.forms["addVisitor"]["hostNo"].value;
	processedHostNo.value = phoneNumberHostNo;
}
function showOfficeDivOnLoad(officerLogin,visitPurpose)
{
    document.getElementById(officerLogin).style.display = document.getElementById(visitPurpose).value == "GOVERNMENT AGENCY" ? 'block' : 'none';
    document.getElementById(officerLogin).style.display == 'block' ? document.getElementById("officerIdNo").setAttribute("required", "") : document.getElementById("officerIdNo").removeAttribute("required");
    
    getProcessedMobileNoOnLoad(event);
}

function showPassword() {
	  var x = document.getElementById("officerpsw");
	  if (x.type === "password") {
	    x.type = "text";
	  } else {
	    x.type = "password";
	  }
} 

function getSMSOTP()
{
	
    var otp = Math.floor((Math.random() * 1000000) + 1);
    
    var processedMobileNo =  document.querySelector("#processedMobileNo").value;
    document.querySelector("#otpGenerated").value = otp;
    $.ajax({
        type: "POST",
        url: "../getSMSOTP",
        data: "processedMobileNo="+processedMobileNo+"&otpGenerated="+otp,
        success: function(result){
        	alert("OTP sent successfully, check SMS");
        }
    });
    
}
function checkMobileNo() {
	 if (processedMobileNo.value.length == 0)
   { 
      alert("Please enter a valid mobile number. Try removing the brackets/dashes/spaces in the number.");  	
      return false; 
   }  	
}
</script>
</head>
<body onload="showOfficeDivOnLoad('officerLogin','visitPurpose')">
	<div class="container body-content">
		<div class="page-header">
			<label class="heading"><%=((request.getAttribute("responseObj") != null) ? request.getAttribute("responseObj") : "")%></label>
			<br> <label class="heading">Visitor Management System</label> <br>
			<b>How to use:</b> Please enter Visitor Details.
			<%
			String idNo = "SxxxxxxxJ";
			String name = "";
			String otpGenerated = "";
			Visitor v = null;
			ArrayList<Site> siteDropdown = new ArrayList<Site>();
			ArrayList<Dropdown> visitPurpose = new ArrayList<Dropdown>();
			String readOnlyStatus = "required"; //this is to set the status to readonly for view function only else it is a required field
			if (request.getAttribute("visitorLatRec") != null) {
				v = (Visitor) request.getAttribute("visitorLatRec");
				otpGenerated = (String) request.getAttribute("otpGenerated");
			}
			if (request.getAttribute("siteDropdown") != null) {
				siteDropdown = (ArrayList<Site>) request.getAttribute("siteDropdown");
			}
			if (request.getAttribute("visitPurpose") != null) {
				visitPurpose = (ArrayList<Dropdown>) request.getAttribute("visitPurpose");
			}
			// is not admin or officer, means is it a normal visitor
			if (request.getSession(false).getAttribute("usertype") == null
					&& request.getSession(false).getAttribute("idNo") != null) {
				idNo = (String) request.getSession(false).getAttribute("idNo");
				name = (String) request.getSession(false).getAttribute("name");
			}
			if (request.getAttribute("status") != null) {
				readOnlyStatus = (String) request.getAttribute("status");
			}
			%>
			<center>
				<form action="addVisitor" method="post" name="addVisitor" onsubmit="return checkMobileNo()">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="name">Name (姓名): </label> <input type="text"
								class="form-control" name="name"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? name : v.getName())%>"
								<%=readOnlyStatus%>>
						</div>
						<div class="form-group col-md-6">
							<label for="companyName">Company Name (公司名称): </label> <input
								type="text" class="form-control" name="companyName"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getCompanyName())%>"
								<%=readOnlyStatus%>>
						</div>
						<div class="form-group col-md-6">
							<label for="siteVisiting">Site You Are Visiting (访问地点): </label>
							<%
							if (readOnlyStatus.equals("readonly")) {
							%>
							<select name="siteVisiting" class="form-control"
								<%=readOnlyStatus%>>
								<%
								for (Site eachSite : siteDropdown) {
								%>
								<option value="<%=eachSite.getSiteName()%>"
									<%=(v != null || v.getSite() != null) && v.getSite().equals(eachSite.getSiteName()) ? "selected" : ""%>>
									<%=eachSite.getSiteName()%></option>
								<%
								}
								%>
							</select>
							<%
							} else {
							%>
							<select name="siteVisiting" class="form-control"
								<%=readOnlyStatus%>>
								<option style="display:none;"></option>
								<%
								for (Site eachSite : siteDropdown) {
								%>
								<option value="<%=eachSite.getSiteName()%>">
									<%=eachSite.getSiteName()%></option>
								<%
								}
								%>
							</select>
							<%
							}
							%>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="idNo">ID Number (ID号码): </label> <input type="text"
								class="form-control" name="idNo"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? idNo : v.getIdNo())%>" minlength="4"
								maxlength="15" <%=((v == null) ? "" : "readonly")%>>
						</div>
						<div class="form-group col-md-4">
							<label for="mobileNo">Mobile No. (手机号码): </label> <input
								type="tel" class="form-control" id="mobileNo" name="mobileNo"
								onchange="processMobileNo(event)"
								value="<%=((v != null && v.getMobileNo() != null) ? v.getMobileNo() : "")%>"
								<%=readOnlyStatus%>> <input type="hidden"
								id="processedMobileNo" name="processedMobileNo" />
						</div>
						<div class="form-group col-md-4">
							<label for="visitPurpose">Visit Purpose (访问目的): </label>
							<%
							if (v == null || v.getVisitPurpose() == null) {
							%>
							<select id="visitPurpose"
								onchange="showDiv('officerLogin', this)" name="visitPurpose"
								class="form-control" <%=readOnlyStatus%>>
								<option style="display:none;"></option>
								<%
								for (Dropdown d : visitPurpose) {
								%>
								<option value="<%=d.getDropdownValue()%>">
									<%=d.getDropdownValue()%></option>
								<%
								}
								%>
							</select>
							<%
							} else {
							%>
							<select id="visitPurpose"
								onchange="showDiv('officerLogin', this)" name="visitPurpose"
								class="form-control" <%=readOnlyStatus%>>
								<%
								for (Dropdown d : visitPurpose) {
								%>
								<option value="<%=d.getDropdownValue()%>"
									<%=v.getVisitPurpose().equals(d.getDropdownValue()) ? "selected" : ""%>>
									<%=d.getDropdownValue()%></option>
								<%
								}
								%>
							</select>
							<%
							}
							%>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="vehicleNo">Vehicle Number (车牌号码): </label> <input
								type="text" class="form-control" name="vehicleNo"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getVehicleNo())%>"
								<%=readOnlyStatus.equals("readonly") ? readOnlyStatus : ""%>>
						</div>
						<div class="form-group col-md-6">
							<label for="hostName">Host Name (接待人): </label> <input
								type="text" class="form-control" name="hostName"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getHostName())%>"
								<%=readOnlyStatus.equals("readonly") ? readOnlyStatus : ""%>>
						</div>
						<div class="form-group col-md-4">
							<label for="hostNo">Host No. (接待人电话号码): </label> <input
								type="tel" class="form-control" id="hostNo" name="hostNo"
								onchange="processHostNo(event)"
								value="<%=((v != null && v.getHostNo() != null) ? v.getHostNo() : "")%>"
								<%=readOnlyStatus.equals("readonly") ? readOnlyStatus : ""%>> <input type="hidden"
								id="processedHostNo" name="processedHostNo" />
						</div>
						<div class="form-group col-md-6">
							<label for="visitorCardId">Visitor Card ID (访问卡号码): </label> <input
								type="text" class="form-control" name="visitorCardId"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getVisitorCardId())%>"
								<%=readOnlyStatus.equals("readonly") ? readOnlyStatus : ""%>>
						</div>
						<div class="form-group col-md-6">
							<label for="remarks">Remarks (其他): </label> <input type="text"
								class="form-control" name="remarks" id="remarks"
								value="<%=((v == null) ? "" : v.getRemarks())%>"
								<%=readOnlyStatus.equals("readonly") ? readOnlyStatus : ""%>>
						</div>
					</div>
					<br>
					<div id="officerLogin" class="form-row">
						<i>Please aproach guard house and seek approval from security
							officer on duty.</i>
						<div class="form-group col-md-6">
							<label for="officerIdNo">Approving Officer ID Number: </label> <input
								type="text" class="form-control" name="officerIdNo"
								id="officerIdNo" placeholder="xxxx"
								oninput="this.value = this.value.toUpperCase()" minlength="4"
								maxlength="9">
						</div>
						<div class="form-group col-md-4">
							<label for="officerpsw">Password:</label> <input type="password"
								class="form-control" id="officerpsw" name="officerpsw"
								pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
								title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"><input
								type="checkbox" onclick="showPassword()">Show Password
						</div>
					</div>
					<%
					if (readOnlyStatus.equals("readonly")) {
					%>
					<div class="form-row">
						<div class="form-group col-md-3">
							<label for="createdBy">Created By: </label> <input type="text"
								class="form-control" name="createdBy"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getCreatedBy())%>" readonly>
						</div>
						<div class="form-group col-md-3">
							<label for="createdByDt">Created By Date: </label> <input
								type="text" class="form-control" name="createdByDt"
								value="<%=((v == null) ? "" : v.getCreatedByDt())%>" readonly>
						</div>
						<div class="form-group col-md-3">
							<label for="lastModifiedBy">Last Modified By: </label> <input
								type="text" class="form-control" name="lastModifiedBy"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getLastModifiedBy())%>" readonly>
						</div>
						<div class="form-group col-md-3">
							<label for="lastModifiedByDt">Last Modified By Date: </label> <input
								type="text" class="form-control" name="lastModifiedByDt"
								value="<%=((v == null) ? "" : v.getLastModifiedByDt())%>"
								readonly>
						</div>
					</div>
					<%
					}
					%>
					<div class="form-row">
						<button type="submit" class="btn btn-primary btn-lg active"
							<%=readOnlyStatus.equals("readonly") ? "disabled" : ""%>>Submit
							Record</button>
						<a href="/vms" class="btn btn-warning btn-lg active" role="button"
							aria-pressed="true">Back</a>
					</div>
				</form>
			</center>
		</div>
	</div>
</body>
<script>
const phoneInputField = document.querySelector("#mobileNo");
const phoneInput = window.intlTelInput(phoneInputField, {
	 preferredCountries: ['sg', 'my'],	
	 utilsScript:
     "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/utils.js",
 });
processedMobileNo = document.querySelector("#processedMobileNo");
function processMobileNo(event) {
	 event.preventDefault();
	 const phoneNumber = phoneInput.getNumber();
	 processedMobileNo.value = phoneNumber;
}
</script>
<script>
const phoneInputFieldHostNo = document.querySelector("#hostNo");
const phoneInputHostNo = window.intlTelInput(phoneInputFieldHostNo, {
 preferredCountries: ['sg', 'my'],	
 utilsScript:
    "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/utils.js",
});
processedHostNo = document.querySelector("#processedHostNo");
function processHostNo(event) {
 event.preventDefault();

 const phoneNumberHostNo = phoneInputHostNo.getNumber();
 processedHostNo.value = phoneNumberHostNo;
}

</script>

</html>