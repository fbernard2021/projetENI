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
	<div class="container">
		<div class="row">
		<div class="col-12">
		<h2> Page de connexion</h2>
		<form action="${pageContext.request.contextPath}/connexion" method="post">
		
			<div class="form-group">
				<label for="identifiant">Pseudo ou email : </label>
				<input type="text"  class="form-control" name="pseudo" required/>
			</div>
			<div class="form-group">
				<label for="pseudo">Mot de passe : </label>
				<input type="password"  class="form-control" name="mdp" required/>
			</div>
			${message}
			</br>
			<button type="submit" class="btn btn-default">Se connecter</button>
		</form>
		</div>
		</div>
	</div>
</body>
</html>