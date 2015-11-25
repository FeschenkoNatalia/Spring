<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

<div class="container">

	<c:choose>
		<c:when test="${transactionForm['new']}">
			<h1>Add Transaction</h1>
		</c:when>
		<c:otherwise>
			<h1>Update Transaction</h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/transactions" var="transactionActionUrl" />

	<form:form class="form-horizontal" method="post" modelAttribute="transactionForm" action="${transactionActionUrl}">

		<form:hidden path="id" />

		<spring:bind path="user">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">User</label>
				<div class="col-sm-10">
					<form:input path="user" type="text" class="form-control " id="user" placeholder="User" />
					<form:errors path="user" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="transaction_date">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Transaction_date</label>
				<div class="col-sm-10">
					<form:input path="transaction_date" class="form-control" id="transaction_date" placeholder="transaction_date" />
					<form:errors path="transaction_date" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="transaction_type">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Transaction_type</label>
				<div class="col-sm-10">
					<form:select path="transaction_type" items="${transaction_typeList}" multiple="true" size="5" class="form-control" />
					<form:errors path="transaction_type" class="control-label" />
				</div>
				<div class="col-sm-10"></div>
			</div>
		</spring:bind>

		<spring:bind path="source_type">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Source_type</label>
				<div class="col-sm-10">
					<form:select path="source_type" items="${source_typeList}" multiple="true" size="5" class="form-control" />
					<form:errors path="source_type" class="control-label" />
				</div>
				<div class="col-sm-10"></div>
			</div>
		</spring:bind>

		<spring:bind path="source_sum">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Source_sum</label>
				<div class="col-sm-10">
					<form:textarea path="source_sum" class="form-control" id="source_sum" placeholder="source_sum" />
					<form:errors path="source_sum" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="transaction_desc">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Transaction_desc</label>
				<div class="col-sm-10">
					<form:input path="transaction_desc" class="form-control" id="transaction_desc" placeholder="transaction_desc" />
					<form:errors path="transaction_desc" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${transactionForm['new']}">
						<button type="submit" class="btn-lg btn-primary pull-right">Add</button>
					</c:when>
					<c:otherwise>
						<button type="submit" class="btn-lg btn-primary pull-right">Update</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>

</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>