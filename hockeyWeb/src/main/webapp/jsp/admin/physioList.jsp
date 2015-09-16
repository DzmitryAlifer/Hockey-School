<%@ include file="../include/jsphead.jsp"%>

<html>
<head>
    <title><fmt:message key="pagetitle.physiolist" bundle="${rb}"/></title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>

<jsp:include page="../include/menuAdminOwner.jsp"/>

<h1 class="header"><fmt:message key="physiolist.header" bundle="${rb}"/></h1>

<table width="70%" align="center">
    <tr>
        <th><fmt:message key="physiolist.firstname" bundle="${rb}"/></th>
        <th><fmt:message key="physiolist.lastname" bundle="${rb}"/></th>
        <th><fmt:message key="physiolist.speedinc" bundle="${rb}"/></th>
        <th><fmt:message key="physiolist.strengthinc" bundle="${rb}"/></th>
    </tr>
    <c:forEach var="physio" items="${physios}">
        <tr>
            <td> ${physio.firstName} </td>
            <td> ${physio.lastName} </td>
            <td>
                <c:if test="${previousCommand.equals(\"spend_money_result\") && physioGrows[physio.id][0] > 0}">
                    <b style="font-size: x-small">
                        (+${physioGrows[physio.id][0]})
                    </b>
                </c:if>
                <b> ${physio.speedInc} </b>
            </td>
            <td>
                <c:if test="${previousCommand.equals(\"spend_money_result\") && physioGrows[physio.id][1] > 0}">
                    <b style="font-size: x-small">
                        (+${physioGrows[physio.id][1]})
                    </b>
                </c:if>
                <b> ${physio.strengthInc} </b>
            </b></td>
        </tr>
    </c:forEach>
</table>

<%@ include file="../include/footer.jsp" %>

</body>
</html>