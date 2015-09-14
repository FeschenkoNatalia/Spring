<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/add" method="post">
        <div class="form-group"><h3>New transaction</h3></div>
        <div class="form-group"><input type="text" class="form-control" name="transaction_date" placeholder="transaction_date"></div>
        <div class="form-group"><input type="text" class="form-control" name="transaction_sum" placeholder="transaction_sum"></div>
        <div class="form-group"><input type="text" class="form-control" name="transaction_desc" placeholder="transaction_desc"></div>
        <div class="form-group"><input type="text" class="form-control" name="sourcetypes" placeholder="sourcetypes"></div>
        <div class="form-group"><input type="text" class="form-control" name="transactionstypes" placeholder="transactiontypes"></div>
        <div class="form-group"><input type="submit" class="btn btn-primary" value="Add"></div>
    </form>
</div>
</body>
</html>