<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-default">
		<div class ="container-fluid">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/accueil">ENI-Enchères</a>
			
			<c:choose>
			<!--  when non connecté -->
			<a class="navbar-brand" href="${pageContext.request.contextPath}/connexion">Se connecter</a>
			<!-- otherwise -->
			<!-- end -->
				<!-- Afficher non enregistré dans session  -->
			<!-- end -->
			</c:choose>
		</div>
	</nav>
</body>
</html>