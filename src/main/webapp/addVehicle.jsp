<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="loginVMSCSS.jsp"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="org.apache.commons.collections.IteratorUtils"%>
<%@page import="com.google.gdata.data.spreadsheet.CellEntry"%>
<%@page import="com.google.gdata.data.spreadsheet.Cell"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="net.javatutorial.entity.*"%>
<%@page import="net.javatutorial.DAO.*"%>
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
		var tempAsStr = document.forms["addVehicle"]["temperature"].value;
		var tempAsInt = parseFloat(tempAsStr);
		if (isNaN(tempAsInt) || tempAsInt < 36 || tempAsInt > 37.5)  {
			alert("COVID Alert: Invalid temperature or temperature is high. Please go home if you are sick.");
			return false;
		}
	}
	function checkMobileNo() {
		 if (processedMobileNo.value.length == 0)
	     { 
	        alert("Please enter a valid mobile number. Try removing the spaces in the number.");  	
	        return false; 
	     }  	
	}	
	processedMobileNo = document.querySelector("#processedMobileNo");
	function getProcessedMobileNoOnLoad(event) {
		event.preventDefault();
		const phoneNumber = document.forms["addVehicle"]["mobileNo"].value;
		processedMobileNo.value = phoneNumber; 
	}
</script>
</head>
<body onload="getProcessedMobileNoOnLoad(event)">
	<div class="container body-content">
		<div class="page-header">
			<label class="heading">Vehicle Management System - Gate Pass</label>
			<br> <b>How to use:</b> Please enter Vehicle Details where
			relevant
			<%
 String idNo = "SxxxxxxxJ";
 String name = "";
 String status = "required";
 Vehicle v = null;
 ArrayList<Dropdown> vehiclePurpose = new ArrayList<Dropdown>();
 ArrayList<Dropdown> containerSize = new ArrayList<Dropdown>();
 ArrayList<Site> siteDropdown = new ArrayList<Site>();
 if (request.getAttribute("vehicleLatRec") != null) {
 	v = (Vehicle) request.getAttribute("vehicleLatRec");
 }
 if (request.getAttribute("status") != null) {
 	status = (String) request.getAttribute("status");
 }
 if (request.getAttribute("vehiclePurpose") != null) {
 	vehiclePurpose = (ArrayList<Dropdown>) request.getAttribute("vehiclePurpose");
 }
 if (request.getAttribute("containerSize") != null) {
 	containerSize = (ArrayList<Dropdown>) request.getAttribute("containerSize");
 }
 if (request.getAttribute("siteDropdown") != null) {
 	siteDropdown = (ArrayList<Site>) request.getAttribute("siteDropdown");
 }
 if (request.getSession(false).getAttribute("usertype") == null
 		&& request.getSession(false).getAttribute("idNo") != null) {
 	idNo = (String) request.getSession(false).getAttribute("idNo");
 	name = (String) request.getSession(false).getAttribute("name");
 }
 %>
			<center>
				<form action="addVehicle" method="post" name="addVehicle"
					onsubmit="return checkMobileNo()">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="name">Vehicle Driver Name (司机姓名): </label> <input
								type="text" class="form-control" name="name"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? name : v.getName())%>" <%=status%>>
						</div>
						<div class="form-group col-md-6">
							<label for="companyName">Company Name (公司名称): </label> <input
								type="text" class="form-control" name="companyName"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getCompanyName())%>" <%=status%>>
						</div>
						<div class="form-group col-md-6">
							<label for="site">Site/Warehouse Name (仓库名称): </label>
							<%
							if (status.equals("readonly")) {
							%>
							<select name="site" class="form-control" <%=status%>>
								<%
								for (Site d : siteDropdown) {
								%>
								<option value="<%=d.getSiteName()%>"
									<%=((v != null || v.getSite() != null) && v.getSite().equals(d.getSiteName()) ? "selected" : "")%>>
									<%=d.getSiteName()%></option>
								<%
								}
								%>
							</select>
							<%
							} else {
							%>
							<select name="site" class="form-control" <%=status%>>
								<option style="display: none;"></option>
								<%
								for (Site d : siteDropdown) {
								%>
								<option value="<%=d.getSiteName()%>">
									<%=d.getSiteName()%></option>
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
							<label for="idNo">Driver ID Number (身份证号码): </label> <input
								type="text" class="form-control" name="idNo"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? idNo : v.getIdNo())%>" minlength="4"
								maxlength="15" <%=status%>>
						</div>
						<div class="form-group col-md-6">
							<label for="mobileNo">Mobile No. (手机号码): </label> <input
								type="tel" class="form-control" id="mobileNo" name="mobileNo"
								onchange="processMobileNo(event)"
								value="<%=((v != null && v.getMobileNo() != null) ? v.getMobileNo() : "")%>"
								<%=status%>> <input type="hidden"
								id="processedMobileNo" name="processedMobileNo" />
						</div>
						<div class="form-group col-md-4">
							<label for="visitPurpose">Visit Purpose (目的): </label>
							<%
							if (v == null || v.getVisitPurpose() == null) {
							%>
							<select name="visitPurpose" class="form-control" <%=status%>>
								<option style="display: none;"></option>
								<%
								for (Dropdown d : vehiclePurpose) {
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
							<select name="visitPurpose" class="form-control" <%=status%>>
								<%
								for (Dropdown d : vehiclePurpose) {
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
							<label for="primeMoverNo">Vehicle/Primemover Number (车号):
							</label> <input type="text" class="form-control" name="primeMoverNo"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getPrimeMoverNo())%>"
								<%=status%>>
						</div>
						<div class="form-group col-md-6">
							<label for="containerNo">Container Number (集装箱号): </label> <input
								type="text" class="form-control" name="containerNo"
								oninput="this.value = this.value.toUpperCase()"
								value="<%=((v == null) ? "" : v.getContainerNo())%>"
								<%=status.equals("readonly") ? status : ""%>>
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<input type="checkbox" id="loadedNoLoaded" name="loadedNoLoaded"
								value="Yes"
								<%=(v != null) && v.getLoadedNoLoaded().equals("Yes") ? "checked" : ""%>
								<%=status.equals("readonly") ? "disabled" : ""%>> <label
								for="loadedNoLoaded"> Select if container is loaded.
								(选择若集装箱已满.) </label>
						</div>
						<div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="sealNo">Seal No (印章号码): </label> <input type="text"
										class="form-control" name="sealNo"
										oninput="this.value = this.value.toUpperCase()"
										value="<%=((v == null) ? "" : v.getSealNo())%>"
										<%=status.equals("readonly") ? status : ""%>>
								</div>
								<div class="form-group col-md-6">
									<label for="containerSize">Container Size (集装箱测村): </label>
									<%
									if (v == null || v.getContainerSize() == null || StringUtils.isEmpty(v.getContainerSize())) {
									%>
									<select name="containerSize" class="form-control"
										<%=status.equals("readonly") ? status : ""%>>
										<option style="display: none;"></option>
										<%
										for (Dropdown d : containerSize) {
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
									<select name="containerSize" class="form-control"
										<%=status.equals("readonly") ? status : ""%>>
										<%
										for (Dropdown d : containerSize) {
										%>
										<option value="<%=d.getDropdownValue()%>"
											<%=v.getContainerSize().equals(d.getDropdownValue()) ? "selected" : ""%>>
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
									<label for="lorryChetNumber">Lorry Chit Number (货车车牌号):
									</label> <input type="text" class="form-control" name="lorryChetNumber"
										oninput="this.value = this.value.toUpperCase()"
										value="<%=((v == null) ? "" : v.getLorryChetNumber())%>"
										<%=status.equals("readonly") ? status : ""%>>
								</div>
								<div class="form-group col-md-6">
									<label for="deliveryNoticeNumber">Delivery Notice
										Number (送货通知号): </label> <input type="text" class="form-control"
										name="deliveryNoticeNumber"
										oninput="this.value = this.value.toUpperCase()"
										value="<%=((v == null) ? "" : v.getDeliveryNoticeNumber())%>"
										<%=status.equals("readonly") ? status : ""%>>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="remarks">Remarks (其他): </label> <input type="text"
										class="form-control" name="remarks" id="remarks"
										value="<%=((v == null) ? "" : v.getRemarks())%>"
										<%=status.equals("readonly") ? status : ""%>>
								</div>
							</div>
							<%
							if (status.equals("readonly")) {
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
										value="<%=((v == null) ? "" : v.getLastModifiedBy())%>"
										readonly>
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
									<%=status.equals("readonly") ? "disabled" : ""%>>Submit
									Record</button>
								<a href="/vehms" class="btn btn-warning btn-lg active"
									role="button" aria-pressed="true">Back</a>
							</div>
							<br> <br>
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
</html>