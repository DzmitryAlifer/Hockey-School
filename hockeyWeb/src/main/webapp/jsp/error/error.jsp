<%@ include file="../include/jsphead.jsp"%>

<html>
<head>
    <title><fmt:message key="pagetitle.error" bundle="${rb}"/></title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>
<br><br>
<form action="Controller">
    <table width="60%" cellspacing="0" cellpadding="15" class="block" style="opacity: 0.9">
        <tr>
            <td class="header">
                <h1><fmt:message key="error" bundle="${rb}"/> ${pageContext.errorData.statusCode} </h1>
            </td>
        </tr>
        <tr>
            <td>
                <fmt:message key="errorpage.note" bundle="${rb}"/> <br><br>
                &nbsp;<img src="../../image/skull.png">&nbsp;
            </td>
        </tr>
        <tr>
            <td style="text-align: left">
                <span style="font-size: smaller">
                    <b> <fmt:message key="errorpage.servlet" bundle="${rb}"/> </b> ${pageContext.errorData.servletName} <br>
                    <b> <fmt:message key="errorpage.exception" bundle="${rb}"/> </b> ${pageContext.exception} <br>
                    <b> <fmt:message key="errorpage.message" bundle="${rb}"/> </b> ${pageContext.exception.message} <br>
                </span>
            </td>
        </tr>
        <tr>
            <td><u><a href="index.jsp"><fmt:message key="errorpage.goto" bundle="${rb}"/></a></u><br></td>
        </tr>
    </table>
</form>

<%@ include file="/jsp/include/footer.jsp" %>

</body>
</html>