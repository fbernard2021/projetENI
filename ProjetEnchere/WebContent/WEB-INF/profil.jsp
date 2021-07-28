<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.encheres.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil</title>
<%@ include file = "entete.jsp" %>
</head>
<body>

	<div class="container">
		<div class="row justify-content-center">
			<div class="col-6 offset-3">
				<h1>Profil</h1>
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
		
	<c:if test="${!empty profilAffiche}">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-8 offset-2">
				<p>Pseudo : ${profilAffiche.getPseudo()}</p>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-8 offset-2">
				<p>Nom : ${profilAffiche.getNom()}</p>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-8 offset-2">
				<p>Prénom : ${profilAffiche.getPrenom()}</p>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-8 offset-2">
				<p>Email : ${profilAffiche.getEmail()}</p>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-8 offset-2">
				<p>Téléphone : ${profilAffiche.getTelephone()}</p>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-8 offset-2">
				<p>Rue : ${profilAffiche.getRue()}</p>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-8 offset-2">
				<p>Code Postal : ${profilAffiche.getCodePostal()}</p>
			</div>
		</div>
		<div class="row justify-content-center">
			<div class="col-8 offset-2">
				<p>Ville : ${profilAffiche.getVille()}</p>
			</div>
		</div>
		<c:if test="${utilisateur.pseudo == profilAffiche.pseudo}">
		<div class="row justify-content-center">
			<div class="col-8 offset-2">
			<a href="${pageContext.request.contextPath}/utilisateur/modifierProfil">
				<button type="button" class="btn btn-primary">modifier le profil</button>
			</a>
			</div>
		</div>
		</c:if>
				
	</div>
	</c:if>

</body>
</html>