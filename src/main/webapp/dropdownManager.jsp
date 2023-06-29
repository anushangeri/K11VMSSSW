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
						columns : [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14],
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
		ArrayList<Dropdown> vList = (ArrayList<Dropdown>) request.getAttribute("vList");
		String message = (String) request.getAttribute("message");
		
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
							<th class="th-sm">Dropdown Key</th>
							<th class="th-sm">Dropdown Value</th>
							<th class="th-sm">Created Date</th>
							<th class="th-sm">Modified Date</th>
							<th class="th-sm">Delete</th>
						</tr>
					</thead>
					<tbody>
						<%
							if (!vList.isEmpty()) {
								Iterator<Dropdown> vListIter = vList.iterator();
								while (vListIter.hasNext()) {
									Dropdown v = vListIter.next();
						%>
								<tr>
									<td><%=v.getDropdownId()%></td>
									<td><%=v.getDropdownKey()%></td>
									<td><%=v.getDropdownValue()%></td>
									<td><%=v.getCreatedDt()%></td>
									<td><%=v.getLastModifiedDt()%></td>
									<td>
										<form method="POST" action="/deleteDropdownbyDropdownId">
											<input type="hidden" id="dropdownId" name="dropdownId"
												value="<%=v.getDropdownId()%>"> 
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
				<a href="addDropdown.jsp" class="btn btn-warning btn-lg active"
					role="button" aria-pressed="true">Create Dropdown Record</a>
				<a href="clientMain.jsp" class="btn btn-warning btn-lg active"
					role="button" aria-pressed="true">Back</a>
			</center>
		</div>
	<br>
	
</body>
</html>