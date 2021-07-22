package fr.eni.encheres.bo;

import java.sql.Date;

public class ArticlesVendus {
	private int numArticle;
	private String nomArticle;
	private String description;
	private Date dateDebutEnchere;
	private Date dateFinEnchere;
	private int prixInitial;
	private int prixVente;
	private int numUtilisateur;
	private int numCategorie;
	private String pseudo;
	
	public ArticlesVendus(int numArticle, String nomArticle, String description, Date dateDebutEnchere,
			Date dateFinEnchere, int prixInitial, int prixVente,int numUtilisateur, int numCategorie)
	{
		this.numArticle = numArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEnchere = dateDebutEnchere;
		this.dateFinEnchere = dateFinEnchere;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.numUtilisateur = numUtilisateur;
		this.numCategorie = numCategorie;
	}
	
	public ArticlesVendus(int numArticle, String nomArticle, String description, Date dateFinEnchere,
			 int prixVente,String pseudo)
	{
		this.numArticle = numArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEnchere = dateFinEnchere;
		this.prixVente = prixVente;
		this.pseudo = pseudo;
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

	public Date getDateDebutEnchere()
	{
		return dateDebutEnchere;
	}
	public void setDateDebutEnchere(Date dateDebutEnchere)
	{
		this.dateDebutEnchere = dateDebutEnchere;
	}

	public Date getDateFinEnchere()
	{
		return dateFinEnchere;
	}
	public void setDateFinEnchere(Date dateFinEnchere)
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
	
	
}
