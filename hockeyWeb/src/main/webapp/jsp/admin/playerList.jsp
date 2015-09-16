<%@ include file="../include/jsphead.jsp"%>

<html>
<head>
    <title><fmt:message key="pagetitle.playerlist" bundle="${rb}"/></title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
    <link rel="stylesheet" type="text/css" href="../../css/flags.css">
</head>
<body>
<c:set var="previousCommand" scope="session" value="player_list"/>

<%@ include file="../include/menuAdminOwner.jsp"%>

<%@ include file="../include/players.jsp"%>

<%@ include file="../include/footer.jsp" %>

</body>
</html>