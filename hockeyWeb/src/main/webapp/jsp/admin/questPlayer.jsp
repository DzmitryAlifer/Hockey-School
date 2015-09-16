<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/jsphead.jsp"%>

<html>
<head>
    <title> <fmt:message key="pagetitle.questplayer" bundle="${rb}"/> </title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>

<c:set var="previousCommand" scope="session" value="quest_player_list"/>

<%@ include file="../include/menuAdminOwner.jsp"%>

<h1 class="header"> <fmt:message key="choosequest.title" bundle="${rb}"/> </h1>



<form action="Controller" method="post">
    <input type="hidden" name="command" value="quest_form">
    <br><b><hr><fmt:message key="choosequest.schoolnote" bundle="${rb}"/></b><br>
    <%@ include file="../include/chooseSchoolPlayer.jsp" %>
<%--    <br><b><hr><fmt:message key="choosequest.teamnote" bundle="${rb}"/></b><br>
    <%@ include file="../include/chooseTeamPlayer.jsp" %>--%>
</form>

<%@ include file="../include/footer.jsp" %>
<br>
</body>
</html>