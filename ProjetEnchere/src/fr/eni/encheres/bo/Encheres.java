package fr.eni.encheres.bo;

import java.time.LocalDate;
import java.util.Date;

public class Encheres {
	private int numUtilisateur;
	private int numArticle;
	private LocalDate dateEnchere;
	private int montantEnchere;
	
	public Encheres(int numUtilisateur, int numArticle, LocalDate dateEnchere, int montantEnchere)
	{
		this.numUtilisateur = numUtilisateur;
		this.numArticle = numArticle;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}
	
	public int getNumUtilisateur()
	{
		return numUtilisateur;
	}
	public void setNumUtilisateur(int numUtilisateur)
	{
		this.numUtilisateur = numUtilisateur;
	}

	public int getNumArticle()
	{
		return numArticle;
	}
	public void setNumArticle(int numArticle)
	{
		this.numArticle = numArticle;
	}

	public LocalDate getDateEnchere()
	{
		return dateEnchere;
	}
	public void setDateEnchere(LocalDate dateEnchere)
	{
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere()
	{
		return montantEnchere;
	}
	public void setMontantEnchere(int montantEnchere)
	{
		this.montantEnchere = montantEnchere;
	}
}
