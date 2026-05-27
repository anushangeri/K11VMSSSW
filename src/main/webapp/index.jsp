<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="loginVMSCSS.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>System Selection</title>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
    <script src="https://drvic10k.github.io/bootstrap-sortable/Scripts/bootstrap-sortable.js" type="text/javascript"></script>
</head>
<body class="bg-light">

<%
    session.removeAttribute("usertype");
    session.removeAttribute("name");
    session.removeAttribute("idNo");
%>

<div class="container text-center" style="margin-top: 100px;">
    <div class="col-md-6 col-sm-8 col-xs-10 center-block" style="float:none;">

        <a href="vmsDashboard.jsp" class="btn btn-info btn-lg text-white w-100 mb-4">
            <strong>Visitor Management System</strong>
        </a>

        <a href="clockingMain.jsp" class="btn btn-info btn-lg text-white w-100">
            <strong>K11 Clocking System</strong>
        </a>

    </div>
</div>

</body>
</html>