<%@ include file="../include/jsphead.jsp"%>

<html>
<head>
    <title><fmt:message key="pagetitle.spendmoney" bundle="${rb}"/></title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>

<%@ include file="../include/menuAdminOwner.jsp"%>

<h1 class="header"><fmt:message key="spendmoney.header" bundle="${rb}"/></h1>
<b style="color: darkred"> ${warning} </b> <br><br>
<fmt:message key="spendmoney.note1" bundle="${rb}"/>
<b class="price">
    <fmt:message key="dollar" bundle="${rb}"/>
    ${budget}
</b><br>
<fmt:message key="spendmoney.note2" bundle="${rb}"/><br>
<hr>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="spend_money_result" >
    <%@ include file="../include/chooseObjective.jsp" %>
</form>
<br>
<%@ include file="../include/footer.jsp" %>

</body>
</html>