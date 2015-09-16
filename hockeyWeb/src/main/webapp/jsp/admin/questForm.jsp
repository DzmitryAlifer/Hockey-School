<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/jsphead.jsp"%>

<html>
<head>
    <title> <fmt:message key="pagetitle.questplayer" bundle="${rb}"/> </title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>

<c:set var="previousCommand" scope="session" value="quest_form"/>

<%@ include file="../include/menuAdminOwner.jsp"%>

<h1 class="header"> <fmt:message key="questform.title" bundle="${rb}"/> </h1>

<b>
    <fmt:message key="questform.note" bundle="${rb}"/> <br>
    ${player.getFirstName()} ${player.getLastName()}
</b>
<hr>

<form action="Controller" method="post">
    <input type="hidden" name="command" value="quest_to_base">
    <table width="80%" cellspacing="0" cellpadding="10" align="center">
        <tr>
            <td width="30%"><b> <fmt:message key="questform.birthyear" bundle="${rb}"/> </b></td>
            <td width="70%">
                <select name="birthYear">
                    <c:if test="${quest != null}">
                        <option selected value="${quest.getBirthYear()}">${quest.getBirthYear()}</option>
                    </c:if>
                    <option value="1960">1960</option>
                    <option value="1961">1961</option>
                    <option value="1962">1962</option>
                    <option value="1963">1963</option>
                    <option value="1964">1964</option>
                    <option value="1965">1965</option>
                    <option value="1966">1966</option>
                    <option value="1967">1967</option>
                    <option value="1968">1968</option>
                    <option value="1969">1969</option>
                    <option value="1970">1970</option>
                    <option value="1971">1971</option>
                    <option value="1972">1972</option>
                    <option value="1973">1973</option>
                    <option value="1974">1974</option>
                    <option value="1975">1975</option>
                    <option value="1976">1976</option>
                    <option value="1977">1977</option>
                    <option value="1978">1978</option>
                    <option value="1979">1979</option>
                    <option value="1980">1980</option>
                    <option value="1981">1981</option>
                    <option value="1982">1982</option>
                    <option value="1983">1983</option>
                    <option value="1984">1984</option>
                    <option value="1985">1985</option>
                    <option value="1986">1986</option>
                    <option value="1987">1987</option>
                    <option value="1988">1988</option>
                    <option value="1989">1989</option>
                    <option value="1990">1990</option>
                    <option value="1991">1991</option>
                    <option value="1992">1992</option>
                    <option value="1993">1993</option>
                    <option value="1994">1994</option>
                    <option value="1995">1995</option>
                    <option value="1996">1996</option>
                    <option value="1997">1997</option>
                    <option value="1998">1998</option>
                    <option value="1999">1999</option>
                    <option value="2000">2000</option>
                </select>
            </td>
        </tr>
        <tr>
            <td><b> <fmt:message key="questform.birthplace" bundle="${rb}"/> </b></td>
            <td> <input type="text" name="birthPlace" required pattern="^[A-aZ-z\u0410-\u042f\u0430-\u044f-]{3,20}$" value="${quest.getBirthPlace()}"/> </td>
        </tr>
        <tr>
            <td><b> <fmt:message key="questform.nationality" bundle="${rb}"/> </b></td>
            <td>
                <select name="nationality" >
                    <c:if test="${quest != null}">
                        <option selected value="${quest.getNationality()}"><fmt:message key="country.${quest.getNationality()}" bundle="${rb}"/></option>
                    </c:if>
                    <optgroup label="<fmt:message key="country.europe" bundle="${rb}"/>">
                        <option value="belarus"><fmt:message key="country.belarus" bundle="${rb}"/></option>
                        <option value="russia"><fmt:message key="country.russia" bundle="${rb}"/></option>
                        <option value="ukraine"><fmt:message key="country.ukraine" bundle="${rb}"/></option>
                        <option value="sweden"><fmt:message key="country.sweden" bundle="${rb}"/></option>
                        <option value="finland"><fmt:message key="country.finland" bundle="${rb}"/></option>
                        <option value="france"><fmt:message key="country.france" bundle="${rb}"/></option>
                        <option value="germany"><fmt:message key="country.germany" bundle="${rb}"/></option>
                        <option value="czech"><fmt:message key="country.czech" bundle="${rb}"/></option>
                        <option value="spain"><fmt:message key="country.spain" bundle="${rb}"/></option>
                        <option value="norway"><fmt:message key="country.norway" bundle="${rb}"/></option>
                        <option value="denmark"><fmt:message key="country.denmark" bundle="${rb}"/></option>
                        <option value="italy"><fmt:message key="country.italy" bundle="${rb}"/></option>
                        <option value="swiss"><fmt:message key="country.swiss" bundle="${rb}"/></option>
                        <option value="holland"><fmt:message key="country.holland" bundle="${rb}"/></option>
                        <option value="poland"><fmt:message key="country.poland" bundle="${rb}"/></option>
                        <option value="slovenia"><fmt:message key="country.slovenia" bundle="${rb}"/></option>
                        <option value="slovakia"><fmt:message key="country.slovakia" bundle="${rb}"/></option>
                        <option value="austria"><fmt:message key="country.austria" bundle="${rb}"/></option>
                        <option value="latvia"><fmt:message key="country.latvia" bundle="${rb}"/></option>
                        <option value="gb"><fmt:message key="country.gb" bundle="${rb}"/></option>
                    </optgroup>
                    <optgroup label="<fmt:message key="country.america" bundle="${rb}"/>">
                        <option value="usa"><fmt:message key="country.usa" bundle="${rb}"/></option>
                        <option value="canada"><fmt:message key="country.canada" bundle="${rb}"/></option>
                        <option value="argentina"><fmt:message key="country.argentina" bundle="${rb}"/></option>
                        <option value="brazil"><fmt:message key="country.brazil" bundle="${rb}"/></option>
                    </optgroup>
                    <optgroup label="<fmt:message key="country.asia" bundle="${rb}"/>">
                        <option value="japan"><fmt:message key="country.japan" bundle="${rb}"/></option>
                        <option value="china"><fmt:message key="country.china" bundle="${rb}"/></option>
                    </optgroup>
                </select>
            </td>
        </tr>
        <tr>
            <td><b> <fmt:message key="questform.height" bundle="${rb}"/> </b></td>
            <td> <input type="text" name="height" required pattern="^[1][5-9][0-9]$|^[2][0-4][0-9]$" value="${quest.getHeight()}"/> </td>
        </tr>
        <tr>
            <td><b> <fmt:message key="questform.weight" bundle="${rb}"/> </b></td>
            <td> <input type="text" name="weight" required pattern="^[4-9][0-9]$|^[1][0-5][0-9]$" value="${quest.getWeight()}"/> </td>
        </tr>
        <tr>
            <td><b> <fmt:message key="questform.jersey" bundle="${rb}"/> </b></td>
            <td> <input type="text" name="jersey" required pattern="^[1-9]$|^[1-9][0-9]$" value="${quest.getJersey()}"/> </td>
        </tr>
        <tr>
            <td><b> <fmt:message key="questform.preferableside" bundle="${rb}"/> </b></td>
            <td>
                <select name="preferableSide">
                    <c:if test="${quest != null}">
                        <option selected value="${quest.getPreferableSide()}"><fmt:message key="${quest.getPreferableSide()}" bundle="${rb}"/></option>
                    </c:if>
                    <option value="left"><fmt:message key="left" bundle="${rb}"/></option>
                    <option value="right"><fmt:message key="right" bundle="${rb}"/></option>
                    <option value="both"><fmt:message key="both" bundle="${rb}"/></option>
                </select>
            </td>
        </tr>
        <tr>
            <td><b> <fmt:message key="questform.gamerole" bundle="${rb}"/> </b></td>
            <td>
                <select name="gameRole" >
                    <c:if test="${quest != null}">
                        <option selected value="${quest.getGameRole()}"><fmt:message key="role.${quest.getGameRole()}" bundle="${rb}"/></option>
                    </c:if>
                    <optgroup label="<fmt:message key="role.defence" bundle="${rb}"/>">
                        <option value="defence"><fmt:message key="role.defence" bundle="${rb}"/></option>
                        <option value="powerdefence"><fmt:message key="role.powerdefence" bundle="${rb}"/></option>
                        <option value="speeddefence"><fmt:message key="role.speeddefence" bundle="${rb}"/></option>
                    </optgroup>
                    <optgroup label="<fmt:message key="role.forward" bundle="${rb}"/>">
                        <option value="forward"><fmt:message key="role.forward" bundle="${rb}"/></option>
                        <option value="powerforward"><fmt:message key="role.powerforward" bundle="${rb}"/></option>
                        <option value="speedforward"><fmt:message key="role.speedforward" bundle="${rb}"/></option>
                    </optgroup>
                    <optgroup label="<fmt:message key="role.universal" bundle="${rb}"/>">
                        <option value="another"><fmt:message key="role.universal" bundle="${rb}"/></option>
                    </optgroup>
                </select>
            </td>
        </tr>
        <tr>
            <td><b> <fmt:message key="questform.interests" bundle="${rb}"/> </b></td>
            <td> <textarea name="interests" cols="20" rows="4">${quest.getInterests()}</textarea> </td>
        </tr>
        <tr>
            <td colspan="2"> <input type="image" value=" " class="puckButton"> </td>
        </tr>
    </table>
</form>

<br><br>

<%@ include file="../include/footer.jsp" %>

</body>
</html>