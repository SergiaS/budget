<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://budget.com" %>

<html lang="ru">
<head>
    <title>Accounts transactions</title>
    <style>
        .normal {
            color: green;
        }
        .excess {
            color: red;
        }
    </style>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>Accounts transactions</h2>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date and Time</th>
            <th>Operation</th>
            <th>Card Name</th>
            <th>Amount</th>
            <th>Notes</th>
            <th>Company Payment</th>
            <th>Category</th>
        </tr>
        </thead>
        <c:forEach items="${accounts}" var="account">
            <jsp:useBean id="account" type="com.budget.model.AccountTo"/>
            <tr class="${account.excess ? 'excess' : 'normal'}">
<%--                <td>${account.dateTimeOperation.toLocalDate()}, ${account.dateTimeOperation.toLocalTime()}</td>--%>
                <td>${fn:formatDateTime(account.dateTimeOperation)}</td>
                <td>${account.operationType}</td>
                <td>${account.cardName}</td>
                <td>${account.amount}</td>
                <td>${account.notes}</td>
                <td>${account.companyPayment}</td>
                <td>${account.category}</td>
            </tr>

        </c:forEach>
    </table>
</body>
</html>
