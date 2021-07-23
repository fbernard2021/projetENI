package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Retraits;

public interface RetraitsDAO {
	
	public Retraits selectByArticleVendu(int numArticle);
	public void insertRetraits(Retraits retrait);

}
