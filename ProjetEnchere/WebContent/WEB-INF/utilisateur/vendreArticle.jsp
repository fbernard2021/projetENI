<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.encheres.messages.LecteurMessage" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="now" value="<%=new Date()%>"/>
<fmt:formatDate value="${now}" var="date" pattern="yyyy-MM-dd"/>
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
			<div class="row">
				<div class="col-6 offset-3">
					<form action="${pageContext.request.contextPath}/utilisateur/vendreArticle" method="post">
					<div class="form-group">
						<label for="nom">Article :</label>
						<input type="text" class="form-control" id="nom" name="nom" required>
					</div>
					<div class="form-group">
						<label for="description">Description :</label>
						<textarea  class="form-control" id="description" name="description" rows = "6" required></textarea>
					</div>
					<div class="form-group">
						<label for="categories">Catégories :</label>
						<select class="form-control" id="categories" name="description">
						<c:forEach  items="${listeCategories}" var="liste">
							<option value="${liste.getNomCategorie()}">${liste.getNomCategorie()}</option>
						</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="prix">Mise à prix :</label>
						<input type="number" id="prix" name="prix" min="1" max="10000">
					</div>
					<div class="form-group">
						<label for="dateDebut">Début de l'enchère :</label>
						<input type="date" id="dateDebut" name="dateDebut" value="${date}" min="${date}" >
					</div>
					</form>
				</div>
			</div>
		
		
		</c:if>

</body>
</html>