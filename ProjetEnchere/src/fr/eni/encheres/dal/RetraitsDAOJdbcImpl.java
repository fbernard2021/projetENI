package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Retraits;

public class RetraitsDAOJdbcImpl implements RetraitsDAO {
	
	private static final String selectByArticleVendu = "SELECT rue, code_postal, ville FROM RETRAITS WHERE no_article = ? ;";
	
	private static final String insertRetraits = "INSERT INTO RETRAITS VALUES (?,?,?,?) ;";
	

	@Override
	public Retraits selectByArticleVendu(int numArticle) {
		Retraits retrait = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) 
		{
			PreparedStatement pstmt = cnx.prepareStatement(selectByArticleVendu);
			pstmt.setInt(1, numArticle);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				retrait = new Retraits(rs.getString(1), rs.getInt(2), rs.getString(3));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retrait;
	}

	@Override
	public void insertRetraits(Retraits retrait) {
		
		BusinessException exception = new BusinessException();
		try(Connection cnx = ConnectionProvider.getConnection()) 
		{
			PreparedStatement pstmt = cnx.prepareStatement(insertRetraits);
			pstmt.setInt(1, retrait.getNumArticle());
			pstmt.setString(2, retrait.getRue());
			pstmt.setInt(3, retrait.getCodePostal());
			pstmt.setString(4, retrait.getVille());
			pstmt.executeUpdate();

			
		} catch (SQLException e) {
			e.printStackTrace();
			if(e.getMessage().contains("retraits_articles_vendus_fk"))
			{
			exception.ajouterErreur(CodesResultatDAL.ERREUR_ARTICLE_INTROUVABLE);
			}
		}
		

	}

}


