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
	private static final String selectNumCategorie = "SELECT no_categorie FROM CATEGORIES WHERE libelle = ? ;";
	private static final String selectNomCategorie = "SELECT libelle FROM CATEGORIES WHERE no_categorie = ? ;";

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

	@Override
	public int selectNumCategorie(String libelle) {
		int num = 0;
		
		try(Connection cnx = ConnectionProvider.getConnection()) 
		{
			PreparedStatement pstmt = cnx.prepareStatement(selectNumCategorie);
			pstmt.setString(1, libelle);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				num = rs.getInt(1);
			}
		
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		return num;
	}

	@Override
	public String selectNomCategorie(int num) {
		String nom = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) 
		{
			PreparedStatement pstmt = cnx.prepareStatement(selectNomCategorie);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				nom = rs.getString(1);
			}
		
		}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		return nom;
	}

}
