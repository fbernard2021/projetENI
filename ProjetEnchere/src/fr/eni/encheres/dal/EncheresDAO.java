package fr.eni.encheres.dal;

import java.util.List;
import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Encheres;

public interface EncheresDAO {
	public List<Encheres> selectAll();
	public Encheres selectLastEnchere(int numArticle);
}
