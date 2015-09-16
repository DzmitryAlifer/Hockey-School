<%@ include file="../include/jsphead.jsp"%>

<html>
<head>
    <title><fmt:message key="pagetitle.adminlist" bundle="${rb}"/></title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>

<%@ include file="../include/menuAdminOwner.jsp"%>

<h1 class="header"><fmt:message key="adminlist.header" bundle="${rb}"/></h1>
<b style="color:darkred">${selfDelete}</b><hr>
<table width="70%" align="center" cellpadding="3" cellspacing="3">
    <c:forEach var="currentAdmin" items="${admins}">
        <tr>
            <td>
                ${currentAdmin.getFirstName()} ${currentAdmin.getLastName()}
            </td>
        </tr>
    </c:forEach>
</table>

<%@ include file="../include/footer.jsp" %>

</body>
</html>