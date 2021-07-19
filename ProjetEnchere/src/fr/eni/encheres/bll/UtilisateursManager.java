package fr.eni.encheres.bll;

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
								String ville, String motDePasse,  int credit, int administrateur) throws BusinessException
	{
		
		Utilisateurs utilisateur = new Utilisateurs(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur);
		
		utilisateursDAO.insert(utilisateur);
		
		return utilisateur;
	}
	
	public List<Utilisateurs> selectionnerListeUtilisateurs()
	{
		List<Utilisateurs> liste = new ArrayList<>();
		
		liste = utilisateursDAO.selectAll();
		
		return liste;
		
	}
	
	public Utilisateurs selectionnerUtilisateurParPseudo(String pseudo, String motDePasse) throws BusinessException
	{
		Utilisateurs utilisateur = utilisateursDAO.selectByPseudo(pseudo, motDePasse);
		
		return utilisateur;
	}
	
	public Utilisateurs selectionnerUtilisateursParMail(String email, String motDePasse) throws BusinessException
	{
		Utilisateurs utilisateur = utilisateursDAO.selectByMail(email, motDePasse);
		
		return utilisateur;
		
	}

}
