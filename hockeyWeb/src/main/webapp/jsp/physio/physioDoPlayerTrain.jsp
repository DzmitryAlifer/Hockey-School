<%@ include file="../include/jsphead.jsp"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<html>
<head>
    <title> <fmt:message key="pagetitle.physiodoplayertrain" bundle="${rb}"/> </title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>
<body>
<c:set var="previousCommand" scope="session" value="physio_do_player_train"/>

<%@ include file="../include/menuCoachPhysio.jsp"%>

<h1 class="header"> <fmt:message key="physiotrain.title" bundle="${rb}"/> </h1>

<b style="color:darkred"> ${noSkillToTrain} </b><br>

<fmt:message key="physiotrain.note" bundle="${rb}"/> <hr>
<b> <fmt:message key="physiotrain.physio" bundle="${rb}"/> ${person.firstName} ${person.lastName} </b><br>
<fmt:message key="physiotrain.speedteaching" bundle="${rb}"/> ${person.speedInc} <br>
<fmt:message key="physiotrain.strengthteaching" bundle="${rb}"/> ${person.strengthInc} <hr>
<b> <fmt:message key="physiotrain.player" bundle="${rb}"/> ${trainingPlayer.firstName} ${trainingPlayer.lastName}</b><br>

<form action="Controller" method="post" >
    <input type="hidden" name="command" value="physio_start">
    <table align="center">
        <tr>
            <td style="text-align: left">
                <fmt:message key="physiotrain.defence" bundle="${rb}"/> ${trainingPlayer.defence}
            </td>
            <td width="20%"> </td>
            <td style="text-align: left">
                <input type="radio" checked id="spRadio" name="skill" value="speed" />
                <label for="spRadio">&nbsp; <fmt:message key="physiotrain.speed" bundle="${rb}"/> ${trainingPlayer.speed} </label>
            </td>
        </tr>
        <tr>
            <td style="text-align: left">
                <fmt:message key="physiotrain.attack" bundle="${rb}"/> ${trainingPlayer.attack}
            </td>
            <td> </td>
            <td style="text-align: left">
                <input type="radio" id="strRadio" name="skill" value="strength" />
                <label for="strRadio">&nbsp;<fmt:message key="physiotrain.strength" bundle="${rb}"/> ${trainingPlayer.strength} </label>
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
