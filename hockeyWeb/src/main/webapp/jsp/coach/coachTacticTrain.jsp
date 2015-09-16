<%@ include file="../include/jsphead.jsp"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<html>
<head>
    <title><fmt:message key="pagetitle.coachtactictrain" bundle="${rb}"/></title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
    <link rel="stylesheet" type="text/css" href="../../css/flags.css">
</head>
<body>

<c:set var="previousCommand" scope="session" value="coach_tactic_train"/>

<%@ include file="../include/menuCoachPhysio.jsp"%>

<h1 class="header"><fmt:message key="coach.tactictrain.header" bundle="${rb}"/></h1>

<b>${person.firstName} ${person.lastName}</b>
<fmt:message key="coach.tactictrain.note1" bundle="${rb}"/> <br>
<fmt:message key="coach.tactictrain.note2" bundle="${rb}"/> <br>
<fmt:message key="coach.tactictrain.note3" bundle="${rb}"/> <br>
<b>${person.defenceInc}</b>
<fmt:message key="coach.tactictrain.note4" bundle="${rb}"/> <br>
<b>${person.attackInc}</b>
<fmt:message key="coach.tactictrain.note5" bundle="${rb}"/> <br>
<hr>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="coach_do_tactic_train">
    <table align="center">
        <tr>
            <td width="*"></td>
            <td width="250px">
                <input type="radio" checked id="defenceRadio" name="tactic" value="defence"/>
                <label for="defenceRadio">&nbsp;<b> <fmt:message key="coach.tactictrain.defence" bundle="${rb}"/> </b></label>
            </td>
            <td width="250px">
                <input type="radio"  id="attackRadio" name="tactic" value="attack"/>
                <label for="attackRadio">&nbsp;<b> <fmt:message key="coach.tactictrain.attack" bundle="${rb}"/> </b></label>
            </td>
            <td width="*"></td>
        </tr>
        <tr>
            <td colspan="4"><img src="../../image/2boards.png"></td>
        </tr>
        <tr>
            <td colspan="4"><br><input type="image" value=" " class="puckButton"></td>
        </tr>
    </table>

</form>
<br><br>

<%@ include file="../include/footer.jsp" %>

</body>
</html>
