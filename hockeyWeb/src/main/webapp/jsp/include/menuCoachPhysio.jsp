<%@ include file="../include/jsphead.jsp"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<table width="100%" cellpadding="0" cellspacing="5">
    <tr>
        <td width="300px" align="center">
            <div class="blueFusion">
                <fmt:message key="menu.hello" bundle="${rb}"/>, ${person.firstName} ${person.lastName} (${role})<br><br>
                <a href="Controller?command=logout"><u><fmt:message key="menu.logout" bundle="${rb}"/></u></a>
            </div>
            <br>
        </td>
        <td width="*">

        </td>
        <td width="200px">
            <%@ include file="language.jsp"%>
        </td>
    </tr>
    <tr>
        <td colspan="5">
            <%@ include file="header.jsp"%>
        </td>
    </tr>
</table>

<br>
<table width="97%">
    <tr>
        <td width="3%" rowspan="5"></td>
        <td width="23%" valign="top">
            <div class="block">
                <c:if test="${role.equals(\"coach\")}">
                    <h4 class="menu2"> <a href="Controller?command=coach_player_train"> <fmt:message key="menu.player.personaltrain" bundle="${rb}"/> </a></h4>
                    <h4 class="menu2"> <a href="Controller?command=coach_tactic_train"> <fmt:message key="menu.player.tactictrain" bundle="${rb}"/> </a></h4>
                </c:if>
                <c:if test="${role.equals(\"physiotherapist\")}">
                    <h4 class="menu2"> <a href="Controller?command=physio_player_train"> <fmt:message key="menu.player.personaltrain" bundle="${rb}"/> </a></h4>
                </c:if>
                <h4 class="menu2"> <a href="Controller?command=player_list"> <fmt:message key="menu.player.list" bundle="${rb}"/> </a></h4>
            </div>
        </td>
        <td width="7%" rowspan="5"></td>
        <td width="65%" rowspan="5" valign="top">
            <div class="block">
