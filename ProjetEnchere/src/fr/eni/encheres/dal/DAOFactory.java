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
	
	public static ArticlesVendusDAO getArticlesVendusDAO()
	{
		return new ArticlesVendusDAOJdbcImpl();
	}
	
	public static CategoriesDAO getCategoriesDAO()
	{
		return new CategoriesDAOJdbcImpl();
	}
	
	public static RetraitsDAO getRetraitsDAO()
	{
		return new RetraitsDAOJdbcImpl();
	}
}
