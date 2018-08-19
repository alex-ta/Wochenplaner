<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="url" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" />
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="/web-0.0.1-SNAPSHOT/js/RestClient.js"></script>
<script src="/web-0.0.1-SNAPSHOT/js/TableUsers.js"></script>

<script type="text/javascript">
var userUrl = "/web-0.0.1-SNAPSHOT/v1/users/";
var entryUrl = "/web-0.0.1-SNAPSHOT/v1/entry/";

$(document).ready(function() {
	var table = new TableWrapper(document.getElementById("content"));
	table.init(userUrl);
});

</script>
</head>
<body>


	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
		            <span class="sr-only">Toggle navigation</span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		          </button>
				<a class="navbar-brand" href="#">Willkommen ${username}</a>
			</div>

			<div id="navbar" class="navbar-collapse collapse">
				<c:if test="${username != null}">
					<form class="navbar-form navbar-right" id="logoutForm"
						method="POST" action="${url}/logout">
						<div class="form-group">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</div>
						<div class="form-group">
							<button type="logout" class="btn btn-danger"
								onclick="document.forms['logoutForm'].submit()">Logout</button>
						</div>
						
					</form>
					
					       <div class="navbar-form navbar-right">
					       	<a class="btn btn-info" href="/web/">Home</a>
					       </div>
				</c:if>
			</div>
		</div>
	</nav>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<h2>Benutzerverwaltung</h2>
			<p>Diese Seite zeigt alle vorhandnen Nutzer an. Nutzer können angelegt, bearbeitet und gelöscht werden.</p>
		</div>
	</div>

	<div id="content" class="container">
		
	</div>
</body>
</html>