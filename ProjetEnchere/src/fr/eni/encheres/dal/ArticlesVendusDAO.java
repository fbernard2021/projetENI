package fr.eni.encheres.dal;

import java.util.List;
import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticlesVendus;
import fr.eni.encheres.bo.Encheres;

public interface ArticlesVendusDAO {
	public List<ArticlesVendus> selectAll();
	public List<ArticlesVendus> selectAccueil();
	public ArticlesVendus selectById(int numArticle);
	public List<ArticlesVendus> selectByUser(int numUser);
	public List<ArticlesVendus> rechercherArticles(String recherche, String nomCategorie);
	public void insertArticle(ArticlesVendus article) throws BusinessException;
	
}
