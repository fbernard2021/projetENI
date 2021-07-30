package fr.eni.encheres.bo;

import java.time.LocalDate;
import java.util.Date;

public class ArticlesVendus {
	private int numArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEnchere;
	private LocalDate dateFinEnchere;
	private int prixInitial;
	private int prixVente;
	private int numUtilisateur;
	private int numCategorie;
	private String pseudo;
	private String etatVente;
	
//	 nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie 
	
	public ArticlesVendus(int numArticle, String nomArticle, String description, LocalDate dateDebutEnchere,
			LocalDate dateFinEnchere, int prixInitial,int numUtilisateur, int numCategorie, String etatVente)
	{
		this.numArticle = numArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.prixInitial = prixInitial;
		this.numUtilisateur = numUtilisateur;
		this.numCategorie = numCategorie;
		this.etatVente = etatVente;
	}
	
	public ArticlesVendus( String nomArticle, String description, LocalDate dateDebutEnchere,
			LocalDate dateFinEnchere, int prixInitial,int numUtilisateur, int numCategorie, String etatVente)
	{

		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.prixInitial = prixInitial;
		this.numUtilisateur = numUtilisateur;
		this.numCategorie = numCategorie;
		this.etatVente = etatVente;
	}
	
	public ArticlesVendus(String nomArticle, String description, LocalDate dateDebutEnchere,
			LocalDate dateFinEnchere, int prixInitial, int prixVente, int numUtilisateur, int numCategorie)
	{
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.numUtilisateur = numUtilisateur;
		this.numCategorie = numCategorie;
	}
	
	public ArticlesVendus(int numArticle, String nomArticle, String description, LocalDate dateFinEnchere,
			 int prixInitial, int prixVente,String pseudo, String etatVente)
	{
		this.numArticle = numArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEnchere = dateFinEnchere;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.pseudo = pseudo;
		this.etatVente = etatVente;
	}

	public int getNumArticle()
	{
		return numArticle;
	}
	public void setNumArticle(int numArticle)
	{
		this.numArticle = numArticle;
	}

	public String getNomArticle()
	{
		return nomArticle;
	}
	public void setNomArticle(String nomArticle)
	{
		this.nomArticle = nomArticle;
	}

	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}

	public LocalDate getDateDebutEnchere()
	{
		return dateDebutEnchere;
	}
	public void setDateDebutEnchere(LocalDate dateDebutEnchere)
	{
		this.dateDebutEnchere = dateDebutEnchere;
	}

	public LocalDate getDateFinEnchere()
	{
		return dateFinEnchere;
	}
	public void setDateFinEnchere(LocalDate dateFinEnchere)
	{
		this.dateFinEnchere = dateFinEnchere;
	}

	public int getPrixInitial()
	{
		return prixInitial;
	}
	public void setPrixInitial(int prixInitial)
	{
		this.prixInitial = prixInitial;
	}

	public int getPrixVente()
	{
		return prixVente;
	}
	public void setPrixVente(int prixVente)
	{
		this.prixVente = prixVente;
	}

	public int getNumUtilisateur()
	{
		return numUtilisateur;
	}
	public void setNumUtilisateur(int numUtilisateur)
	{
		this.numUtilisateur = numUtilisateur;
	}

	public int getNumCategorie()
	{
		return numCategorie;
	}
	public void setNumCategorie(int numCategorie)
	{
		this.numCategorie = numCategorie;
	}

	public String getPseudo()
	{
		return pseudo;
	}
	public void setPseudo(String pseudo)
	{
		this.pseudo = pseudo;
	}

	public String getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}
	
	
}
