<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/jsphead.jsp"%>

<html>
<head>
    <title><fmt:message key="pagetitle.addplayer" bundle="${rb}"/></title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>

<%@ include file="../include/menuAdminOwner.jsp"%>

<h1 class="header"><fmt:message key="addplayer.header" bundle="${rb}"/></h1>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="player_to_base">
    <table width="50%" cellspacing="6" cellpadding="6" align="center" cols="2">
        <tr>
            <td colspan="2">
                <b style="color:darkred">${invalidName}</b><br>
                <b><fmt:message key="addplayer.note" bundle="${rb}"/></b><hr>
            </td>
        </tr>
        <tr>
            <td><b><fmt:message key="addplayer.firstname" bundle="${rb}"/></b></td>
            <td><input type="text" name="firstName" placeholder="<fmt:message key="addplayer.firstplaceholder" bundle="${rb}"/>" required pattern="^[A-Za-z\u0410-\u042f\u0430-\u044f]{3,20}$"/></td>
        </tr>
        <tr>
            <td><b><fmt:message key="addplayer.lastname" bundle="${rb}"/></b></td>
            <td><input type="text"  name="lastName" placeholder="<fmt:message key="addplayer.lastplaceholder" bundle="${rb}"/>" required pattern="^[A-Za-z\u0410-\u042f\u0430-\u044f]{3,20}$"/></td>
        </tr>
        <tr>
            <td><b><fmt:message key="addplayer.defence" bundle="${rb}"/></b></td>
            <td><input type="range" name="defence" min="1" max="10"></td>
        </tr>
        <tr>
            <td><b><fmt:message key="addplayer.attack" bundle="${rb}"/></b></td>
            <td><input type="range" name="attack" min="1" max="10"></td>
        </tr>
        <tr>
            <td><b><fmt:message key="addplayer.speed" bundle="${rb}"/></b></td>
            <td><input type="range" name="speed" min="1" max="10"></td>
        </tr>
        <tr>
            <td><b><fmt:message key="addplayer.strength" bundle="${rb}"/></b></td>
            <td><input type="range" name="strength" min="1" max="10"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="image" value=" " class="puckButton"></td>
        </tr>
    </table>
</form>

<%--<form action="upload.php" method="post" enctype="multipart/form-data">
    <input type="file" name="photo" accept="image/png,image/jpeg"><br>
    <input type="submit" value="Загрузить">
</form>--%>

<%@ include file="../include/footer.jsp" %>

</body>
</html>