<%@ include file="../include/jsphead.jsp"%>

<html>
<head>
    <title><fmt:message key="pagetitle.setplayerprice" bundle="${rb}"/></title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <script src="/js/script.js"></script>
</head>
<body>

<%@ include file="../include/menuAdminOwner.jsp"%>

<h1 class="header"><fmt:message key="setplayerprice.header" bundle="${rb}"/></h1>
<b><fmt:message key="setplayerprice.note" bundle="${rb}"/></b><br><hr>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="choose_player_price">
    <%@ include file="../include/chooseTeamPlayer.jsp" %>
</form>

<%@ include file="../include/footer.jsp" %>

</body>
</html>