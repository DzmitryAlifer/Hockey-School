<%@ include file="../include/jsphead.jsp"%>

<html>
<head>
    <title><fmt:message key="pagetitle.addadmin" bundle="${rb}"/></title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>

<%@ include file="../include/menuAdminOwner.jsp"%>

<h1 class="header"><fmt:message key="addadmin.header" bundle="${rb}"/></h1>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="admin_to_base" >
    <table width="50%" cellspacing="6" cellpadding="6" align="center">
        <tr>
            <td colspan="2">
                <b style="color:darkred"> ${nvalidName} ${invalidLogin} ${differentPasswords} </b><br>
                <b><fmt:message key="addadmin.note" bundle="${rb}"/></b><hr>
            </td>
        </tr>
        <tr>
            <td><b><fmt:message key="addadmin.firstname" bundle="${rb}"/></b></td>
            <td><input type="text" name="newFirstName" value="${adminFirstName}" placeholder="<fmt:message key="addadmin.firstplaceholder" bundle="${rb}" />" required pattern="^[A-aZ-z\u0410-\u0430\u042f-\u044f]{3,20}$"/></td>
        </tr>
        <tr>
            <td><b><fmt:message key="addadmin.lastname" bundle="${rb}"/></b></td>
            <td><input type="text" name="newLastName" value="${adminLastName}" placeholder="<fmt:message key="addadmin.lastplaceholder" bundle="${rb}"/>" required pattern="^[A-aZ-z\u0410-\u0430\u042f-\u044f]{3,20}$"/></td>
        </tr>
        <tr>
            <td><b><fmt:message key="addadmin.login" bundle="${rb}"/></b></td>
            <td><input type="text" name="newAdminLogin" value="${adminLogin}" placeholder="<fmt:message key="addadmin.loginplaceholder" bundle="${rb}"/>" required pattern="^\w[A-Za-z0-9_-]+"/></td>
        </tr>
        <tr>
            <td><b><fmt:message key="addadmin.password" bundle="${rb}"/></b></td>
            <td><input type="password" name="newAdminPassword" placeholder="<fmt:message key="addadmin.passwordplaceholder" bundle="${rb}"/>" required pattern="[A-Za-z0-9]+"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="password" name="newAdminPasswordCopy" placeholder="<fmt:message key="passwordcopyplaceholder" bundle="${rb}"/>" required pattern="[A-Za-z0-9]+"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="image" value=" " class="puckButton"></td>
        </tr>
    </table>
</form>

<%@ include file="../include/footer.jsp" %>

</body>
</html>