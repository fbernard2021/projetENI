package fr.eni.encheres.dal;

public abstract class DAOFactory {
	
	public static UtilisateursDAO getUtilisateursDAO()
	{
		return new UtilisateursDAOJdbcImpl();
	}
	
	public static EncheresDAO getEncheresDAO()
	{
		return new EncheresDAOJdbcImpl();
	}
}
