<%@ include file="../include/jsphead.jsp"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<html>
<head>
    <title><fmt:message key="pagetitle.coachplayertrain" bundle="${rb}"/></title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>

<c:set var="previousCommand" scope="session" value="coach_player_train"/>

<%@ include file="../include/menuCoachPhysio.jsp"%>

<h1 class="header"><fmt:message key="playerlist.school" bundle="${rb}"/></h1><br>
<b> <fmt:message key="playertrain.note" bundle="${rb}"/> </b><hr>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="coach_do_player_train">
    <%@ include file="../include/chooseSchoolPlayer.jsp" %>
</form>

<%@ include file="../include/footer.jsp" %>

</body>
</html>
