package fr.eni.encheres.dal;

public abstract class DAOFactory {
	
	public static UtilisateursDAO getUtilisateursDAO()
	{
		return new UtilisateursDAOJdbcImpl();
	}
}
