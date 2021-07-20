package fr.eni.encheres.bll;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateurs;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateursDAO;

public class UtilisateursManager {
	
	
	private UtilisateursDAO utilisateursDAO;
	
	
	public UtilisateursManager()
	{
		this.utilisateursDAO = DAOFactory.getUtilisateursDAO();
	}
	
	
	public Utilisateurs ajouter(String pseudo, String nom, String prenom,
								String email, String telephone,String rue, int codePostal, 
								String ville, String motDePasse,  int credit, int administrateur) throws BusinessException, NoSuchAlgorithmException
	{
		
		BusinessException exception = new BusinessException();
		
		this.validerMotDePasse(motDePasse, exception);
		this.validerPseudo(pseudo, exception);
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		 md.update(motDePasse.getBytes());
		 
		 byte byteData[] = md.digest();
		 
		 StringBuffer sb = new StringBuffer();
	     for (int i = 0; i < byteData.length; i++) {
	    	 
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	     }

		Utilisateurs utilisateur = new Utilisateurs(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, credit, administrateur);
		
		utilisateursDAO.insert(utilisateur, sb.toString());
		
		return utilisateur;
	}
	
	public List<Utilisateurs> selectionnerListeUtilisateurs()
	{
		List<Utilisateurs> liste = new ArrayList<>();
		
		liste = utilisateursDAO.selectAll();
		
		return liste;
		
	}
	
	public Utilisateurs connexion(String pseudo, String motDePasse) throws BusinessException, NoSuchAlgorithmException
	{
		BusinessException exception = new BusinessException();
		Utilisateurs utilisateur = null;
		
		this.validerPseudo(pseudo, exception);
		this.validerMotDePasse(motDePasse, exception);
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		 md.update(motDePasse.getBytes());
		 
		 byte byteData[] = md.digest();
		 
		 StringBuffer sb = new StringBuffer();
	     for (int i = 0; i < byteData.length; i++) {
	    	 
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	     }
		
		if(!exception.hasErreurs())
		{
			utilisateur = utilisateursDAO.confirmConnection(pseudo, sb.toString());
		}
		
		if(exception.hasErreurs())
		{
			throw exception;
		}
		

		return utilisateur;
	}
	

	private void validerPseudo(String pseudo, BusinessException exception)
	{
		if(pseudo.length() < 8 || pseudo.length() >16)
		{
			exception.ajouterErreur(CodesResultatBLL.REGLE_TAILLE_PSEUDO_ERREUR);
		}
	}
	
	private void validerMotDePasse(String motDePasse, BusinessException exception)
	{
		if(motDePasse.length() < 8 || motDePasse.length() >16)
		{
			exception.ajouterErreur(CodesResultatBLL.REGLE_TAILLE_MDP_ERREUR);
		}
	}
	

}
