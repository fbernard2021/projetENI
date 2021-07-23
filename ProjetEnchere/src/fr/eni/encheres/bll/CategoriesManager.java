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
	
	
	private void verifierListe(List<Categories> listeCategories, BusinessException exception)
	{
		if(listeCategories == null)
		{
			exception.ajouterErreur(CodesResultatBLL.LISTE_CATEGORIES_NULL);
		}
	}
	

}
