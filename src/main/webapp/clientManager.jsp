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
<script type="text/javascript">
	$(document).ready(function() {
		$(document).ready(function() {
			$('table').DataTable({
				dom : 'Blfrtip',
				buttons : [ {
					text : 'Export To Excel',
					extend : 'excelHtml5',
					exportOptions : {
						modifier : {
							selected : true
						},
						columns : [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
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
				} ]
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
		ArrayList<ClientAccount> vList = (ArrayList<ClientAccount>) request.getAttribute("vList");
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
	</center>
		<% 
			if (vList != null && vList.size() > 0) {
		%>
			<div class="container body-content" id="tableview">
				<table id="example"
					class="table table-striped table-bordered table-sm sortable">
					<thead>
						<tr>
							<th class="th-sm">S/N</th>
							<th class="th-sm">Name</th>
							<th class="th-sm">Site</th>
							<th class="th-sm">ID Type</th>
							<th class="th-sm">ID Number</th>
							<th class="th-sm">Access Type</th>
							<th class="th-sm">Created Date</th>
							<th class="th-sm">Modified Date</th>
							<th class="th-sm">Override Password</th>
							<th class="th-sm">Delete</th>
						</tr>
					</thead>
					<tbody>
						<%
							if (!vList.isEmpty()) {
								Iterator<ClientAccount> vListIter = vList.iterator();
								while (vListIter.hasNext()) {
									ClientAccount v = vListIter.next();
						%>
								<tr>
									<td><%=v.getAccountId()%></td>
									<td><%=v.getName()%></td>
									<td><%=(v.getSite() != null &&  v.getSite().length > 0 ? Arrays.toString(v.getSite()) : "")%></td>
									<td><%=v.getIdType()%></td>
									<td><%=v.getIdNo()%></td>
									<td><%=v.getAccessType()%></td>
									<td><%=v.getCreatedDt()%></td>
									<td><%=v.getModifiedDt()%></td>
									<td>
										<form method="POST" action ="/overridePassword">
											<input type="hidden" id="idNo" name="idNo" value="<%=v.getIdNo()%>">
											<input type="submit" name="Submit" value="Override Password">
										</form>
									</td>
									<td>
										<form method="POST" action ="/deleteClientRecord">
											<input type="hidden" id="accountId" name="accountId" value="<%=v.getAccountId()%>">
											<input type="submit" name="Submit" value="Delete">
										</form>
									</td>
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
				<a href="/retrieveDropdownForClientForm" class="btn btn-warning btn-lg active"
					role="button" aria-pressed="true">Create Client Record</a>
				<a href="clientMain.jsp" class="btn btn-warning btn-lg active"
					role="button" aria-pressed="true">Back</a>
			</center>
		</div>
	<br>
	
</body>
</html>