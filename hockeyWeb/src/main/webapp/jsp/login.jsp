<%@ taglib prefix="ctg" uri="customtags" %>
<%@ include file="include/jsphead.jsp"%>

<html>
<head>
    <title><fmt:message key="pagetitle.login" bundle="${rb}"/></title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
<%@ include file="include/language.jsp"%>
<br><br>
<%@ include file="include/header.jsp"%>
<br>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="login" >
    <table cellspacing="6" cellpadding="6" class="block">
        <tr>
            <td colspan="2" class="header" align="center">
                <h1> <fmt:message key="login.loginpage" bundle="${rb}"/> </h1>
                <img src="../image/figures.png">
            </td>
        </tr>
        <tr>
            <td colspan="2" style="color: darkred">&nbsp;<b> ${loginWarning} </b></td>
        </tr>
        <tr>
            <td colspan="2"><b><fmt:message key="login.note" bundle="${rb}"/></b></td>
        </tr>
        <tr>
            <td><b><fmt:message key="login.login" bundle="${rb}"/></b></td>
            <td><input type="text" name="login" value="${enteredLogin}" class="rounded" required pattern="^\w[A-Za-z0-9_-]+"/></td>
        </tr>
        <tr>
            <td><b><fmt:message key="login.password" bundle="${rb}"/></b></td>
            <td><input type="password" name="password" value="${enteredPassword}" required pattern="[A-Za-z0-9]+"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="image" value=" " class="puckButton"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="button" value="<fmt:message key="login.hint" bundle="${rb}"/>"
                       onclick="return alert('<fmt:message key="login.hintheader" bundle="${rb}"/> \n\n <fmt:message key="login.hintowner" bundle="${rb}"/> \n <fmt:message key="login.hintadmin" bundle="${rb}"/> \n <fmt:message key="login.hintcoach" bundle="${rb}"/> \n <fmt:message key="login.hintphysio" bundle="${rb}"/>');" />
            </td>
        </tr>
    </table>
</form>

</body>
</html>