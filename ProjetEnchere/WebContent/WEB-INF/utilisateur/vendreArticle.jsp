<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.encheres.messages.LecteurMessage" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="now" value="<%=new Date()%>"/>
<c:set var="tomorrow" value="<%=new Date(new Date().getTime() + 60*60*24*1000)%>"/>
<fmt:formatDate value="${now}" var="date" pattern="yyyy-MM-dd"/>
<fmt:formatDate value="${tomorrow}" var="demain" pattern="yyyy-MM-dd"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vendre article</title>
</head>
<body>

	<%@ include file = "entete.jsp" %>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-6 offset-3">
				<h1>Vendre un article</h1>
			</div>
		</div>
	</div>
	
		<c:if test="${!empty listeCategories }">
			<form action="${pageContext.request.contextPath}/utilisateur/vendreArticle" method="post">
				<div class="row">
				<div class="col-6 offset-3">
					<div class="form-group">
						<label for="nom">Article :</label>
						<input type="text" class="form-control" id="nom" name="article" required>
					</div>
					<div class="form-group">
						<label for="description">Description :</label>
						<textarea  class="form-control" id="description" name="description" rows = "6" required></textarea>
					</div>
					<div class="form-group">
						<label for="categories">Catégories :</label>
						<select class="form-control" id="categories" name="categories">
						<c:forEach  items="${listeCategories}" var="liste">
							<option value="${liste.getNomCategorie()}">${liste.getNomCategorie()}</option>
						</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="prix">Mise à prix :</label>
						<input type="number" id="prix" name="prix" min="1" max="10000" required>
					</div>
					<div class="form-group">
						<label for="dateDebut">Début de l'enchère :</label>
						<input type="date" id="dateDebut" name="dateDebut" value="${date}" min="${date}" >
					</div>
					<div class="form-group">
						<label for="dateFin">Fin de l'enchère :</label>
						<input type="date" id="dateFin" name="dateFin" value="${demain}" min="${demain}" >
					</div>
					<fieldset class="border p-2">
   						<legend  class="w-auto">Retrait</legend>
   						<div class="form-group">
							<label for="rue">rue :</label>
							<input type="text" id="rue" name="rue" value="${sessionScope.utilisateur.getRue()}" required>
						</div>
   						<div class="form-group">
							<label for="postal">Code postal :</label>
							<input type="text" id="postal" name="postal" value="${sessionScope.utilisateur.getCodePostal()}" required>
						</div>
   						<div class="form-group">
							<label for="ville">Ville :</label>
							<input type="text" id="ville" name="ville" value="${sessionScope.utilisateur.getVille()}" required>
						</div>
					</fieldset>
				</div>
				</div>
				<div class="row justify-content-center">
				<div class="col-1">	
					<button type="submit" class="btn btn-primary">Enregistrer</button>
				</div>
				</div>
		
			</form>

		
		</c:if>

</body>
</html>