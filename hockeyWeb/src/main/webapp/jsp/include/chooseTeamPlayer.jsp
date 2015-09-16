<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<table width="90%" align="center" cellspacing="0" cellpadding="10">
    <tr>
        <th> <fmt:message key="player.fullname" bundle="${rb}"/> </th>
        <th> <fmt:message key="player.defence" bundle="${rb}"/> </th>
        <th> <fmt:message key="player.attack" bundle="${rb}"/> </th>
        <th> <fmt:message key="player.speed" bundle="${rb}"/> </th>
        <th> <fmt:message key="player.strength" bundle="${rb}"/> </th>
        <th> <c:if test="${role.equals(\"owner\")}"> <fmt:message key="player.price" bundle="${rb}"/> </c:if> </th>
    </tr>
    <c:forEach var="teamPlayer" items="${teamPlayers}">
        <tr class="stripe">
            <td style="text-align: left">
                <input type="radio" checked id="teamPlayerRadio${teamPlayer.id}" name="id" value="${teamPlayer.id}" />
                <label for="teamPlayerRadio${teamPlayer.id}"> &nbsp;${teamPlayer.firstName} ${teamPlayer.lastName} </label>
            </td>
            <td> <ctg:color-meter value="${teamPlayer.defence}"/> </td>
            <td> <ctg:color-meter value="${teamPlayer.attack}"/> </td>
            <td> <ctg:color-meter value="${teamPlayer.speed}"/> </td>
            <td> <ctg:color-meter value="${teamPlayer.strength}"/> </td>
            <td>
                <c:if test="${role.equals(\"owner\") && teamPlayer.price > 0}">
                    <b class="price">$ ${teamPlayer.price} </b>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="6"><br>
            <c:if test="${previousCommand.equals(\"set_player_price\")}">
                <input type="image" value=" " class="puckButton" onclick="return confirm('<fmt:message key="jsmessage.priceplayer" bundle="${rb}"/>') ? true : false;" />
            </c:if>
            <c:if test="${previousCommand.equals(\"sell_player_list\")}">
                <input type="image" value=" " class="puckButton" onclick="return confirm('<fmt:message key="jsmessage.sellplayer" bundle="${rb}"/>') ? true : false;" />
            </c:if>
        </td>
    </tr>
</table>
