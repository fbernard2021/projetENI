package fr.eni.encheres.bll;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticlesVendus;
import fr.eni.encheres.bo.Utilisateurs;
import fr.eni.encheres.dal.DAOFactory;
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
	
	public ArticlesVendus selectionnerunArticle(int numArticle) throws BusinessException
	{
		BusinessException exception = new BusinessException();
		
		ArticlesVendus article = null;
		
		article = articlesVendusDAO.selectById(numArticle);
		
		this.validerArticle(article, exception);
		
		if(exception.hasErreurs())
		{
			throw exception;
		}
		else
		{
			return article;
		}
	}
	
	public ArticlesVendus ajouterArticle(int numArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int prixInitial, int prixVente, int numUtilisateur, int numCategorie) throws BusinessException
	{
		BusinessException exception = new BusinessException();
		
		this.validerNomArticle(nomArticle, exception);
		this.validerDescription(description, exception);
		

		ArticlesVendus article = new ArticlesVendus(numArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, prixVente, numUtilisateur, numCategorie);
		
		if(!exception.hasErreurs())
		{
		articlesVendusDAO.insertArticle(article);
		}
		else
		{
			throw exception;
		}
		
		return article;
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
	
	private void validerArticle(ArticlesVendus article, BusinessException exception)
	{
		if(article == null)
		{
			exception.ajouterErreur(CodesResultatBLL.ARTICLE_NULL);
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
