package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticlesVendus;
import fr.eni.encheres.bo.Encheres;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EncheresDAO;

public class EncheresManager {

	private EncheresDAO encheresDAO;
	
	public EncheresManager()
	{
		this.encheresDAO = DAOFactory.getEncheresDAO();
	}
	
	public List<Encheres> selectionnerListeEncheres() throws BusinessException
	{
		BusinessException exception = new BusinessException();
		
		List<Encheres> liste = new ArrayList<>();
		
		liste = encheresDAO.selectAll();
		

		if(!exception.hasErreurs())
		{
			this.validerListe(liste, exception);
			return liste;
		}
		else
		{
			throw exception;
		}
	}
	
	
	public Encheres selectionnerDerniereEnchere(ArticlesVendus article) throws BusinessException
	{
		Encheres enchere = null;
		BusinessException exception = new BusinessException();		
		enchere = encheresDAO.selectLastEnchere(article.getNumArticle());
		
		if(enchere != null)
		{
			this.validerMontant(enchere, article, exception);
		}
		
		if(!exception.hasErreurs())
		{
			return enchere;
		}
		else
		{
			throw exception;
		}
	}
	
	public Encheres selectionnerMeilleurOffre(int numArticle) throws BusinessException
	{
		Encheres enchere = null;
		BusinessException exception = new BusinessException();		
		enchere = encheresDAO.selectMeilleureOffre(numArticle);
		
		if(enchere != null)
		{
			this.validerMontant(enchere, exception);
		}
		
		if(!exception.hasErreurs())
		{
			return enchere;
		}
		else
		{
			throw exception;
		}	
	}

	public void insererEnchere(Encheres enchere) throws BusinessException
	{
		BusinessException exception = new BusinessException();
		
		Encheres encherePrecedente = null;
		
		this.validerEnchere(enchere, exception);
		
		encherePrecedente = encheresDAO.selectByID(enchere);
		
		
		if(!exception.hasErreurs())
		{
			if(encherePrecedente == null)
			{
				encheresDAO.insert(enchere);
			}
			else
			{
				encheresDAO.updateEnchere(enchere);
			}
		}
		else
		{
			throw exception;
		}
		
		
	}
	
	
	private void validerEnchere(Encheres enchere, BusinessException exception)
	{
		if(enchere == null)
		{
			exception.ajouterErreur(CodesResultatBLL.ERREUR_ENCHERES_NULL);
		}
	}
	
	private void validerMontant(Encheres enchere, BusinessException exception)
	{
		if(enchere == null)
		{
			exception.ajouterErreur(CodesResultatBLL.ERREUR_ENCHERES_NULL);
		}
	}
	
	private void validerListe(List<Encheres> liste, BusinessException exception)
	{
		if(liste == null)
		{
			exception.ajouterErreur(CodesResultatBLL.LISTE_ENCHERES_NULL);
		}
		
	}
	
	private void validerMontant(Encheres enchere, ArticlesVendus article, BusinessException exception)
	{
		if(enchere.getMontantEnchere() <= article.getPrixInitial())
		{
			exception.ajouterErreur(CodesResultatBLL.ERREUR_PRIX_NON_VALIDE);
		}
		
	}
}
