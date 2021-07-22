<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.encheres.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier profil</title>
</head>
<body>

	<%@ include file = "entete.jsp" %>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-6 offset-3">
				<h1>Modifier profil</h1>
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
		
      	<c:if test="${!empty mdpOk}">
			<div class="alert alert-success" role="alert">
			  <strong>Modification mot de passe</strong>
			  <ul>
			  	<c:if test="${!empty mdpOk}">
			  		<li>Le mot de passe a été modifié avec succès</li>
			  	</c:if>
			  </ul>
			</div>
		</c:if>
	
	
	<c:if test="${!empty sessionScope.utilisateur }">
		<div class="row">
			<div class="col-12">
				<form action="${pageContext.request.contextPath}/utilisateur/modifierProfil" method="post">
					<div class="form-group">
						<label for="pseudo">pseudo :</label>
						<input type="text" class="form-control" id="pseudo" name="pseudo" value="${sessionScope.utilisateur.getPseudo()}" required>
					</div>
					<div class="form-group">
						<label for="nom">Nom :</label>
						<input type="text" class="form-control" id="nom" name="nom" value="${sessionScope.utilisateur.getNom()}" required>
					</div>
					<div class="form-group">
						<label for="prenom">Prénom :</label>
						<input type="text" class="form-control" id="prenom" name="prenom" value="${sessionScope.utilisateur.getPrenom()}" required>
					</div>
					<div class="form-group">
						<label for="email">Email :</label>
						<input type="email" class="form-control" id="email" name="email" value="${sessionScope.utilisateur.getEmail()}" required>
					</div>
					<div class="form-group">
						<label for="telephone">Téléphone :</label>
						<input type="text" class="form-control" id="telephone" name="telephone" value="${sessionScope.utilisateur.getTelephone()}">
					</div>
					<div class="form-group">
						<label for="rue">Rue :</label>
						<input type="text" class="form-control" id="rue" name="rue" value="${sessionScope.utilisateur.getRue()}" required>
					</div>
					<div class="form-group">
						<label for="postal">Code postale :</label>
						<input type="text" class="form-control" id="postal" name="postal" value="${sessionScope.utilisateur.getCodePostal()}" required>
					</div>
					<div class="form-group">
						<label for="ville">Ville :</label>
						<input type="text" class="form-control" id="ville" name="ville" value="${sessionScope.utilisateur.getVille()}" required>
					</div>
					<div class="form-group">
						<label for="mdp">Mot de passe Actuel :</label>
						<input type="password" class="form-control" id="mdp" name="mdpAct" required>
					</div>
					<div class="form-group">
						<label for="mdp">Nouveau Mot de passe :</label>
						<input type="password" class="form-control" id="mdp" name="newMdp" >
					</div>
					<div class="form-group">
						<label for="mdp">Confirmation :</label>
						<input type="password" class="form-control" id="mdp" name="mdpConfirm" >
					</div>

					<button type="submit" class="btn btn-default">Modifier</button>
				</form>
			</div>
		</div>
	</c:if>

</body>
</html>