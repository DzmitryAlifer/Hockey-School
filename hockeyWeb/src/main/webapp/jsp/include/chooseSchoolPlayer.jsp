<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<table  width="90%" align="center" cellspacing="0" cellpadding="10">
    <tr>
        <th> <fmt:message key="player.fullname" bundle="${rb}"/> </th>
        <th> <fmt:message key="player.defence" bundle="${rb}"/> </th>
        <th> <fmt:message key="player.attack" bundle="${rb}"/> </th>
        <th> <fmt:message key="player.speed" bundle="${rb}"/> </th>
        <th> <fmt:message key="player.strength" bundle="${rb}"/> </th>
    </tr>
    <c:forEach var="schoolPlayer" items="${schoolPlayers}">
        <tr class="stripe">
            <td style="text-align: left">
                <input type="radio" checked id="schoolPlayerRadio${schoolPlayer.getId()}" name="id" value="${schoolPlayer.getId()}" />
                <label for="schoolPlayerRadio${schoolPlayer.getId()}"> &nbsp;${schoolPlayer.getFirstName()}&nbsp;${schoolPlayer.getLastName()} </label>
            </td>
            <td> <ctg:color-meter value="${schoolPlayer.getDefence()}"/> </td>
            <td> <ctg:color-meter value="${schoolPlayer.getAttack()}"/> </td>
            <td> <ctg:color-meter value="${schoolPlayer.getSpeed()}"/> </td>
            <td> <ctg:color-meter value="${schoolPlayer.getStrength()}"/> </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="5"><br><input type="image" value=" " class="puckButton"></td>
    </tr>
</table>
