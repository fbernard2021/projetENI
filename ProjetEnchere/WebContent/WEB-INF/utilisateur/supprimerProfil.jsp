<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.encheres.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Supprimer profil</title>
</head>
<body>

	<%@ include file = "entete.jsp" %>
	<div class="container">
		<div class="row justify-content">
			<div class="col-6 offset-3">
				<h1>Supprimer votre compte</h1>
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
		<div class="col-6 offset-3">
			<form action="${pageContext.request.contextPath}/utilisateur/suppressionCompte" method="post">
					<label for="mdp">Mot de passe : </label>
					<input type="password"  class="form-control" name="mdp" required/>
				<button type="submit" class="btn btn-primary">Supprimer</button>
			</form>
		</div>

	
		

</body>
</html>