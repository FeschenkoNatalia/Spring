<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Home Finance</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<spring:url value="/" var="urlHome" />
<spring:url value="/transactions/add" var="urlAddTransaction" />
<spring:url value="/transactions/{pattern}/list" var="urlFindByPatternTransaction" />

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlHome}">Home Finance</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${urlAddTransaction}">Add transaction</a></li>
			</ul>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${urlFindByPatternTransaction}">Find by pattern transaction</a></li>
			</ul>
		</div>
	</div>
</nav>