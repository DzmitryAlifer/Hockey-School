<%@ include file="../include/jsphead.jsp"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

    <table width="100%" cellpadding="0" cellspacing="5">
        <tr>
            <td width="200">
                <div class="blueFusion">
                    <fmt:message key="menu.hello" bundle="${rb}"/>,&nbsp;${person.getFirstName()}&nbsp;${person.getLastName()}&nbsp;(${role})<br><br>
                    <a href="Controller?command=logout"><u><fmt:message key="menu.logout" bundle="${rb}"/></u></a>
                </div>
                <br>
            </td>
            <td width="*">

            </td>
            <td width="200">
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
                <details class="block">
                    <summary><b><fmt:message key="action.common" bundle="${rb}"/></b></summary>
                    <h4 class="menu2"><a href="Controller?command=summary"><fmt:message key="menu.summary" bundle="${rb}"/></a></h4>
                    <c:if test="${role.equals(\"owner\")}">
                        <h4 class="menu2"><a href="Controller?command=budget"><fmt:message key="menu.budget" bundle="${rb}"/></a></h4>
                        <h4 class="menu2"><a href="Controller?command=ask_for_funds"><fmt:message key="menu.askforfunds" bundle="${rb}"></fmt:message></a></h4>
                        <h4 class="menu2"><a href="Controller?command=spend_money"><fmt:message key="menu.spendmoney" bundle="${rb}"></fmt:message></a></h4>
                    </c:if>
                </details>
                <br><br>
                <details class="block">
                    <summary><b><fmt:message key="action.player" bundle="${rb}"/></b></summary>
                    <h4 class="menu2"><a href="Controller?command=add_player"><fmt:message key="menu.player.add" bundle="${rb}"/></a></h4>
                    <h4 class="menu2"><a href="Controller?command=quest_player_list"><fmt:message key="menu.player.filldata" bundle="${rb}"/></a></h4>
                    <h4 class="menu2"><a href="Controller?command=delete_player"><fmt:message key="menu.player.delete" bundle="${rb}"/></a></h4>
                    <h4 class="menu2"><a href="Controller?command=promote_player"><fmt:message key="menu.player.promote" bundle="${rb}"/></a></h4>
                    <c:if test="${role.equals(\"owner\")}">
                        <h4 class="menu2"><a href="Controller?command=set_player_price"><fmt:message key="menu.player.setprice" bundle="${rb}"/></a></h4>
                        <h4 class="menu2"><a href="Controller?command=sell_player_list"><fmt:message key="menu.player.sell" bundle="${rb}"/></a></h4>
                    </c:if>
                    <h4 class="menu2"><a href="Controller?command=player_list"><fmt:message key="menu.player.list" bundle="${rb}"/></a></h4>
                </details>
                <br><br>
                <details class="block">
                    <summary><b><fmt:message key="action.coach" bundle="${rb}"/></b></summary>
                    <h4 class="menu2"><a href="Controller?command=add_coach"><fmt:message key="menu.coach.add" bundle="${rb}"/></a></h4>
                    <h4 class="menu2"><a href="Controller?command=delete_coach"><fmt:message key="menu.coach.delete" bundle="${rb}"/></a></h4>
                    <h4 class="menu2"><a href="Controller?command=coach_list"><fmt:message key="menu.coach.list" bundle="${rb}"/></a></h4>
                </details>
                <br><br>
                <details class="block">
                    <summary><b><fmt:message key="action.physio" bundle="${rb}"/></b></summary>
                    <h4 class="menu2"><a href="Controller?command=add_physio"><fmt:message key="menu.physio.add" bundle="${rb}"/></a></h4>
                    <h4 class="menu2"><a href="Controller?command=delete_physio"><fmt:message key="menu.physio.delete" bundle="${rb}"/></a></h4>
                    <h4 class="menu2"><a href="Controller?command=physio_list"><fmt:message key="menu.physio.list" bundle="${rb}"/></a></h4>
                </details>
                <br><br>
                <c:if test="${role.equals(\"owner\")}">
                    <details class="block">
                        <summary><b><fmt:message key="action.admin" bundle="${rb}"/></b></summary>
                        <h4 class="menu2"><a href="Controller?command=add_admin"><fmt:message key="menu.admin.add" bundle="${rb}"/></a></h4>
                        <h4 class="menu2"><a href="Controller?command=delete_admin"><fmt:message key="menu.admin.delete" bundle="${rb}"/></a></h4>
                        <h4 class="menu2"><a href="Controller?command=admin_list"><fmt:message key="menu.admin.list" bundle="${rb}"/></a></h4>
                    </details>
                </c:if>
            </td>
			<td width="7%" rowspan="5"></td>
			<td width="65%" rowspan="5" valign="top">
                <div class="block">
