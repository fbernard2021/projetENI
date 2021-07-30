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

	<c:if test="${articleVendu.etatVente == 'ET'}">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-8 offset-2">
				<c:if test="${empty pseudoMeilleureOffre }">
				<h2>Personne n'a remporté l'enchère</h2>
				</c:if>
				<c:if test="${!empty pseudoMeilleureOffre }">
					<c:if test="${utilisateur.pseudo != pseudoMeilleureOffre}">
						<h2>${pseudoMeilleureOffre} a remporté l'enchère</h2>
					</c:if>
					<c:if test="${utilisateur.pseudo == pseudoMeilleureOffre}">
						<h2>Vous avez remporté l'enchère</h2>
					</c:if>
				</c:if>
				</div>
			</div>
		</div>
	
	
	
	</c:if>


	<c:if test="${!empty articleVendu}">
	<form action="${pageContext.request.contextPath}/utilisateur/afficherArticle" method="post">
	<div class="container">
	<div class="row justify-content-center">
	<div class="col-8 offset-2">
		<table class="table table-borderless">
			<tbody>
				<tr>
					<th scope="row">Article :</th>
					<td>${articleVendu.nomArticle}</td>
				</tr>
				<tr>
					<th scope="row">Description :</th>
					<td>${articleVendu.description}</td>
				</tr>
				<tr>
					<th scope="row">Catégories :</th>
					<td>${nomCategorie}</td>
				</tr>
		<c:if test="${!empty enchere}">
		<input type="hidden" name="meilleureOffre" value="<c:out value='${enchere.montantEnchere}' />" />
		<input type="hidden" name="pseudoMeilleureOffre" value="<c:out value='${pseudoMeilleureOffre}' />" />
				<tr>
				<c:if test="${utilisateur.pseudo != pseudoMeilleureOffre}">
					<th scope="row">Meilleur offre par ${pseudoMeilleureOffre} :</th>
				</c:if>
				<c:if test="${utilisateur.pseudo == pseudoMeilleureOffre}">
					<th scope="row">Meilleur offre par vous :</th>
				</c:if>
					<td>${enchere.montantEnchere}</td>
				</tr>
		</c:if>
				<tr>
					<th scope="row">Mise à prix :</th>
					<td>${articleVendu.prixInitial}</td>
				</tr>
				<tr>
					<th scope="row">Début de l'enchère :</th>
					<td>${articleVendu.dateDebutEnchere}</td>
				</tr>
				<tr>
					<th scope="row">Fin de l'enchère :</th>
					<td>${articleVendu.dateFinEnchere}</td>
				</tr>
				<tr>
					<th scope="row">Adresse :</th>
					<td>${retrait.rue} ${retrait.codePostal} ${retrait.ville}</td>
				</tr>
			</tbody>
		</table>
	</div>
	</div>
	</div>
	<div class="container">
			<div class="row">
			<div class="col-6 offset-3">
		<c:if test="${(articleVendu.etatVente == 'EC') && (articleVendu.numUtilisateur != utilisateur.numUtilisateur)}">
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
		
		<button type="submit" class="btn btn-primary">Enchérir</button>
		
		</c:if>
		</div>
		</div>
	</div>

	
	</form>
	</c:if>


</body>
</html>