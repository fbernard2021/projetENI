package fr.eni.encheres.dal;

import java.util.List;
import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticlesVendus;
import fr.eni.encheres.bo.Encheres;

public interface ArticlesVendusDAO {
	public List<ArticlesVendus> selectAll();
	public List<ArticlesVendus> selectAccueil();
	public List<ArticlesVendus> selectListeParCategorie(String nomCategorie);
	public ArticlesVendus selectById(int numArticle);
	public void insertArticle(ArticlesVendus article) throws BusinessException;
}
