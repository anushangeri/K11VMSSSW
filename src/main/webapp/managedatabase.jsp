<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="loginVMSCSS.jsp"%>
<%@page import="java.util.*"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.URL"%>
<%@page import="com.google.gdata.util.ServiceException"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css"></style>
</head>
<body>
 <%
	        String responseObj = (String) request.getAttribute("responseObj");
	        if (responseObj != null) {
	            %>
	            <label class="heading"><%=responseObj%></label>
	            <%
	        }
  %>
	<table class="table">
	  <thead>
	    <tr>
	      <th scope="col">#</th>
	      <th scope="col">Table Name</th>
	      <th scope="col">Create</th>
	      <th scope="col">Update</th>
	      <th scope="col">Delete</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <th scope="row">1</th>
	      <td>Employee</td>
	      <td><form action="createEmpTbl" method="post">
                  <button class="btn" type="submit">Create</button>
              </form>
          </td>
	      <td><form action="updateEmpTbl" method="post">
                  <button class="btn" type="submit">Update</button>
              </form>
          </td>
	      <td><form action="deleteEmpTbl" method="post">
                  <button class="btn" type="submit">Delete</button>
              </form>
          </td>
	    </tr>
	    <tr>
	      <th scope="row">2</th>
	      <td>Visitor Management System (VMS)</td>
	      <td><form action="createVmsTbl" method="post">
                  <button class="btn" type="submit">Create</button>
              </form>
          </td>
	      <td><form action="updateVmsTbl" method="post">
                  <button class="btn" type="submit">Update</button>
              </form>
          </td>
	      <td><form action="deleteVmsTbl" method="post">
                  <button class="btn" type="submit">Delete</button>
              </form>
          </td>
	    </tr>
	    <tr>
	      <th scope="row">3</th>
	      <td>Vehicle Movement System</td>
	      <td><form action="createVehmsTbl" method="post">
                  <button class="btn" type="submit">Create</button>
              </form>
          </td>
	      <td><form action="updateVehmsTbl" method="post">
                  <button class="btn" type="submit">Update</button>
              </form>
          </td>
	      <td><form action="deleteVehmsTbl" method="post">
                  <button class="btn" type="submit">Delete</button>
              </form>
          </td>
	    </tr>
	    <tr>
	      <th scope="row">4</th>
	      <td>Client Account</td>
	      <td><form action="clientAccountTbl" method="post">
                  <button class="btn" type="submit">Create</button>
              </form>
          </td>
	      <td><form action="updateClientAccountTbl" method="post">
                  <button class="btn" type="submit">Update</button>
              </form>
          </td>
	      <td><form action="-" method="post">
                  <button class="btn" type="submit">Delete</button>
              </form>
          </td>
	    </tr>
	   	<tr>
	      <th scope="row">5</th>
	      <td>Site</td>
	      <td><form action="createSiteTbl" method="post">
                  <button class="btn" type="submit">Create</button>
              </form>
          </td>
	      <td><form action="updateSiteTbl" method="post">
                  <button class="btn" type="submit">Update</button>
              </form>
          </td>
	      <td><form action="-" method="post">
                  <button class="btn" type="submit">Delete</button>
              </form>
          </td>
	    </tr>
	    <tr>
	      <th scope="row">6</th>
	      <td>VMS Archived</td>
	      <td><form action="createVmsArchivedTbl" method="post">
                  <button class="btn" type="submit">Create</button>
              </form>
          </td>
	      <td><form action="updateVmsArchivedTbl" method="post">
                  <button class="btn" type="submit">Update</button>
              </form>
          </td>
	      <td><form action="-" method="post">
                  <button class="btn" type="submit">Delete</button>
              </form>
          </td>
	    </tr>
	    <tr>
	      <th scope="row">7</th>
	      <td>VehicleMS Archived</td>
	      <td><form action="createVehMSArchivedTbl" method="post">
                  <button class="btn" type="submit">Create</button>
              </form>
          </td>
	      <td><form action="updateVehmsArchivedTbl" method="post">
                  <button class="btn" type="submit">Update</button>
              </form>
          </td>
	      <td><form action="-" method="post">
                  <button class="btn" type="submit">Delete</button>
              </form>
          </td>
	    </tr>
	    <tr>
	      <th scope="row">8</th>
	      <td>Dropdown List</td>
	      <td><form action="createDropdownTbl" method="post">
                  <button class="btn" type="submit">Create</button>
              </form>
          </td>
	      <td><form action="-" method="post">
                  <button class="btn" type="submit">Update</button>
              </form>
          </td>
	      <td><form action="-" method="post">
                  <button class="btn" type="submit">Delete</button>
              </form>
          </td>
	    </tr>
	    <tr>
	      <th scope="row">9</th>
	      <td>Clocking</td>
	      <td><form action="createClockingTbl" method="post">
                  <button class="btn" type="submit">Create</button>
              </form>
          </td>
          <td><form action="updateClockingTbl" method="post">
                  <button class="btn" type="submit">Update</button>
              </form>
          </td>
	    </tr>
	  </tbody>
	</table>
</body>
</html>