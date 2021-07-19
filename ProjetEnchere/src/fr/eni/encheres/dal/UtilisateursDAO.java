package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Utilisateurs;

public interface UtilisateursDAO {
	
	public List<Utilisateurs> selectAll();
	public List<Utilisateurs> selectByPseudo(String pseudo);
	public void insert(Utilisateurs utilisateur);

}
