package fr.eni.encheres.dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_OBJET_NULL=10000;
	
	/**
	 * Echec général quand erreur non gérée à l'insertion 
	 */
	public static final int INSERT_OBJET_ECHEC=10001;
	
	/**
	 * Echec de l'insertion d'un pseudo vide
	 */
	public static final int INSERT_PSEUDO_NULL=10002;
	
	/**
	 * Echec de l'insertion d'un mail vide
	 */
	public static final int INSERT_MAIL_NULL=10003;
	
	/**
	 * Echec de l'insertion d'un pseudo vide
	 */
	public static final int INSERT_MDP_NULL=10004;
	
	public static final int UTILISATEUR_INCONNU=10005;
	
	public static final int INSERT_USER_ECHEC=10006;
	
	
	
	
}
