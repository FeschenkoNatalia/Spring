<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Prog.kiev.ua</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h3>Transactions List</h3>

    <form class="form-inline" role="form" action="/search" method="post">
        <input type="text" class="form-control" name="pattern" placeholder="Search">
        <input type="submit" class="btn btn-default" value="Search">
    </form>

    <table class="table table-striped">
        <thead>
        <tr>
            <td><b>transaction_date</b></td>
            <td><b>transaction_sum</b></td>
            <td><b>transaction_desc</b></td>
            <td><b>transactionstypes.type</b></td>
            <td><b>Action</b></td>
        </tr>
        </thead>
        <c:forEach items="${transactions}" var="transactions">
            <tr>
                <td>${transactions.transaction_date}</td>
                <td>${transactions.transaction_sum}</td>
                <td>${transactions.transaction_desc}</td>
                <td>${transactions.transactionstypes.type}</td>
                <td><a href="/delete?id=${transactions.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>

    <form class="form-inline" role="form" action="/add_page" method="post">
        <input type="submit" class="btn btn-default" value="Add new">
    </form>
</div>
</body>
</html>