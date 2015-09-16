<table align="left" style="font-size: x-small; font-weight: bold">
    <tr>
        <td style="text-align: left">
            <fmt:message key="quest.birthyear" bundle="${rb}"/> ${schoolPlayer.quest.birthYear}
        </td>
    </tr>
    <tr>
        <td style="text-align: left">
            <fmt:message key="quest.birthplace" bundle="${rb}"/> ${schoolPlayer.quest.birthPlace}
        </td>
    </tr>
    <tr>
        <td style="text-align: left">
            <fmt:message key="quest.nationality" bundle="${rb}"/>
            <img src="../../image/blank.gif" class="flag ${schoolPlayer.quest.nationality}" alt="${schoolPlayer.quest.nationality}" />
        </td>
    </tr>
    <tr>
        <td style="text-align: left">
            <fmt:message key="quest.height" bundle="${rb}"/> ${schoolPlayer.quest.height}
        </td>
    </tr>
    <tr>
        <td style="text-align: left">
            <fmt:message key="quest.weight" bundle="${rb}"/> ${schoolPlayer.quest.weight}
        </td>
    </tr>
    <tr>
        <td style="text-align: left">
            <fmt:message key="quest.jersey" bundle="${rb}"/> ${schoolPlayer.quest.jersey}
        </td>
    </tr>
    <tr>
        <td style="text-align: left">
            <fmt:message key="quest.side" bundle="${rb}"/> ${schoolPlayer.quest.preferableSide}
        </td>
    </tr>
    <tr>
        <td style="text-align: left">
            <fmt:message key="quest.role" bundle="${rb}"/> <fmt:message key="role.${schoolPlayer.quest.gameRole}" bundle="${rb}"/>
        </td>
    </tr>
    <tr>
        <td style="text-align: left">
            <fmt:message key="quest.interests" bundle="${rb}"/> ${schoolPlayer.quest.interests}
        </td>
    </tr>
    <tr>
        <td style="text-align: left">
            <c:if test="${role.equals(\"administrator\") || role.equals(\"owner\")}">
                <a href="Controller?command=update_quest&playerId=${schoolPlayer.id}"><u><fmt:message key="quest.update" bundle="${rb}"/></u></a>
            </c:if>
        </td>
    </tr>
</table>