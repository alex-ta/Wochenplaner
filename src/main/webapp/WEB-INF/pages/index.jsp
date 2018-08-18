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

<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" />
<script
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>

<script src="/web/js/RestClient.js"></script>
<script src="/web/js/TableWrapper.js"></script>
<script type="text/javascript">
	var userUrl = "/web/v1/users/";
	var entryUrl = "/web/v1/entries/";
	var dataObj = {};
	var restUsers = new RestClient(userUrl);
	var restEntries = new RestClient(entryUrl);
	
	
	Wrapper = function(type) {
		var type = type;
		
		function setUp(res) {
			dataObj[type] = res; //JSON.stringify(res);
			var keys = Object.keys(dataObj);
			if (keys.indexOf("users") > -1 && keys.indexOf("data") > -1) {
				users = dataObj["users"];
				data = dataObj["data"];
				init();
			}
		}

		this.getAll = function(data) {
			setUp(data);
		}
		this.post = function(data) {
			console.log(data);
		}

	}
	
	function changeContent(obj, id){
		var value = obj.value;
		var name = obj.name;
		var entry = data[id];
		entry[name] = value;
		restEntries.post(entry, new Wrapper("post"));
		console.log(entry);
		table.createStats()
	}

	window.onload = function() {
		restUsers.getAll(new Wrapper("users"));
		restEntries.getAll(new Wrapper("data"));
	}
</script>

</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Wochenplaner 2018</a>
			</div>
		</div>
	</nav>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<h2>Wochenplaner 2018</h2>
			<div class="container">
				<h2>Statistik</h2>
				<div id="container">
					<p>waiting for data</p>
				</div>
			</div>
		</div>
	</div>

	<div id="content" class="container">
		<!-- Example row of columns -->
		<p>waiting for data</p>
	</div>
</body>
</html>