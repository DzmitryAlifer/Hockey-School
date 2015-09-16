<%@ include file="../include/jsphead.jsp"%>

<html>
<head>
    <title> <fmt:message key="pagetitle.budget" bundle="${rb}"/> </title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>

<%@ include file="../include/menuAdminOwner.jsp"%>

<h1 class="header"> <fmt:message key="budget.header" bundle="${rb}"/> </h1>
<br>

<table width="60%" align="center" cellpadding="5" cellspacing="0" style="font-size: smaller">
    <c:forEach var="transfer" items="${transfers}">
        <tr class="stripe">
            <td> ${transfer.date} </td>
            <td> ${transfer.operation} </td>
            <td><b style="font-size: smaller">
                <c:choose>
                    <c:when test="${transfer.sum > 0}">+</c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose>
                $ ${Math.abs(transfer.sum)}
            </b></td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="2" style="border-top: 1px dashed"><b> <fmt:message key="total" bundle="${rb}"/> </b></td>
        <td class="price" style="border-top: 1px dashed #000">
            <c:choose>
                <c:when test="${budgetAmount > 0}">+</c:when>
                <c:otherwise>-</c:otherwise>
            </c:choose>
            $ ${Math.abs(budgetAmount)}
        </td>
    </tr>
</table>

<br><br>

<%@ include file="../include/footer.jsp" %>

</body>
</html>