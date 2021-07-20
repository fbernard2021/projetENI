package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateurs;

public interface UtilisateursDAO {
	
	public List<Utilisateurs> selectAll();
	public Utilisateurs confirmConnection(String pseudo, String motDePasse) throws BusinessException;
	void insert(Utilisateurs utilisateur, String motDePasse) throws BusinessException;

}
