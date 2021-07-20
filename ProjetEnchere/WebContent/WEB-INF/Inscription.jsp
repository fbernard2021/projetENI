<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.encheres.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<title>Inscription</title>
</head>



<body>
	<%@ include file = "entete.jsp" %>
	
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-6 offset-3">
				<h1>Mon Profil</h1>
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
		<div class="row">
			<div class="col-12">
				<form action="${pageContext.request.contextPath}/inscription" method="post">
					<div class="form-group">
						<label for="pseudo">Pseudo :</label>
						<input type="text" class="form-control" id="pseudo" name="pseudo" required>
					</div>
					<div class="form-group">
						<label for="nom">Nom :</label>
						<input type="text" class="form-control" id="nom" name="nom" required>
					</div>
					<div class="form-group">
						<label for="prenom">Prénom :</label>
						<input type="text" class="form-control" id="prenom" name="prenom" required>
					</div>
					<div class="form-group">
						<label for="email">Email :</label>
						<input type="email" class="form-control" id="email" name="email" required>
					</div>
					<div class="form-group">
						<label for="telephone">Téléphone :</label>
						<input type="text" class="form-control" id="telephone" name="telephone">
					</div>
					<div class="form-group">
						<label for="rue">Rue :</label>
						<input type="text" class="form-control" id="rue" name="rue" required>
					</div>
					<div class="form-group">
						<label for="postal">Code postale :</label>
						<input type="text" class="form-control" id="postal" name="postal" required>
					</div>
					<div class="form-group">
						<label for="ville">Ville :</label>
						<input type="text" class="form-control" id="ville" name="ville" required>
					</div>
					<div class="form-group">
						<label for="mdp">Mot de passe :</label>
						<input type="password" class="form-control" id="mdp" name="mdp" required>
					</div>
					<div class="form-group">
						<label for="mdpConfirm">Confirmation :</label>
						<input type="password" class="form-control" id="mdpConfirm" name="mdpConfirm">
					</div>
					<div>
						<h3>Administrateur :</h3>
						<div class="form-check">
  							<input class="form-check-input" type="radio" name="adm" id="1" value="1">
  							<label class="form-check-label" for="1">
   								 Oui
  							</label>
						</div>
						<div class="form-check">
  							<input class="form-check-input" type="radio" name="adm" id="0" value="0" checked>
  							<label class="form-check-label" for="0">
   								 Non
 							 </label>
						</div>
					</div>
					<button type="submit" class="btn btn-default">Créer</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>