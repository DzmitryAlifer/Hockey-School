<table align="left" style="font-size: x-small; font-weight: bold">
    <tr>
        <td style="text-align: left">
            <fmt:message key="quest.birthyear" bundle="${rb}"/> ${teamPlayer.quest.birthYear}
        </td>
    </tr>
    <tr>
        <td style="text-align: left">
            <fmt:message key="quest.birthplace" bundle="${rb}"/> ${teamPlayer.quest.birthPlace}
        </td>
    </tr>
    <tr>
        <td style="text-align: left">
            <fmt:message key="quest.nationality" bundle="${rb}"/>
            <img src="/image/blank.gif" class="flag ${teamPlayer.quest.nationality}" alt="${teamPlayer.quest.nationality}" />
        </td>
    </tr>
    <tr>
        <td style="text-align: left">
            <fmt:message key="quest.height" bundle="${rb}"/> ${teamPlayer.quest.height}
        </td>
    </tr>
    <tr>
        <td style="text-align: left">
            <fmt:message key="quest.weight" bundle="${rb}"/> ${teamPlayer.quest.weight}
        </td>
    </tr>
    <tr>
        <td style="text-align: left">
            <fmt:message key="quest.jersey" bundle="${rb}"/> ${teamPlayer.quest.jersey}
        </td>
    </tr>
    <tr>
        <td style="text-align: left">
            <fmt:message key="quest.side" bundle="${rb}"/> ${teamPlayer.quest.preferableSide}
        </td>
    </tr>
    <tr>
        <td style="text-align: left">
            <fmt:message key="quest.role" bundle="${rb}"/> <fmt:message key="role.${teamPlayer.quest.gameRole}" bundle="${rb}"/>
        </td>
    </tr>
    <tr>
        <td style="text-align: left">
            <fmt:message key="quest.interests" bundle="${rb}"/> ${teamPlayer.quest.interests}
        </td>
    </tr>
    <tr>
        <td style="text-align: left">
            <c:if test="${role.equals(\"administrator\") || role.equals(\"owner\")}">
                <a href="Controller?command=update_quest&playerId=${teamPlayer.id}"><u><fmt:message key="quest.update" bundle="${rb}"/></u></a>
            </c:if>
        </td>
    </tr>
</table>