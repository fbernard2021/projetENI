<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.encheres.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Afficher article</title>
</head>
<body>

	<%@ include file = "entete.jsp" %>

	<div class="container">
		<div class="row justify-content-center">
			<div class="col-6 offset-3">
				<h1>Article</h1>
			</div>
		</div>
	</div>
	
      	<c:if test="${!empty listeCodesErreur}">
			<div class="alert alert-danger" role="alert">
			  <strong>Erreur!</strong>
			  <ul>
			  	<c:forEach var="code" items="${listeCodesErreur}">
			  		<li>${LecteurMessage.getMessageErreur(code)}</li>
			  	</c:forEach>
			  </ul>
			</div>
		</c:if>

	<c:if test="${!empty articleVendu}">
	<form action="${pageContext.request.contextPath}/utilisateur/afficherArticle" method="post">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-8 offset-2">
				<p>Article : ${articleVendu.nomArticle}</p>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-8 offset-2">
				<p>Description : ${articleVendu.description}</p>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-8 offset-2">
				<p>Catégories : ${nomCategorie}</p>
			</div>
		</div>
		<c:if test="${!empty enchere}">
		<input type="hidden" name="meilleureOffre" value="<c:out value='${enchere.montantEnchere}' />" />
		<input type="hidden" name="pseudoMeilleureOffre" value="<c:out value='${pseudoMeilleureOffre}' />" />
		<div class="row justify-content-center">
			<div class="col-8 offset-2">
				<p>Meilleur offre par ${pseudoMeilleureOffre} : ${enchere.montantEnchere}</p>
			</div>
		</div>
		</c:if>
		<div class="row justify-content-center">
			<div class="col-8 offset-2">
				<p>Mise à prix : ${articleVendu.prixInitial}</p>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-8 offset-2">
				<p>Début de l'enchère : ${articleVendu.dateDebutEnchere}</p>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-8 offset-2">
				<p>Fin de l'enchère : ${articleVendu.dateFinEnchere}</p>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-8 offset-2">
				<p>Adresse : ${retrait.rue} ${retrait.codePostal} ${retrait.ville}</p>
			</div>
		</div>
		
		<div class="form-group">
			<label for="prix">Ma proposition :</label>
			<c:if test="${!empty enchere}">
			<input type="number" id="prix" name="offre" min="${enchere.montantEnchere+1}"  required>
			</c:if>
			<c:if test="${empty enchere}">
			<input type="number" id="prix" name="offre" min="${articleVendu.prixInitial+1}"  required>
			</c:if>
		</div>
		<input type="hidden" name="id" value="<c:out value='${articleVendu.numArticle}' />" />
		
		<button type="submit" class="btn btn-default">Enchérir</button>
		
		
		
	</div>

	
	</form>
	</c:if>


</body>
</html>