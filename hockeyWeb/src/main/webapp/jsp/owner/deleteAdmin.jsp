<%@ include file="../include/jsphead.jsp"%>

<html>
<head>
    <title><fmt:message key="pagetitle.deleteadmin" bundle="${rb}"/></title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <script src="/js/script.js"></script>
</head>
<body>

<%@ include file="../include/menuAdminOwner.jsp"%>

<h1 class="header"><fmt:message key="deleteadmin.header" bundle="${rb}"/></h1>

<b style="color:darkred">${nobodyToDelete}&nbsp;${selfDelete}</b><br>
<b><fmt:message key="deleteadmin.note" bundle="${rb}"/></b><br><hr>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="delete_admin_from_base" >
    <table cellspacing="3" cellpadding="3" align="center">
        <c:forEach var="admin" items="${admins}">
            <tr>
                <td style="text-align: left">
                    <input type="checkbox" id="adminCheckbox${admin.getId()}" name="id" value="${admin.getId()}" />
                    <label for="adminCheckbox${admin.getId()}">&nbsp;${admin.getFirstName()}&nbsp;${admin.getLastName()}</label>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="2"><br><input type="image" value=" " class="puckButton" onclick="return confirm('<fmt:message key="jsmessage.deleteadmin" bundle="${rb}"/>') ? true : false;" /></td>
        </tr>
    </table>
</form>

<%@ include file="../include/footer.jsp" %>

</body>
</html>