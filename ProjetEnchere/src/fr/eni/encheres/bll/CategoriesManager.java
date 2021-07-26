package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categories;
import fr.eni.encheres.dal.CategoriesDAO;
import fr.eni.encheres.dal.DAOFactory;

public class CategoriesManager {
	
	private CategoriesDAO categoriesDAO;
	
	
	public CategoriesManager()
	{
		categoriesDAO = DAOFactory.getCategoriesDAO();
	}
	
	
	public List<Categories> selectionnerCategories() throws BusinessException
	{
		
		BusinessException exception = new BusinessException();
		List<Categories> listeCategories = new ArrayList<>();
		
		listeCategories = categoriesDAO.selectAll();
		
		this.verifierListe(listeCategories, exception);
		
		
		if(exception.hasErreurs())
		{
			throw exception;
		}
		else
		{
			return listeCategories;
		}
	}
	
	public int selectionnerNumeroCategorie(String libelle) throws BusinessException
	{
		BusinessException exception = new BusinessException();
		int num = 0;
		
		this.verifierNom(libelle, exception);
		
		num = categoriesDAO.selectNumCategorie(libelle);
		
		if(exception.hasErreurs())
		{
			throw exception;
		}
		else
		{
		return num;
		}
		
	}
	
	public String selectionnerNomCategorie(int num) throws BusinessException
	{
		
		BusinessException exception = new BusinessException();
		String nom;
		
		
		nom = categoriesDAO.selectNomCategorie(num);
		
		this.verifierNom(nom, exception);
		
		if(!exception.hasErreurs())
		{
			return nom;
		}
		else
		{
			throw exception;
		}
		
		
	}
	
	
	private void verifierListe(List<Categories> listeCategories, BusinessException exception)
	{
		if(listeCategories == null)
		{
			exception.ajouterErreur(CodesResultatBLL.LISTE_CATEGORIES_NULL);
		}
	}
	
	private void verifierNom(String libelle, BusinessException exception)
	{
		if(libelle == null)
		{
			exception.ajouterErreur(CodesResultatBLL.ERREUR_CATEGORIE_NULL);
		}
	}
	

}
