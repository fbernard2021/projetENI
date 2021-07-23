
package fr.eni.encheres.bll;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Retraits;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.RetraitsDAO;

public class RetraitsManager {
	
	RetraitsDAO retraitDAO;
	
	public RetraitsManager()
	{
		retraitDAO = DAOFactory.getRetraitsDAO();
	}
	
	public void ajouterRetrait(Retraits retrait) throws BusinessException
	{
		BusinessException exception = new BusinessException();
		
		this.validerRetrait(retrait, exception);
		this.validerNumArticle(retrait.getNumArticle(), exception);
		
		if(!exception.hasErreurs())
		{
			retraitDAO.insertRetraits(retrait);
		}
		else
		{
			throw exception;
		}
		
		
	}
	
	public Retraits selectionnerRetrait(int numeroArticle) throws BusinessException
	{
		BusinessException exception = new BusinessException();
		Retraits retrait = null;
		
		this.validerNumArticle(numeroArticle, exception);
		
		if(exception.hasErreurs())
		{
			throw exception;
		}
		else 
		{
			retrait = retraitDAO.selectByArticleVendu(numeroArticle);
			return retrait;
		}
		
		
		
		
		
	}
	
	private void validerRetrait(Retraits retrait, BusinessException exception)
	{
		if(retrait == null)
		{
			exception.ajouterErreur(CodesResultatBLL.RETRAIT_NULL);
		}
	}
	

	@SuppressWarnings("unused")
	private void validerNumArticle(int numArticle, BusinessException exception)
	{
		if((Integer)numArticle == null)
		{
			exception.ajouterErreur(CodesResultatBLL.NUM_ARTICLE_INTROUVABLE);
		}
	}

}
