<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="rodrigo.hernandez.brea@gmail.com">
<!--<link rel="icon" href="../../favicon.ico">-->

<title><tiles:insertAttribute name="title" /></title>

<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/public/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/public/css/dashboard.css" rel="stylesheet">

<link rel="stylesheet" href="${pageContext.request.contextPath}/jstree/themes/default/style.min.css" />


<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="${pageContext.request.contextPath}/public/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<script src="${pageContext.request.contextPath}/public/js/jquery-1.11.1.min.js"></script>

</head>
<body>
	<tiles:insertAttribute name="navbar" />
	<div id="main-container" class="container-fluid">
		<div class="row">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/public/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="${pageContext.request.contextPath}/public/js/ie10-viewport-bug-workaround.js"></script>
    <!-- JSTREE -->
    <script src="${pageContext.request.contextPath}/jstree/jstree.min.js"></script>
</body>
</html>