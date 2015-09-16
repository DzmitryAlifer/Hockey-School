<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<h1 class="header"><fmt:message key="playerlist.school" bundle="${rb}"/></h1>

<table width="90%" align="center" cellspacing="0" cellpadding="10">
    <tr>
        <th><fmt:message key="playerlist.firstname" bundle="${rb}"/>, <fmt:message key="playerlist.lastname" bundle="${rb}"/></th>
        <th><fmt:message key="playerlist.defence" bundle="${rb}"/></th>
        <th><fmt:message key="playerlist.attack" bundle="${rb}"/></th>
        <th><fmt:message key="playerlist.speed" bundle="${rb}"/></th>
        <th><fmt:message key="playerlist.strength" bundle="${rb}"/></th>
    </tr>
    <c:forEach var="schoolPlayer" items="${schoolPlayers}">
        <tr class="stripe">

            <td>
                <details align="left">
                    <summary><strong>${schoolPlayer.firstName}  ${schoolPlayer.lastName}</strong></summary>
                    <c:choose>
                        <c:when test="${schoolPlayer.quest != null}">
                            <%@ include file="../include/schoolPlayersDetails.jsp"%>
                        </c:when>
                        <c:otherwise>
                            <b style="font-size: x-small">[no information]</b>
                        </c:otherwise>
                    </c:choose>
                </details>
            </td>

            <td align="right">
                <c:choose>
                    <c:when test="${idAndIncrementMap[schoolPlayer.id][0] > 0}">
                        <b style="font-size: x-small">(+${idAndIncrementMap[schoolPlayer.id][0]})</b>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${skill.equals(\"defence\") && trainingPlayerId > 0 && schoolPlayer.id == Integer.parseInt(trainingPlayerId)}">
                            <b style="font-size: x-small">(+${skillInc})</b>
                        </c:if>
                    </c:otherwise>
                </c:choose>
                <ctg:color-meter value="${schoolPlayer.defence}"/>
            </td>

            <td align="right">
                <c:choose>
                    <c:when test="${idAndIncrementMap[schoolPlayer.id][1] > 0}">
                        <b style="font-size: x-small">(+${idAndIncrementMap[schoolPlayer.id][1]})</b>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${skill.equals(\"attack\") && trainingPlayerId > 0 && schoolPlayer.id == Integer.parseInt(trainingPlayerId)}">
                            <b style="font-size: x-small">(+${skillInc})</b>
                        </c:if>
                    </c:otherwise>
                </c:choose>
                <ctg:color-meter value="${schoolPlayer.attack}"/>
            </td>

            <td align="right">
                <c:choose>
                    <c:when test="${idAndIncrementMap[schoolPlayer.id][2] > 0}">
                        <b style="font-size: x-small">(+${idAndIncrementMap[schoolPlayer.id][2]})</b>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${skill.equals(\"speed\") && trainingPlayerId > 0 && schoolPlayer.id == Integer.parseInt(trainingPlayerId)}">
                            <b style="font-size: x-small">(+${skillInc})</b>
                        </c:if>
                    </c:otherwise>
                </c:choose>
                <ctg:color-meter value="${schoolPlayer.speed}"/>
            </td>

            <td align="right">
                <c:choose>
                    <c:when test="${idAndIncrementMap[schoolPlayer.id][3] > 0}">
                        <b style="font-size: x-small">(+${idAndIncrementMap[schoolPlayer.id][3]})</b>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${skill.equals(\"strength\") && trainingPlayerId > 0 && schoolPlayer.id == Integer.parseInt(trainingPlayerId)}">
                            <b style="font-size: x-small">(+${skillInc})</b>
                        </c:if>
                    </c:otherwise>
                </c:choose>
                <ctg:color-meter value="${schoolPlayer.strength}"/>
            </td>

        </tr>
    </c:forEach>
</table>

<br><hr><br>

<h1 class="header"><fmt:message key="playerlist.team" bundle="${rb}"/></h1>

<table width="90%" align="center" cellspacing="0" cellpadding="10">
    <tr>
        <th> <fmt:message key="playerlist.firstname" bundle="${rb}"/>, <fmt:message key="playerlist.lastname" bundle="${rb}"/> </th>
        <th> <fmt:message key="playerlist.defence" bundle="${rb}"/> </th>
        <th> <fmt:message key="playerlist.attack" bundle="${rb}"/> </th>
        <th> <fmt:message key="playerlist.speed" bundle="${rb}"/> </th>
        <th> <fmt:message key="playerlist.strength" bundle="${rb}"/> </th>
        <th> <c:if test="${role.equals(\"owner\")}"> <fmt:message key="player.price" bundle="${rb}"/> </c:if> </th>
    </tr>
    <c:forEach var="teamPlayer" items="${teamPlayers}">
        <tr class="stripe">
            <td>
                <details align="left">
                    <summary><strong>${teamPlayer.firstName} ${teamPlayer.lastName}</strong></summary>
                    <c:choose>
                        <c:when test="${teamPlayer.quest != null}">
                            <%@ include file="../include/teamPlayersDetails.jsp"%>
                        </c:when>
                        <c:otherwise>
                            <b style="font-size: x-small">[no information]</b>
                        </c:otherwise>
                    </c:choose>
                </details>
            </td>
            <td>
                <ctg:color-meter value="${teamPlayer.defence}"/>
            </td>
            <td>
                <ctg:color-meter value="${teamPlayer.attack}"/>
            </td>
            <td>
                <ctg:color-meter value="${teamPlayer.speed}"/>
            </td>
            <td>
                <ctg:color-meter value="${teamPlayer.strength}"/>
            </td>
            <td>
                <c:if test="${role.equals(\"owner\") && teamPlayer.price > 0}">
                    <b class="price">$ ${teamPlayer.price} </b>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

<br><br><br>