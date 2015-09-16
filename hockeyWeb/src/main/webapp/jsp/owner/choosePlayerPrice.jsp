<%@ include file="../include/jsphead.jsp"%>

<html>
<head>
    <title><fmt:message key="pagetitle.chooseplayerprice" bundle="${rb}"/></title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <link rel="stylesheet" type="text/css" href="../css/flags.css">
</head>
<body>

<%@ include file="../include/menuAdminOwner.jsp"%>

<h1 class="header"><fmt:message key="setplayerprice.header" bundle="${rb}"/></h1>
<fmt:message key="chooseplayerprice.note1" bundle="${rb}"/><br>
<fmt:message key="chooseplayerprice.note2" bundle="${rb}"/><br>
<hr>

<table align="center" style="font-weight: bold" cellspacing="0" cellpadding="8">
    <tr>
        <td>
            <details align="left">
                <summary><strong> ${teamPlayer.firstName} ${teamPlayer.lastName} </strong></summary>
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
    </tr>
    <tr>
        <td> <fmt:message key="chooseplayerprice.recommendedprice" bundle="${rb}"/> ${recommendedPrice} </td>
    </tr>
    <tr>
        <td>
            <fmt:message key="chooseplayerprice.yourprice" bundle="${rb}"/>
            <form action="Controller" method="post" oninput="amount.value=rangeInput.value">
                <input type="hidden" name="command" value="sell_player_list">
                <input type="range" id="rangeInput" name="price" min="${recommendedPrice/10}" max="${recommendedPrice*10}" value="${recommendedPrice}">
                <br><output name="amount" for="rangeInput"> ${recommendedPrice} </output> <br><br>
                <input type="image" value=" " class="puckButton">
            </form>
        </td>
    </tr>
</table>
<br><br>

<%@ include file="../include/footer.jsp" %>

</body>
</html>