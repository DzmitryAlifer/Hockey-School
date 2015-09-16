<%@ include file="../include/jsphead.jsp"%>

<html>
<head>
    <title><fmt:message key="pagetitle.addcoach" bundle="${rb}"/></title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>

<%@ include file="../include/menuAdminOwner.jsp"%>

<h1 class="header"><fmt:message key="addcoach.header" bundle="${rb}"/></h1>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="coach_to_base">
    <table width="50%" cellspacing="6" cellpadding="6" align="center" cols="2">
        <tr>
            <td colspan="2">
                <b style="color:darkred"> ${invalidName} ${invalidLogin}  ${differentPasswords} </b><br>
                <b><fmt:message key="addcoach.note" bundle="${rb}"/></b><hr>
            </td>
        </tr>
        <tr>
            <td><b><fmt:message key="addcoach.firstname" bundle="${rb}"/></b></td>
            <td><input type="text" name="firstName" value="${coachFirstName}" placeholder="<fmt:message key="addcoach.firstplaceholder" bundle="${rb}"/>" required pattern="^[A-aZ-z\u0410-\u0430\u042f-\u044f]{3,20}$"/></td>
        </tr>
        <tr>
            <td><b><fmt:message key="addcoach.lastname" bundle="${rb}"/></b></td>
            <td><input type="text" name="lastName" value="${coachLastName}" placeholder="<fmt:message key="addcoach.lastplaceholder" bundle="${rb}"/>" required pattern="^[A-aZ-z\u0410-\u0430\u042f-\u044f]{3,20}$"/></td>
        </tr>
        <tr>
            <td><b><fmt:message key="addcoach.defenceinc" bundle="${rb}"/></b></td>
            <td><input type="range" name="defenceInc" min="1" max="10"></td>
        </tr>
        <tr>
            <td><b><fmt:message key="addcoach.attackinc" bundle="${rb}"/></b></td>
            <td><input type="range" name="attackInc" min="1" max="10"></td>
        </tr>
        <tr>
            <td><b><fmt:message key="addcoach.login" bundle="${rb}"/></b></td>
            <td><input type="text" name="newCoachLogin" value="${coachLogin}" placeholder="<fmt:message key="addcoach.loginplaceholder" bundle="${rb}"/>" required pattern="^\w[A-Za-z0-9_-]+"/></td>
        </tr>
        <tr>
            <td><b><fmt:message key="addcoach.password" bundle="${rb}"/></b></td>
            <td><input type="password" name="newCoachPassword" placeholder="<fmt:message key="addcoach.passwordplaceholder" bundle="${rb}"/>" required pattern="[A-Za-z0-9]+"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="password" name="newCoachPasswordCopy" placeholder="<fmt:message key="passwordcopyplaceholder" bundle="${rb}"/>" required pattern="[A-Za-z0-9]+"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="image" value=" " class="puckButton"></td>
        </tr>
    </table>
</form>

<%@ include file="../include/footer.jsp" %>

</body>
</html>