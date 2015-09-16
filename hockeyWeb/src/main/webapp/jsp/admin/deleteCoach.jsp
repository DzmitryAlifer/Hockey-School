<%@ include file="../include/jsphead.jsp"%>

<html>
<head>
    <title><fmt:message key="pagetitle.deletecoach" bundle="${rb}"/></title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>

<%@ include file="../include/menuAdminOwner.jsp"%>

<h1 class="header"><fmt:message key="deletecoach.header" bundle="${rb}"/></h1>

<b style="color:darkred">${nobodyToDelete}</b><br>
<b><fmt:message key="deletecoach.note" bundle="${rb}"/></b><br><hr>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="delete_coach_from_base">
    <table cellspacing="3" cellpadding="3" align="center">
        <tr>
            <th><fmt:message key="deletecoach.fullname" bundle="${rb}"/></th>
            <th><fmt:message key="deletecoach.defenceinc" bundle="${rb}"/></th>
            <th><fmt:message key="deletecoach.attackinc" bundle="${rb}"/></th>
        </tr>
        <c:forEach var="coach" items="${coaches}">
            <tr>
                <td style="text-align: left">
                    <input type="checkbox" id="playerCheckbox${coach.getId()}" name="id" value="${coach.getId()}" />
                    <label for="playerCheckbox${coach.getId()}">&nbsp;${coach.getFirstName()}&nbsp;${coach.getLastName()}</label>
                </td>
                <td>${coach.getDefenceInc()}</td>
                <td>${coach.getAttackInc()}</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="3"><br><input type="image" value=" " class="puckButton" onclick="return confirm('<fmt:message key="jsmessage.deletecoach" bundle="${rb}"/>') ? true : false;" /></td>
        </tr>
    </table>
</form>

<%@ include file="../include/footer.jsp" %>

</body>
</html>