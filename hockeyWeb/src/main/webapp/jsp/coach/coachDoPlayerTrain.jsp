<%@ include file="../include/jsphead.jsp"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<html>
<head>
    <title> <fmt:message key="pagetitle.coachdoplayertrain" bundle="${rb}"/> </title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>
<c:set var="previousCommand" scope="session" value="coach_do_player_train"/>

<%@ include file="../include/menuCoachPhysio.jsp"%>

<h1 class="header"><fmt:message key="coachtrain.title" bundle="${rb}"/></h1>

<b style="color:darkred"> ${noSkillToTrain} </b><br>

<fmt:message key="coachdotrain.note" bundle="${rb}"/>
<hr>
<b> <fmt:message key="coachdotrain.coach" bundle="${rb}"/> ${person.firstName} ${person.lastName} </b><br>
<fmt:message key="coachdotrain.defence" bundle="${rb}"/> ${person.defenceInc} <br>
<fmt:message key="coachdotrain.attack" bundle="${rb}"/> ${person.attackInc} <hr>
<b> <fmt:message key="coachdotrain.player" bundle="${rb}"/> ${trainingPlayer.firstName} ${trainingPlayer.lastName}</b><br>

<form action="Controller" method="post" >
    <input type="hidden" name="command" value="coach_start">
    <table align="center">
        <tr>
            <td style="text-align: left">
                <input type="radio" checked id="defRadio" name="skill" value="defence" />
                <label for="defRadio">&nbsp; <fmt:message key="chooseplayerprice.defence" bundle="${rb}"/> ${trainingPlayer.defence}</label>
            </td>
            <td width="20%"></td>
            <td style="text-align: left">
                <fmt:message key="chooseplayerprice.speed" bundle="${rb}"/> ${trainingPlayer.speed}
            </td>
        </tr>
        <tr>
            <td style="text-align: left">
                <input type="radio" id="attRadio" name="skill" value="attack" />
                <label for="attRadio">&nbsp; <fmt:message key="chooseplayerprice.attack" bundle="${rb}"/> ${trainingPlayer.attack}</label>
            </td>
            <td></td>
            <td style="text-align: left">
                <fmt:message key="chooseplayerprice.strength" bundle="${rb}"/> ${trainingPlayer.strength}
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <input type="image" value=" " class="puckButton">
            </td>
        </tr>
    </table>
</form>
<%@ include file="../include/footer.jsp" %>

</body>
</html>
