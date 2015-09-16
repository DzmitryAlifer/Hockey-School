<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

    <table  width="90%" align="center" cellspacing="0" cellpadding="10">
        <tr>
            <th><fmt:message key="player.fullname" bundle="${rb}"/></th>
            <th><fmt:message key="player.defence" bundle="${rb}"/></th>
            <th><fmt:message key="player.attack" bundle="${rb}"/></th>
            <th><fmt:message key="player.speed" bundle="${rb}"/></th>
            <th><fmt:message key="player.strength" bundle="${rb}"/></th>
        </tr>
        <c:forEach var="schoolPlayer" items="${schoolPlayers}">
            <tr class="stripe">
                <td style="text-align: left">
                    <input type="checkbox" id="schoolPlayerCheckbox${schoolPlayer.id}" name="id" value="${schoolPlayer.id}" />
                    <label for="schoolPlayerCheckbox${schoolPlayer.id}">&nbsp;${schoolPlayer.firstName}&nbsp;${schoolPlayer.lastName}</label>
                </td>
                <td> <ctg:color-meter value="${schoolPlayer.defence}"/> </td>
                <td> <ctg:color-meter value="${schoolPlayer.attack}"/> </td>
                <td> <ctg:color-meter value="${schoolPlayer.speed}"/> </td>
                <td> <ctg:color-meter value="${schoolPlayer.strength}"/> </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="5"><br>
                <c:if test="${previousCommand.equals(\"promote_player\")}">
                    <input type="image" value=" " class="puckButton" onclick="return confirm('<fmt:message key="jsmessage.promoteplayer" bundle="${rb}"/>') ? true : false;" />
                </c:if>
                <c:if test="${previousCommand.equals(\"delete_player\")}">
                    <input type="image" value=" " class="puckButton" onclick="return confirm('<fmt:message key="jsmessage.deleteplayer" bundle="${rb}"/>') ? true : false;" />
                </c:if>
            </td>
        </tr>
    </table>