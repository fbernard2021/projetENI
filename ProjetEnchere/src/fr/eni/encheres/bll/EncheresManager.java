package fr.eni.encheres.bll;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Encheres;
import fr.eni.encheres.bo.Utilisateurs;
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
		}
		else
		{
			throw exception;
		}
		
		return liste;
		
	}
	
	private void validerListe(List<Encheres> liste, BusinessException exception)
	{
		if(liste == null)
		{
			exception.ajouterErreur(CodesResultatBLL.LISTE_ENCHERES_NULL);
		}
		
	}
}
