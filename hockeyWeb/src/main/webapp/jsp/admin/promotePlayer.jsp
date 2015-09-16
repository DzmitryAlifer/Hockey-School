<%@ include file="../include/jsphead.jsp"%>

<html>
<head>
    <title><fmt:message key="pagetitle.promoteplayer" bundle="${rb}"/></title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>

<%@ include file="../include/menuAdminOwner.jsp"%>

<h1 class="header"><fmt:message key="promoteplayer.header" bundle="${rb}"/></h1>

<b style="color:darkred">${nobodyToPromote}</b><br>
<b><fmt:message key="promoteplayer.note" bundle="${rb}"/></b><br><hr>

    <form action="Controller" method="post">
        <input type="hidden" name="command" value="promote_player_in_base">
        <%@ include file="../include/chooseSchoolPlayers.jsp" %>
    </form>

    <%@ include file="../include/footer.jsp" %>

</body>
</html>