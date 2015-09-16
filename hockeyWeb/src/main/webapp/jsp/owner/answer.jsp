<%@ include file="../include/jsphead.jsp"%>

<html>
<head>
    <title> <fmt:message key="pagetitle.answer" bundle="${rb}"/> </title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>

<%@ include file="../include/menuAdminOwner.jsp"%>

<h1 class="header"> <fmt:message key="answer.header" bundle="${rb}"/> </h1>

<c:choose>
    <c:when test="${answer == true}">
        <fmt:message key="answer.yes1" bundle="${rb}"/><br>
        <fmt:message key="answer.yes2" bundle="${rb}"/>${money}<br><br>
        <b>${objective}</b><br><fmt:message key="answer.yes3" bundle="${rb}"/><br>
    </c:when>
    <c:otherwise>
        <fmt:message key="answer.no" bundle="${rb}"/>
    </c:otherwise>
</c:choose>
<hr>


<br>
<%@ include file="../include/footer.jsp" %>

</body>
</html>