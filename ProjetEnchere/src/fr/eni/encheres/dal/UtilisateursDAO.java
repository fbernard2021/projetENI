package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateurs;

public interface UtilisateursDAO {
	
	public List<Utilisateurs> selectAll();
	public Utilisateurs selectByPseudo(String pseudo, String motDePasse) throws BusinessException;
	public Utilisateurs selectByMail(String mail, String motDePasse) throws BusinessException;
	public void insert(Utilisateurs utilisateur) throws BusinessException;

}
