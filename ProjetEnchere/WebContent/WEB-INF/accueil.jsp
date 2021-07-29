<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="fr.eni.encheres.messages.LecteurMessage" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
<%@ include file = "entete.jsp" %>
</head>
<body>
<h1>Liste des enchères</h1>


<form action="${pageContext.request.contextPath}/accueil" method="post">
	<div class="form-group">
		<input class="form-control" id="recherche" name="recherche"/>
           <label for="categories">Catégories :</label>
           <select class="form-control" id="categories" name="nomCategorie">
           	<option value="toutesCategories"><h4>Toutes les catégories</h4></option>
         	<c:forEach var="c" items="${categories}">
               	<option value="${c.getNomCategorie()}"><h4>${c.getNomCategorie()}</h4></option>
            </c:forEach>
           </select>
           
        <div class="row">
		<div class="col">
            <div class="form-check">
            	<input class="form-check-input" type="radio" name="achatsOuVentes"
            	id="achats"value="achats" onclick="desactiverMesVentes()">
  				<label class="form-check-label" for="achats"> Achats </label>
            </div>
            <div class="form-check">
  				<input class="form-check-input" type="checkbox" value="encheresOuvertes" id="encheresOuvertes" name="typeEncheres">
  				<label class="form-check-label" for="encheresOuvertes"> Enchères ouvertes </label>
  			</div>
            <div class="form-check">
  				<input class="form-check-input" type="checkbox" value="encheresEnCours" id="encheresEnCours" name="typeEncheres">
  				<label class="form-check-label" for="encheresEnCours"> Enchères en cours </label>
  			</div>
            <div class="form-check">
  				<input class="form-check-input" type="checkbox" value="encheresRemportees" id="encheresRemportees" name="typeEncheres">
  				<label class="form-check-label" for="encheresRemportees"> Enchères remportées </label>
  			</div>
  		</div>
  		<div class="col">
  			<div class="form-check">
  				<input class="form-check-input" type="radio" name="achatsOuVentes"
  				id="ventes"value="ventes" onclick="desactiverAchats()">
  				<label class="form-check-label" for="ventes"> Mes ventes </label>
  			</div>
            <div class="form-check">
  				<input class="form-check-input" type="checkbox" value="ventesEnCours" id="ventesEnCours" name="typeVentes">
  				<label class="form-check-label" for="ventesEnCours"> Mes ventes en cours </label>
  			</div>
            <div class="form-check">	
  				<input class="form-check-input" type="checkbox" value="ventesNonDebutees" id="ventesNonDebutees" name="typeVentes">
  				<label class="form-check-label" for="ventesNonDebutees"> Mes ventes non débutées </label>
  			</div>
            <div class="form-check">	
  				<input class="form-check-input" type="checkbox" value="ventesTerminees" id="ventesTerminees" name="typeVentes">
  				<label class="form-check-label" for="ventesTerminees"> Mes ventes terminées </label>
  			</div>
  		</div>
  		</div>
        <button type="submit" class="btn btn-default">Rechercher</button>
	</div>
</form>
       
   <c:if test="${!empty articles}">
	<div class="container">
		<% int compteur = 1; %>
		<div class="row">
		<c:forEach var="a" items="${articles}">
			<div class="col">
				<a href="${pageContext.request.contextPath}/utilisateur/afficherArticle?id=${a.getNumArticle()}">
					<h3>${a.getNomArticle()}</h3>
				</a>
				<h4>Prix : ${a.getPrixVente()} crédits</h4>
				<h4>Fin de l'enchère : ${a.getDateFinEnchere()}</h4>
				<h4>Vendeur : 
				<a href="${pageContext.request.contextPath}/profil?pseudo=${a.getPseudo()}">
					${a.getPseudo()}</a>
				</h4>
			</div>
			<c:choose>
				<c:when test="${compteur == 2}">
					</div><div class="row">
					<% compteur = 1; %>
				</c:when>
				<c:otherwise>
					<% compteur ++; %>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div></div>
</c:if>
<c:if test="${empty articles}">
	<c:if test="${!empty listeCodesErreur}">
		<div class="alert alert-danger" role="alert">
			<strong>Échec de la recherche</strong>
			<ul>
				<c:forEach var="code" items="${listeCodesErreur}">
					<li><h3>${LecteurMessage.getMessageErreur(code)}</h3></li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
</c:if>

	<script>
        function desactiverMesVentes()
        {
            document.getElementById("ventesEnCours").disabled = true;
            document.getElementById("ventesNonDebutees").disabled = true;
            document.getElementById("ventesTerminees").disabled = true;
            document.getElementById("encheresOuvertes").disabled = false;
            document.getElementById("encheresEnCours").disabled = false;
            document.getElementById("encheresRemportees").disabled = false;
        }
        function desactiverAchats()
        {
            document.getElementById("encheresOuvertes").disabled = true;
            document.getElementById("encheresEnCours").disabled = true;
            document.getElementById("encheresRemportees").disabled = true;
            document.getElementById("ventesEnCours").disabled = false;
            document.getElementById("ventesNonDebutees").disabled = false;
            document.getElementById("ventesTerminees").disabled = false;
        }
    </script>
</body>
</html>