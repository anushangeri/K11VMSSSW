<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="loginVMSCSS.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.URL"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="net.javatutorial.entity.*"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://drvic10k.github.io/bootstrap-sortable/Contents/bootstrap-sortable.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.19.1/moment.js" type="text/javascript"></script>
<script src="https://drvic10k.github.io/bootstrap-sortable/Scripts/bootstrap-sortable.js" type="text/javascript"></script>
<link href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" />
<link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.2.1/css/buttons.dataTables.min.css" />
<script src="https://code.jquery.com/jquery-1.12.3.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.2.1/js/dataTables.buttons.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/buttons/1.2.1/js/buttons.html5.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/plug-ins/1.10.24/sorting/datetime-moment.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(document).ready(function() {
			$.fn.dataTable.moment('DD/MM/YYYY hh:mm:ss A');
			$('table').DataTable({
				"order": [[ 0, "desc" ]],
				dom : 'Blfrtip',
				buttons : [ {
					text : 'Export To Excel',
					extend : 'excelHtml5',
					exportOptions : {
						modifier : {
							selected : true
						},
						columns : [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17],
						format : {
							header : function(data, columnIdx) {
								return data;
							},
						}
					},
					footer : false,
					customize : function(xlsx) {
						var sheet = xlsx.xl.worksheets['sheet1.xml'];
					}
				} ],
				"order": [[14, 'desc']]
			});
		});
	});
	function showDiv(divId, element)
	{
	    document.getElementById(divId).style.display = element.value == "Y" ? 'block' : 'none';
	}
</script>
</head>
<body>
	<center>
	<%
		ArrayList<Visitor> vList = (ArrayList<Visitor>) request.getAttribute("vList");
		String message = (String) request.getAttribute("message");
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
		String idNo = "SxxxxxxxJ";
		String userType = "";
	 	if (request.getSession(false).getAttribute("idNo") != null) {
	 		idNo = (String) request.getSession(false).getAttribute("idNo");
	 		userType = (String) request.getSession(false).getAttribute("usertype");
	 	}
		if (message != null && !StringUtils.isEmpty(message)) {
	%>
		<label class="heading"><%=message%> </label><br>
		<a href="/vms?recordsToReceive=currdate" class="btn btn-warning btn-lg active"
				role="button" aria-pressed="true">Current Day</a>
		<a href="/vms?recordsToReceive=10days" class="btn btn-warning btn-lg active"
				role="button" aria-pressed="true">Last 10 Days</a>
		<a href="/vms?recordsToReceive=all" class="btn btn-warning btn-lg active"
				role="button" aria-pressed="true">All</a>
	</center>
		<% 
			if (vList != null && vList.size() > 0) {
		%>
			<div class="container body-content" id="tableview">
				<table id="example"
					class="table table-striped table-bordered table-sm sortable" style="width: 80%;">
					<thead>
						<tr>
							<th class="th-sm">S/N</th>
							<th class="th-sm">View</th>
							<th class="th-sm">Name</th>
							<th class="th-sm">Company Name</th>
							<th class="th-sm">Site Visited</th>
							<!-- if session access type is admin or staff i.e. there is a access type then display idno with hyperlink -->
							<%
							if (userType == null) {
							%>
							<th class="th-sm" style="display: none;">ID Number</th>
							<%
							} else {
							%>
							<th class="th-sm">ID Number <i>(Click to Add New Record)</i></th>
							<%
							}
							%>
							<th class="th-sm">Visitor Contact Number</th>
							<th class="th-sm">Vehicle Number</th>
							<th class="th-sm">Host Name</th>
							<th class="th-sm" style="display:none;">Host Contact Number</th>
							<th class="th-sm">Visitor Pass ID</th>
							<th class="th-sm">Remarks</th>
							<th class="th-sm">Purpose of Visit</th>
							<th class="th-sm">Approving Officer</th>
							<th class="th-sm">Time In</th>
							<th class="th-sm">Time Out</th>
							<th class="th-sm">Created By</th>
							<th class="th-sm">Last Modified By</th>
						</tr>
					</thead>
					<tbody>
						<%
							if (!vList.isEmpty()) {
								Iterator<Visitor> vListIter = vList.iterator();
								while (vListIter.hasNext()) {
									Visitor v = vListIter.next();
						%>
								<tr>
									<td><%=v.getVmsId()%></td>
									<td><form method="GET" action="/retrieveToPopulate">
										<input type="hidden" id="vmsId" name="vmsId"
											value="<%=v.getVmsId()%>"> <input type="hidden"
											id="status" name="status" value="readonly"> <input
											type="submit" name="Submit" value="View">
									</form></td>
									<td><%=v.getName()%></td>
									<td><%=v.getCompanyName()%></td>
									<td><%=((v.getSite() == null) ? "" : v.getSite())%></td>
									<!-- if session access type is admin or staff i.e. there is a access type then display idno with hyperlink -->
									<%
									if (userType == null) {
									%>
										<td style="display: none;"><%=v.getIdNo()%></td>
									<%
									} else {
									%>
										<td><a href="/retrieveToPopulate?idNo=<%=v.getIdNo()%>"><%=v.getIdNo()%></a></td>
									<%
									}
									%>
									<td><%=v.getMobileNo()%></td>
									<td><%=v.getVehicleNo()%></td>
									<td><%=v.getHostName()%></td>
									<td style="display:none;"><%=v.getHostNo()%></td>
									<td><%=v.getVisitorCardId()%></td>
									<td>
										<p><%=(v.getRemarks() != null ? v.getRemarks() : "No Remarks Yet")%></p>
										<select id = "ddlRemarks" onchange="showDiv('dvRemarks<%=v.getVmsId()%>', this)">
									        <option value="N">No Edit Remarks</option>
									        <option value="Y">Yes Edit Remarks</option>            
									    </select>
									    <hr />
										<div id="dvRemarks<%=v.getVmsId()%>" style="display: none">
											<form method="POST" action ="/updateVisitorRemarks">
												<input type="hidden" id="vmsId" name="vmsId" value="<%=v.getVmsId()%>">
												<input type="text" class="form-control" name="remarks"
												oninput="this.value = this.value.toUpperCase()">
												<input type="submit" name="Submit" value="Update">
											</form>
										</div>
									</td>
									<td><%=v.getVisitPurpose()%></td>
									<% if (v.getApprovingOfficer() != null && !v.getApprovingOfficer().equalsIgnoreCase("null")) { %>
										<td><%=v.getApprovingOfficer()%></td>
									<%
										}
										else if(userType != null && (userType.equalsIgnoreCase("OFFICER") || userType.toUpperCase().equals("ADMIN")) ){
									%>
										<td><form method="POST" action ="/approveVisitor">
											<input type="hidden" id="vmsId" name="vmsId" value="<%=v.getVmsId()%>">
											<input type="submit" name="Submit" value="Approve"></form></td>
									<%
										}
										else {
											%>
											<td>None</td>
											<%
										}
									%>
									<td><%=sdf.format(v.getTimeInDt())%></td>
									<!-- TO DO: if timeout is null - send to update servlet to update with system time -->
									<% if (v.getTimeOutDt() != null) { %>
										<td><%=sdf.format(v.getTimeOutDt())%></td>
									<%
										}
										else{
									%>
										<td><form method="POST" action ="/updateVisitor">
											<input type="hidden" id="vmsId" name="vmsId" value="<%=v.getVmsId()%>">
											<input type="submit" name="Submit" value="Update"></form></td>
									<%
										}
									%>
									<td><%=(v.getCreatedBy() != null ? v.getCreatedBy() : "")%></td>
									<td><%=(v.getLastModifiedBy() != null ? v.getLastModifiedBy() : "")%></td>
								</tr>
							<%
								}
							%>
			
						<%
							}
						%>
					</tbody>
				</table>
		<%
			}
		%>
	<%
		}
	%>
	</div>
		<div class="container body-content">
			<center>
				<a href="retrieveToPopulate" class="btn btn-warning btn-lg active"
				role="button" aria-pressed="true">Add Visitor Record</a>
				
				<!-- Delete all record function is for K11 Admin only -->
				<%if (request.getSession(false).getAttribute("usertype") != null) { %>
					<a href="/resetPassword.jsp" class="btn btn-warning btn-lg active"
					role="button" aria-pressed="true">Change Password</a>
					
					<% if(!userType.equals("WAREHOUSE")){  %>
						<a href="/clientMain.jsp" class="btn btn-warning btn-lg active" role="button"
						aria-pressed="true">Back</a>
					
						<% 
						String usertype = (String) request.getSession(false).getAttribute("usertype");
						if (usertype.toUpperCase().equals("ADMIN")){ %>
							<a href="deleteAllVisitor" class="btn btn-warning btn-lg active"
							role="button" aria-pressed="true">Delete Visitor Record</a>
							
							<a href="managedatabase.jsp" class="btn btn-warning btn-lg active"
							role="button" aria-pressed="true">Manage Visitor Database</a>
						<%	
						}
					}
				%>
				<% 
				}
				else { 
				%>
				
				<a href="/index.jsp" class="btn btn-warning btn-lg active" role="button"
							aria-pressed="true">Back</a>
				<%}%>
			</center>
		</div>
	<br>
	
</body>
</html>