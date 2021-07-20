<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
</head>
<body>
	<%@ include file = "entete.jsp" %>
	
	<div align="center">
	<h2> Page de connexion</h2>
	<form action="${pageContext.request.contextPath}/connexion">
	
		<div class="form-group">
			<label for="identifiant">Pseudo ou email : </label>
			<input type="text" name="pseudo" required/>
		</div>
		<div class="form-group">
			<label for="pseudo">Mot de passe : </label>
			<input type="password" name="mdp" required/>
		</div>
		
		${message}
		</br>
		<button type="submit">Se connecter</button>
	</form>
	</div>
</body>
</html>