<%@ include file="../include/jsphead.jsp"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<html>
<head>
    <title><fmt:message key="pagetitle.coachplayers" bundle="${rb}"/></title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
    <link rel="stylesheet" type="text/css" href="../../css/flags.css">
</head>
<body>

<c:set var="previousCommand" scope="session" value="player_list"/>

<%@ include file="../include/menuCoachPhysio.jsp"%>

<%@ include file="../include/players.jsp"%>

<%@ include file="../include/footer.jsp" %>

</body>
</html>
