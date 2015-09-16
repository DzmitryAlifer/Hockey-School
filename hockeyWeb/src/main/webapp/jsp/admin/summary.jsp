<%@ include file="../include/jsphead.jsp"%>

<html>
<head>
    <title><fmt:message key="pagetitle.summary" bundle="${rb}"/></title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>

<c:set var="previousCommand" scope="session" value="summary"/>
<%@ include file="../include/menuAdminOwner.jsp"%>

<h1 class="header"><fmt:message key="summary.title" bundle="${rb}"/></h1>
<table align="center">
    <tr>
        <td>
            <h2><fmt:message key="summary.players" bundle="${rb}"/></h2>
        </td>
        <td>
            ${schoolPlayerCount} <fmt:message key="summary.schoolplayers" bundle="${rb}"/> ${averSchoolRate}
            <br>
            ${teamPlayerCount} <fmt:message key="summary.teamplayers" bundle="${rb}"/> ${averTeamRate}
        </td>
    </tr>
    <tr><td colspan="2"><hr></td></tr>
    <tr>
        <td>
            <h2><fmt:message key="summary.coaches" bundle="${rb}"/></h2>
        </td>
        <td>
            ${coachCount} <fmt:message key="summary.coachesinfo" bundle="${rb}"/> ${averCoachRate}
        </td>
    </tr>
    <tr><td colspan="2"><hr></td></tr>
    <tr>
        <td>
            <h2><fmt:message key="summary.physios" bundle="${rb}"/></h2>
        </td>
        <td>
            ${physioCount} <fmt:message key="summary.physiosinfo" bundle="${rb}"/> ${averPhysioRate}
        </td>
    </tr>
    <tr><td colspan="2"><hr></td></tr>
    <tr>
        <td>
            <h2><fmt:message key="summary.admins" bundle="${rb}"/></h2>
        </td>
        <td>
            ${adminCount} <fmt:message key="summary.adminsinfo" bundle="${rb}"/>
        </td>
    </tr>
    <c:if test="${role.equals(\"owner\")}">
        <tr><td colspan="2"><hr></td></tr>
        <tr>
            <td>
                <h2><fmt:message key="summary.saleincome" bundle="${rb}"/></h2>
            </td>
            <td>
                <fmt:message key="summary.saleincomeinfo" bundle="${rb}"/>${totalIncome}
            </td>
        </tr>
    </c:if>
</table>
<br><br>
<%@ include file="../include/footer.jsp" %>

</body>
</html>