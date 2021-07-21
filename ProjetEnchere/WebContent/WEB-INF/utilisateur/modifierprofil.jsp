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


	<div class="container">
		<div class="row justify-content-center">
			<div class="col-6 offset-3">
				<h1>Modifier profil</h1>
			</div>
		</div>
	</div>
	
	<c:if test="${!empty sessionScope.utilisateur }">
		<div class="row">
			<div class="col-12">
				<form action="${pageContext.request.contextPath}/utilisateur/modifierProfil" method="post">

					<div class="form-group">
						<label for="nom">Nom :</label>
						<input type="text" class="form-control" id="nom" name="nom" placeholder="${sessionScope.utilisateur.getNom}">
					</div>
					<div class="form-group">
						<label for="prenom">Prénom :</label>
						<input type="text" class="form-control" id="prenom" name="prenom" placeholder="${sessionScope.utilisateur.getPrenom}">
					</div>
					<div class="form-group">
						<label for="email">Email :</label>
						<input type="email" class="form-control" id="email" name="email" placeholder="${sessionScope.utilisateur.getEmail}">
					</div>
					<div class="form-group">
						<label for="telephone">Téléphone :</label>
						<input type="text" class="form-control" id="telephone" name="telephone" placeholder="${sessionScope.utilisateur.getTelephone}">
					</div>
					<div class="form-group">
						<label for="rue">Rue :</label>
						<input type="text" class="form-control" id="rue" name="rue" placeholder="${sessionScope.utilisateur.getRue}">
					</div>
					<div class="form-group">
						<label for="postal">Code postale :</label>
						<input type="text" class="form-control" id="postal" name="postal" placeholder="${sessionScope.utilisateur.getCodePostal}">
					</div>
					<div class="form-group">
						<label for="ville">Ville :</label>
						<input type="text" class="form-control" id="ville" name="ville" placeholder="${sessionScope.utilisateur.getVille}">
					</div>
					<div class="form-group">
						<label for="mdp">Mot de passe :</label>
						<input type="password" class="form-control" id="mdp" name="mdp" >
					</div>

					<button type="submit" class="btn btn-default">Modifier</button>
				</form>
			</div>
		</div>
	</c:if>

</body>
</html>