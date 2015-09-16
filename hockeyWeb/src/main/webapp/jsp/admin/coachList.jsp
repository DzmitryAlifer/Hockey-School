<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/jsphead.jsp"%>

<html>
<head>
    <title><fmt:message key="pagetitle.coachlist" bundle="${rb}"/></title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>

<%@ include file="../include/menuAdminOwner.jsp" %>

<h1 class="header"><fmt:message key="coachlist.header" bundle="${rb}"/></h1>

<table width="70%" align="center">
    <tr>
        <th><fmt:message key="coachlist.firstname" bundle="${rb}"/></th>
        <th><fmt:message key="coachlist.lastname" bundle="${rb}"/></th>
        <th><fmt:message key="coachlist.defenceinc" bundle="${rb}"/></th>
        <th><fmt:message key="coachlist.attackinc" bundle="${rb}"/></th>
    </tr>
    <c:forEach var="coach" items="${coaches}">
        <tr>
            <td> ${coach.firstName} </td>
            <td> ${coach.lastName} </td>
            <td>
                <c:if test="${previousCommand.equals(\"spend_money_result\") && coachGrows[coach.id][0] > 0}">
                    <b style="font-size: x-small">
                        +(${coachGrows[coach.id][0]})
                    </b>
                </c:if>
                <b> ${coach.defenceInc} </b>
            </td>
            <td>
                <c:if test="${previousCommand.equals(\"spend_money_result\") && coachGrows[coach.id][1] > 0}">
                    <b style="font-size: x-small">
                        (+${coachGrows[coach.id][1]})
                    </b>
                </c:if>
                <b> ${coach.attackInc} </b>
                </b></td>
        </tr>
    </c:forEach>
</table>

<%@ include file="../include/footer.jsp" %>

</body>
</html>