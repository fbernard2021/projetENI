<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.encheres.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<title>Connexion</title>
<%@ include file = "entete.jsp" %>
</head>
<body>
	<div class="container">
		<div class="row">
		<div class="col-12">
			<h2> Page de connexion</h2>
			
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
			
			<form action="${pageContext.request.contextPath}/connexion" method="post">
			
				<div class="form-group">
					<label for="pseudo">Pseudo ou email : </label>
					<input type="text"  class="form-control" name="pseudo" required/>
				</div>
				<div class="form-group">
					<label for="mdp">Mot de passe : </label>
					<input type="password"  class="form-control" name="mdp" required/>
				</div>
				<button type="submit" class="btn btn-default">Se connecter</button>
			</form>
		</div>
		</div>
		<div class="row">
			<a class="btn btn-primary" href="${pageContext.request.contextPath}/inscription">Créer un compte</a>
		</div>
	</div>
</body>
</html>