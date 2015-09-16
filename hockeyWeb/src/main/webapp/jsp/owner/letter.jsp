<%@ include file="../include/jsphead.jsp"%>

<html>
<head>
    <title> <fmt:message key="pagetitle.letter" bundle="${rb}"/> </title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
    <style>
        @font-face	{font-family: "Bad Script Regular";
                    src: url("../../fonts/BadScriptRegular.ttf");}
        @font-face  {font-family: "Jey";
                    src: url("../../fonts/jey.ttf");}
        article     {font-family: "Bad Script Regular", "Cursive";
                    text-align: justify;
                    text-indent: 40px;
                    font-weight: bold;}
    </style>
</head>
<body>

<%@ include file="../include/menuAdminOwner.jsp"%>

<h1 class="header"> <fmt:message key="letter.header" bundle="${rb}"/> </h1>
<br>
<table align="center" width="70%" bgcolor="#fff" style="box-shadow: 0 0 13px 5px rgba(0, 0, 0, 0.6);" cellpadding="40"><tr><td>
<article>
    <p style="text-align: right">
        <fmt:message key="letter.text1" bundle="${rb}"/> <br>
        <fmt:message key="letter.text2" bundle="${rb}"/> <br>
    </p>
    <p><fmt:message key="letter.text3" bundle="${rb}"/></p>
    <p><fmt:message key="letter.text4" bundle="${rb}"/></p>
    <p><fmt:message key="letter.text5" bundle="${rb}"/></p>
    <p><fmt:message key="letter.text6" bundle="${rb}"/>${money}.</p>
    <p>${objective} <fmt:message key="letter.text7" bundle="${rb}"/></p>
    <p><fmt:message key="letter.text8" bundle="${rb}"/></p>
    <fmt:message key="letter.text9" bundle="${rb}"/><br>
    <fmt:message key="letter.text10" bundle="${rb}"/><br>
    ${person.firstName} ${person.lastName}<br>
    <table border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td align="left" valign="top" style="text-indent: 0">
                <b style="font-family: 'Jey'; font-size: 40px">${signature}</b>
            </td>
            <td align="right">
                <img src="../../image/parker.png" width="380px">
            </td>
        </tr>
    </table>
</article>
</td></tr></table><br><br>
<b><fmt:message key="letter.send" bundle="${rb}"/></b>
<form action="Controller" method="post">
    <input type="hidden" name="command" value="answer">
    <input type="image" value=" " class="puckButton" onclick="return confirm('<fmt:message key="jsmessage.send" bundle="${rb}"/>') ? true : false;">
</form>
<br><br><br>

<%@ include file="../include/footer.jsp" %>

</body>
</html>