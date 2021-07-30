<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>
      <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
      <a class="navbar-brand" href="${pageContext.request.contextPath}/accueil">
      	<img src="images/logo.jpg" alt="Logo" style="width:40px;">
      	ENI-Enchères
      </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
        <c:if test="${!empty utilisateur}">
        <li class="nav-item">
        	<a class="nav-link" href="${pageContext.request.contextPath}/utilisateur/vendreArticle">Nouvelle vente</a>
        </li>
        <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         	${utilisateur.pseudo}       
         </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="${pageContext.request.contextPath}/utilisateur/modifierProfil">modifier le profil</a>
          <a class="dropdown-item" href="${pageContext.request.contextPath}/utilisateur/suppressionCompte">supprimer le compte</a>
        </div>
        </li>
          <li class="nav-item ">
            <a class="nav-link" href="${pageContext.request.contextPath}/deconnexion">Déconnexion</a>
          </li>
       </c:if>
       <c:if test="${empty utilisateur}">
          <li class="nav-item ">
            <a class="nav-link" href="${pageContext.request.contextPath}/connexion">connexion</a>
          </li>
       
       </c:if>
      </ul>
    </div>
    </nav>
</body>
</html>