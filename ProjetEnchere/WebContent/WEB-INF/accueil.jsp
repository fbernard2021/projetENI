<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
<%@ include file = "entete.jsp" %>
</head>
<body>
	<h1>Liste des enchères</h1>
	<c:if test="${!empty articles}">
	
		<form action="${pageContext.request.contextPath}/accueil" method="post">
		 <div class="form-group">
            <label for="categories">Catégories :</label>
            <select class="form-control" id="categories" name="categories">
            	<c:forEach var="c" items="${categories}">
                	<option value="${c.getNomCategorie()}"><h4>${c.getNomCategorie()}</h4></option>
               	</c:forEach>
            </select>
            <button type="submit" class="btn btn-default">Rechercher</button>
        </div>
        </form>
	
		<div class="container">
			<% int compteur = 1; %>
			<div class="row">
			<c:forEach var="a" items="${articles}">
				<div class="col">
					<h3>${a.getNomArticle()}</h3>
					<h4>Prix : ${a.getPrixVente()}</h4>
					<h4>Fin de l'enchère : ${a.getDateFinEnchere()}</h4>
					<h4>Vendeur : 
					<a href="${pageContext.request.contextPath}/profil?pseudo=${a.getPseudo()}">
					${a.getPseudo()}</a>
					</h4>
				</div>
				<c:choose>
					<c:when test="${compteur == 2}">
						</div><div class="row">
						<% compteur = 1; %>
					</c:when>
					<c:otherwise>
						<% compteur ++; %>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div></div>
	</c:if>
	<c:if test="${empty articles}">
		<h3>Aucune enchère en cours</h3>
	</c:if>
</body>
</html>