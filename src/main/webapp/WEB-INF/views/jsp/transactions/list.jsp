<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<body>

	<div class="container">

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<h1>All Transactions</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>ID</th>
					<th>User</th>
					<th>TransactionDate</th>
					<th>TransactionType</th>
					<th>SourceType</th>
					<th>SourceSum</th>
					<th>TransactionDesc</th>
					<th>Action</th>>
				</tr>
			</thead>

			<c:forEach var="transaction" items="${transactions}">
				<tr>
					<td>
						${transaction.id}
					</td>
					<td>${transaction.user}</td>
					<td>${transaction.transactionDate}</td>
					<td>${transaction.transactionType}</td>
					<td>${transaction.sourceType}</td>
					<td>${transaction.sourceSum}</td>
					<td>${transaction.transactionDesc}</td>
					<td>
						<spring:url value="/transactions/${transaction.id}" var="transactionUrl" />
						<spring:url value="/transactions/${transaction.id}/delete" var="deleteUrl" />
						<spring:url value="/transactions/${transaction.id}/update" var="updateUrl" />

						<button class="btn btn-info" onclick="location.href='${transactionUrl}'">Query</button>
						<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
					    <button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button></td>
				</tr>
			</c:forEach>
		</table>

	</div>

	<jsp:include page="../fragments/footer.jsp" />

</body>
</html>