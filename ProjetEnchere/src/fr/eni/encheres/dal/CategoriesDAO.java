package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Categories;

public interface CategoriesDAO {
	
	public List<Categories> selectAll();

}
