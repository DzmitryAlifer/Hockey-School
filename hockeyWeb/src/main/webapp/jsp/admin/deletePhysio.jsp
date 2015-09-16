<%@ include file="../include/jsphead.jsp"%>

<html>
<head>
    <title><fmt:message key="pagetitle.deletephysio" bundle="${rb}"/></title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>

<%@ include file="../include/menuAdminOwner.jsp"%>

<h1 class="header"><fmt:message key="deletephysio.header" bundle="${rb}"/></h1>

<b style="color:darkred">${nobodyToDelete}</b><br>
<b><fmt:message key="deletephysio.note" bundle="${rb}"/></b><br><hr>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="delete_physio_from_base">
    <table cellspacing="3" cellpadding="3" align="center">
        <tr>
            <th><fmt:message key="deletephysio.fullname" bundle="${rb}"/></th>
            <th><fmt:message key="deletephysio.speedinc" bundle="${rb}"/></th>
            <th><fmt:message key="deletephysio.strengthinc" bundle="${rb}"/></th>
        </tr>
        <c:forEach var="physio" items="${physios}">
            <tr>
                <td style="text-align: left">
                    <input type="checkbox" id="playerCheckbox${physio.getId()}" name="id" value="${physio.getId()}" />
                    <label for="playerCheckbox${physio.getId()}">&nbsp;${physio.getFirstName()}&nbsp;${physio.getLastName()}</label>
                </td>
                <td>${physio.getSpeedInc()}</td>
                <td>${physio.getStrengthInc()}</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="3"><br>
                <input type="image" value=" " class="puckButton" onclick="return confirm('<fmt:message key="jsmessage.deletephysio" bundle="${rb}"/>') ? true : false;" />
            </td>
        </tr>
    </table>
</form>

<%@ include file="../include/footer.jsp" %>

</body>
</html>