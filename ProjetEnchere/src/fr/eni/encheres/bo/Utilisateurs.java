package fr.eni.encheres.bo;

public class Utilisateurs {
	
	private int numUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private int codePostal;
	private String ville;
	private int credit;
	private boolean administrateur;
	
	public Utilisateurs()
	{
		
	}
	
	
	public Utilisateurs(int numUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,String rue, int codePostal, String ville, int credit, int administrateur)
	{
		this.numUtilisateur = numUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.credit = credit;
		
		if(administrateur == 1)
		{
			this.administrateur = true;
		}
		else
		{
			this.administrateur = false;
		}
	}
	
	public Utilisateurs(String pseudo, String nom, String prenom, String email, String telephone,String rue, int codePostal, String ville, int credit, int administrateur)
	{

		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.credit = credit;
		
		if(administrateur == 1)
		{
			this.administrateur = true;
		}
		else
		{
			this.administrateur = false;
		}
	}
	
	public void clone(Utilisateurs utilisateur)
	{
		this.pseudo = utilisateur.getPseudo();
		this.nom = utilisateur.getNom();
		this.prenom = utilisateur.getPrenom();
		this.email = utilisateur.getEmail();
		this.telephone = utilisateur.getTelephone();
		this.rue = utilisateur.getRue();
		this.codePostal = utilisateur.getCodePostal();
		this.ville = utilisateur.getVille();
		this.credit = utilisateur.getCredit();
	}

	public String getPseudo() {
		return pseudo;
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getRue() {
		return rue;
	}
	
	public void setRue(String rue) {
		this.rue = rue;
	}
	
	public int getCodePostal() {
		return codePostal;
	}
	
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	
	public String getVille() {
		return ville;
	}
	
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	public int getCredit() {
		return credit;
	}
	
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	public boolean isAdministrateur() {
		return administrateur;
	}
	
	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}




	public int getNumUtilisateur() {
		return numUtilisateur;
	}



	public void setNumUtilisateur(int numUtilisateur) {
		this.numUtilisateur = numUtilisateur;
	}
	


}