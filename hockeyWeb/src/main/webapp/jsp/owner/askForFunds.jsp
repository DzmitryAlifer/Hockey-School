<%@ include file="../include/jsphead.jsp"%>

<html>
<head>
    <title> <fmt:message key="pagetitle.askforfunds" bundle="${rb}"/> </title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>

<%@ include file="../include/menuAdminOwner.jsp"%>

<h1 class="header"> <fmt:message key="askforfunds.header" bundle="${rb}"/> </h1>

<table width="80%" align="center" cellspacing="0" cellpadding="10">
    <tr><td><p align="justify"> <fmt:message key="askforfunds.note" bundle="${rb}"/> </p></td></tr>
    <tr><td> <fmt:message key="askforfunds.askfor" bundle="${rb}"/> </td></tr>
</table>
<hr>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="letter" >
    <%@ include file="../include/chooseObjective.jsp" %>
</form>
<br>
<%@ include file="../include/footer.jsp" %>

</body>
</html>