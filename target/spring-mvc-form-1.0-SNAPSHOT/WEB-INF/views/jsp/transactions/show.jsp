<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<div class="container">

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<div class="row">
		<label class="col-sm-2">ID</label>
		<div class="col-sm-10">${transaction.id}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">User</label>
		<div class="col-sm-10">${transaction.user}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Transaction_date</label>
		<div class="col-sm-10">${transaction.transaction_date}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Transaction_type</label>
		<div class="col-sm-10">${transaction.transaction_type}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Source_type</label>
		<div class="col-sm-10">${transaction.source_type}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Source_sum</label>
		<div class="col-sm-10">${transaction.source_sum}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Transaction_desc</label>
		<div class="col-sm-10">${transaction.transaction_desc}</div>
	</div>

</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>