<%@ include file="../include/jsphead.jsp"%>

<html>
<head>
    <title><fmt:message key="pagetitle.deleteplayer" bundle="${rb}"/></title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>

<%@ include file="../include/menuAdminOwner.jsp"%>

<h1 class="header"><fmt:message key="deleteplayer.header" bundle="${rb}"/></h1>

<b style="color:darkred">${nobodyToDelete}</b><br>
<b><fmt:message key="deleteplayer.note" bundle="${rb}"/></b><br><hr>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="delete_player_from_base">
    <%@ include file="../include/chooseSchoolPlayers.jsp" %>
</form>

<%@ include file="../include/footer.jsp" %>

</body>
</html>