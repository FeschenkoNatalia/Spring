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
					<th>Transaction_date</th>
					<th>Transaction_type</th>
					<th>Source_type</th>
					<th>Source_sum</th>
					<th>Transaction_desc</th>
					<th>Action</th>>
				</tr>
			</thead>

			<c:forEach var="transaction" items="${transactions}">
				<tr>
					<td>
						${transaction.id}
					</td>
					<td>${transaction.user}</td>
					<td>${transaction.transaction_date}</td>
					<td>${transaction.transaction_type}</td>
					<td>${transaction.source_type}</td>
					<td>${transaction.source_sum}</td>
					<td>${transaction.transaction_desc}</td>
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