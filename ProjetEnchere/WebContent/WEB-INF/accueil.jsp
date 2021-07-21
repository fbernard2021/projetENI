<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
</head>
<body>

	<h1>Liste des enchères</h1>
	
	<c:if test="${!empty encheres}">
		<div class="container">
			<% int compteur = 1; %>
			<div class="row">
			<c:forEach var="e" items="${encheres}">
				
					<div class="col">
						${v.toString()}
					</div>
				<c:choose>
					<c:when test="${compteur = 2}">
						</div><div class="row">
						<% compteur = 1; %>
					</c:when>
					<c:otherwise>
						<% compteur ++; %>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
	</c:if>
	<c:if test="${empty encheres }">
		<h3>Aucune enchère en cours</h3>
	</c:if>
</body>
</html>