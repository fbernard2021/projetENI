package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticlesVendus;
import fr.eni.encheres.bo.Encheres;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EncheresDAO;
import fr.eni.encheres.dal.ArticlesVendusDAO;

public class ArticlesVendusManager {

	private ArticlesVendusDAO articlesVendusDAO;
	
	public ArticlesVendusManager()
	{
		this.articlesVendusDAO = DAOFactory.getArticlesVendusDAO();
	}
	
	public List<ArticlesVendus> selectionnerListeArticlesVendus() throws BusinessException
	{
		BusinessException exception = new BusinessException();
		
		List<ArticlesVendus> liste = new ArrayList<>();
		
		liste = articlesVendusDAO.selectAll();
		

		if(!exception.hasErreurs())
		{
		this.validerListe(liste, exception);
		}
		else
		{
			throw exception;
		}
		
		return liste;
		
	}
	
	private void validerNomArticle(String nomArticle, BusinessException exception)
	{
		if(nomArticle.length() < 2 || nomArticle.length() >30)
		{
			exception.ajouterErreur(CodesResultatBLL.REGLE_TAILLE_NOM_ARTICLE_ERREUR);
		}
	}
	private void validerDescription(String description, BusinessException exception)
	{
		if(description.length() >300)
		{
			exception.ajouterErreur(CodesResultatBLL.REGLE_TAILLE_DESCRIPTION_ERREUR);
		}
	}
	
	private void validerListe(List<ArticlesVendus> liste, BusinessException exception)
	{
		if(liste == null)
		{
			exception.ajouterErreur(CodesResultatBLL.LISTE_ARTICLES_VENDUS_NULL);
		}
		
	}
}
