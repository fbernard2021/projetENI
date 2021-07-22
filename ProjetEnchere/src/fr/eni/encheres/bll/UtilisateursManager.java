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
		

		Utilisateurs utilisateur = new Utilisateurs(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, credit, administrateur);
		
		if(!exception.hasErreurs())
		{
		StringBuffer sb = this.SHA256(motDePasse);
		utilisateursDAO.insert(utilisateur, sb.toString());
		}
		else
		{
			throw exception;
		}
		
		return utilisateur;
	}
	
	public List<Utilisateurs> selectionnerListeUtilisateurs() throws BusinessException
	{
		BusinessException exception = new BusinessException();
		
		List<Utilisateurs> liste = new ArrayList<>();
		
		liste = utilisateursDAO.selectAll();
		

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
	
	
	public Utilisateurs selectionnerUtilisateur(String pseudo) throws BusinessException
	{
		BusinessException exception = new BusinessException();
		
		Utilisateurs utilisateur = null;
		
		utilisateur = utilisateursDAO.selectByPseudo(pseudo);
		
		this.validerUtilisateur(utilisateur, exception);
		
		if(exception.hasErreurs())
		{
			throw exception;
		}
		else
		{
			return utilisateur;
		}
	}
	
	public Utilisateurs connexion(String pseudo, String motDePasse) throws BusinessException, NoSuchAlgorithmException
	{
		BusinessException exception = new BusinessException();
		Utilisateurs utilisateur = null;
		
		this.validerPseudo(pseudo, exception);
		this.validerMotDePasse(motDePasse, exception);
		
		StringBuffer sb = this.SHA256(motDePasse);
		
		if(!exception.hasErreurs())
		{
			utilisateur = utilisateursDAO.connection(pseudo, sb.toString());
		}
		
		if(exception.hasErreurs())
		{
			throw exception;
		}
		

		return utilisateur;
	}
	
	
	public Utilisateurs modifierProfil(Utilisateurs utilisateur,String motDePasse, String newMotDePasse, String ancienPseudo) throws BusinessException, NoSuchAlgorithmException
	{
		BusinessException exception = new BusinessException();
		
		StringBuffer sb = new StringBuffer();
		
		this.validerUtilisateur(utilisateur, exception);
		this.validerPseudo(utilisateur.getPseudo(), exception);
		this.validerMotDePasse(motDePasse, exception);
		
		StringBuffer sb2 = this.SHA256(motDePasse);
		
		if(newMotDePasse != "")
		{
			this.validerMotDePasse(newMotDePasse, exception);
			sb = this.SHA256(newMotDePasse);
		}
		else
		{
			sb.append("null");
		}
		
		if(!exception.hasErreurs())
		{
			
			utilisateursDAO.updateUser(utilisateur,sb2.toString(), sb.toString(), ancienPseudo);
		}
		else
		{
			throw exception;
		}
		
		return utilisateur;
		
	}
	
	public void supprimerProfil(Utilisateurs utilisateur, String motDePasse) throws BusinessException, NoSuchAlgorithmException
	{
		BusinessException exception = new BusinessException();
		
		StringBuffer sb = new StringBuffer();
		
		this.validerUtilisateur(utilisateur, exception);
		this.validerPseudo(motDePasse, exception);
		this.validerMotDePasse(motDePasse, exception);
		
		sb = this.SHA256(motDePasse);
		
		if(!exception.hasErreurs())
		{
			utilisateursDAO.deleteUser(utilisateur, sb.toString());
		}
		else
		{
			throw exception;
		}
	}
	
	
	

	private void validerPseudo(String pseudo, BusinessException exception)
	{
		if(pseudo.length() < 8 || pseudo.length() >16)
		{
			exception.ajouterErreur(CodesResultatBLL.REGLE_TAILLE_PSEUDO_ERREUR);
		}
	}
	
	private void validerCredit(int credit, BusinessException exception)
	{
		if(credit < 0)
		{
			exception.ajouterErreur(CodesResultatBLL.ERREUR_CREDIT_NEGATIF);
		}
	}
	
	private void validerMotDePasse(String motDePasse, BusinessException exception)
	{
		if(motDePasse.length() < 8 || motDePasse.length() >16)
		{
			exception.ajouterErreur(CodesResultatBLL.REGLE_TAILLE_MDP_ERREUR);
		}
	}
	
	private void validerListe(List<Utilisateurs> liste, BusinessException exception)
	{
		if(liste == null)
		{
			exception.ajouterErreur(CodesResultatBLL.LISTE_UTILISATEURS_NULL);
		}
		
	}
	
	private void validerUtilisateur(Utilisateurs utilisateur, BusinessException exception)
	{
		if(utilisateur == null)
		{
			exception.ajouterErreur(CodesResultatBLL.UTILISATEUR_NULL);
		}
	}
	
	private StringBuffer SHA256(String motDePasse) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		 md.update(motDePasse.getBytes());
		 
		 byte byteData[] = md.digest();
		 
		 StringBuffer sb = new StringBuffer();
	     for (int i = 0; i < byteData.length; i++) {
	    	 
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	     }
		return sb;
		
	}

}