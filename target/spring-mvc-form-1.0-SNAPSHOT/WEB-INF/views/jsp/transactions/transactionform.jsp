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

		<spring:bind path="transactionDate">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">TransactionDate</label>
				<div class="col-sm-10">
					<form:input path="transactionDate" class="form-control" id="transactionDate" placeholder="transactionDate" />
					<form:errors path="transactionDate" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="transactionType">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">TransactionType</label>
				<div class="col-sm-10">
					<form:select path="transactionType" items="${transactionTypeList}" multiple="true" size="5" class="form-control" />
					<form:errors path="transactionType" class="control-label" />
				</div>
				<div class="col-sm-10"></div>
			</div>
		</spring:bind>

		<spring:bind path="sourceType">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">SourceType</label>
				<div class="col-sm-10">
					<form:select path="sourceType" items="${sourceTypeList}" multiple="true" size="5" class="form-control" />
					<form:errors path="sourceType" class="control-label" />
				</div>
				<div class="col-sm-10"></div>
			</div>
		</spring:bind>

		<spring:bind path="sourceSum">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">SourceSum</label>
				<div class="col-sm-10">
					<form:textarea path="sourceSum" class="form-control" id="sourceSum" placeholder="sourceSum" />
					<form:errors path="sourceSum" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="transactionDesc">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">TransactionDesc</label>
				<div class="col-sm-10">
					<form:input path="transactionDesc" class="form-control" id="transactionDesc" placeholder="transactionDesc" />
					<form:errors path="transactionDesc" class="control-label" />
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