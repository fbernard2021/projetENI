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
			<div class="col-1">
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
		<table class="table table-borderless">
			<tbody>
				<tr>
					<th scope="row">Pseudo :</th>
					<td>${profilAffiche.getPseudo()}</td>
				</tr>
				<tr>
					<th scope="row">Nom :</th>
					<td>${profilAffiche.getNom()}</td>
				</tr>
				<tr>
					<th scope="row">Prénom :</th>
					<td>${profilAffiche.getPrenom()}</td>
				</tr>
				<tr>
					<th scope="row">Email :</th>
					<td>${profilAffiche.getEmail()}</td>
				</tr>
				<tr>
					<th scope="row">Téléphone :</th>
					<td>${profilAffiche.getTelephone()}</td>
				</tr>
				<tr>
					<th scope="row">Rue :</th>
					<td>${profilAffiche.getRue()}</td>
				</tr>
				<tr>
					<th scope="row">Code Postal :</th>
					<td>${profilAffiche.getCodePostal()}</td>
				</tr>
				<tr>
					<th scope="row">Ville :</th>
					<td>${profilAffiche.getVille()}</td>
				</tr>
			</tbody>
		</table>
	</div>
	</div>
	</div>
	<div class="container">
		<c:if test="${utilisateur.pseudo == profilAffiche.pseudo}">
		<div class="row justify-content-center">
			<div class="col-6 offset-3">
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