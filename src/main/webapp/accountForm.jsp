<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Account</title>
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
            display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
    <section>
        <h3><a href="index.html">Home</a></h3>
        <hr>
        <h2>${param.action == 'create' ? 'Add New Account' : 'Edit Current Account'}</h2>
        <jsp:useBean id="account" type="com.budget.model.Account" scope="request"/>
        <form method="post" action="accounts">
            <input type="hidden" name="id" value="${account.id}">
            <dl>
                <dt>DateTime:</dt>
                <dd><input type="datetime-local" value="${account.dateTimeOperation}" name="dateTime" required></dd>
            </dl>
            <dl>
                <dt>Card Name:</dt>
                <dd><input type="text" value="${account.cardName}" size=40 name="cardName" required></dd>
            </dl>
            <dl>
                <dt>Operation Type:</dt>
                <dd>
                    <select name="operationType" required>
                        <c:if test="${account.operationType == null}">
                            <option selected hidden value=""></option>
                        </c:if>
                        <c:forEach items="${operations}" var="operation">
                            <option ${operation == account.operationType ? "selected" : ""}
                                    value="${operation}">${operation}</option>
                        </c:forEach>
                    </select>
                </dd>
            </dl>
            <dl>
                <dt>Amount:</dt>
                <dd><input type="text" value="${account.amount}" size=40 name="amount" required></dd>
            </dl>
            <dl>
                <dt>Notes:</dt>
                <dd><input type="text" value="${account.notes}" size=40 name="notes"></dd>
            </dl>
            <dl>
                <dt>Company Payment:</dt>
                <dd><input type="text" value="${account.companyPayment}" size=40 name="companyPayment" required></dd>
            </dl>
            <dl>
                <dt>Category:</dt>
                <dd>
                    <select name="category" required>
                        <c:if test="${account.category == null}">
                            <option selected hidden value=""></option>
                        </c:if>
                        <c:forEach items="${categories}" var="category">
                            <option ${category == account.category ? "selected" : ""}
                            value="${category}">${category}</option>
                        </c:forEach>
                    </select>
                </dd>
            </dl>
            <button type="submit">Save</button>
            <button type="button" onclick="window.history.back()">Cancel</button>
        </form>
    </section>
</body>
</html>
