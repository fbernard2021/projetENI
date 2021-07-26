package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateurs;

public interface UtilisateursDAO {
	
	public List<Utilisateurs> selectAll();
	public Utilisateurs selectByPseudo(String pseudo);
	public Utilisateurs selectById(int id);
	public String selectPseudoById(int id);
	public void updateCredit(Utilisateurs utilisateur);
	public Utilisateurs connection(String id, String motDePasse) throws BusinessException;
	public void insert(Utilisateurs utilisateur, String motDePasse) throws BusinessException;
	public void updateUser(Utilisateurs utilisateur,String motDePasse ,String newMotDePasse, String ancienPseudo) throws BusinessException;
	public void deleteUser(Utilisateurs utilisateur, String motDePasse) throws BusinessException;

}