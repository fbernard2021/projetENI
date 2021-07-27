package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Encheres;

public class EncheresDAOJdbcImpl implements EncheresDAO {
	
	private static final String selectAll = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES;";
	private static final String selectLastEnchere = "SELECT TOP 1 no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES "
			+ "ORDER BY date_enchere DESC ;";
	private static final String insert = "INSERT INTO ENCHERES VALUES (?, ?, GETDATE(), ?);";
	private static final String selectByID = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere  FROM ENCHERES WHERE no_utilisateur = ? "
			+ "AND no_article = ? ;"; 
	private static final String updateEnchere = "UPDATE ENCHERES SET date_enchere = GETDATE() , montant_enchere = ? WHERE no_utilisateur = ? AND "
			+ "no_article = ? ;";

	@Override
	public List<Encheres> selectAll() {
		List<Encheres> donneesEncheres = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			
			PreparedStatement pstmt = cnx.prepareStatement(selectAll);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				donneesEncheres.add(new Encheres(rs.getInt(0), rs.getInt(1), rs.getDate(2), rs.getInt(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return donneesEncheres;
	}

	public Encheres selectLastEnchere(int numArticle) {
		Encheres enchere = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			
			PreparedStatement pstmt = cnx.prepareStatement(selectLastEnchere);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				enchere = new Encheres(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return enchere;
	}


	@Override
	public void insert(Encheres enchere) {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			
			PreparedStatement pstmt = cnx.prepareStatement(insert);
			pstmt.setInt(1, enchere.getNumUtilisateur());
			pstmt.setInt(2, enchere.getNumArticle());
			pstmt.setInt(3, enchere.getMontantEnchere());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public Encheres selectByID(Encheres enchere) {
		Encheres enchere1 = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			
			PreparedStatement pstmt = cnx.prepareStatement(selectByID);
			pstmt.setInt(1, enchere.getNumUtilisateur());
			pstmt.setInt(2, enchere.getNumArticle());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				enchere1 = new Encheres(rs.getInt(1),rs.getInt(2),rs.getDate(3), rs.getInt(4));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return enchere1;
	}

	@Override
	public void updateEnchere(Encheres enchere) {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			
			PreparedStatement pstmt = cnx.prepareStatement(updateEnchere);
			pstmt.setInt(1, enchere.getMontantEnchere());
			pstmt.setInt(2, enchere.getNumUtilisateur());
			pstmt.setInt(3, enchere.getNumArticle());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}



}
