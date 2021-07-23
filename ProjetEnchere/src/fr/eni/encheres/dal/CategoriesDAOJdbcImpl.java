package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Categories;

public class CategoriesDAOJdbcImpl implements CategoriesDAO {
	
	
	private static final String selectAll = "SELECT libelle FROM CATEGORIES;";

	@Override
	public List<Categories> selectAll() {
		List<Categories> liste = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) 
		{
			PreparedStatement pstmt = cnx.prepareStatement(selectAll);
			ResultSet rs = pstmt.executeQuery();	
			
			while(rs.next())
			{
				liste.add(new Categories(rs.getString(1)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return liste;
	}

}
