<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class ="container-fluid">
			<a class="navbar-brand" href="#">ENI-Enchères</a>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-12">
				<h1>Mon Profil</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<form action="#">
					<div class="form-group">
						<label for="pseudo">Pseudo :</label>
						<input type="text" class="form-control" id="pseudo">
					</div>
					<div class="form-group">
						<label for="nom">Nom :</label>
						<input type="text" class="form-control" id="nom">
					</div>
					<div class="form-group">
						<label for="prenom">Prénom :</label>
						<input type="text" class="form-control" id="prenom">
					</div>
					<div class="form-group">
						<label for="email">Email :</label>
						<input type="email" class="form-control" id="email">
					</div>
					<div class="form-group">
						<label for="telephone">Téléphone :</label>
						<input type="text" class="form-control" id="telephone">
					</div>
					<div class="form-group">
						<label for="rue">Rue :</label>
						<input type="text" class="form-control" id="rue">
					</div>
					<div class="form-group">
						<label for="postal">Code postale :</label>
						<input type="number" class="form-control" id="postal">
					</div>
					<div class="form-group">
						<label for="ville">Ville :</label>
						<input type="text" class="form-control" id="ville">
					</div>
					<div class="form-group">
						<label for="mdp">Mot de passe :</label>
						<input type="password" class="form-control" id="mdp">
					</div>
					<div class="form-group">
						<label for="mdpConfirm">Confirmation :</label>
						<input type="password" class="form-control" id="mdpConfirm">
					</div>
					<button type="submit" class="btn btn-default">Créer</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>