package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticlesVendus;
import fr.eni.encheres.bo.Encheres;
import fr.eni.encheres.bo.Utilisateurs;

public class ArticlesVendusDAOJdbcImpl implements ArticlesVendusDAO{
	private static final String selectAll = "SELECT no_article, nom_article, description, date_debut_encheres,"
			+ " date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM Articles_Vendus;";

	private static final String selectAccueil = "SELECT no_article, nom_article, description, date_fin_encheres,"
			+ "prix_vente, pseudo FROM Articles_Vendus a INNER JOIN Utilisateurs u "
			+ "ON a.no_utilisateur = u.no_utilisateur;";
	
	private static final String selectById = "SELECT no_article, nom_article, description, date_debut_encheres,"
			+ " date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie"
			+ " FROM Articles_Vendus WHERE no_article=?;";
	
	private static final String insertArticle = "INSERT INTO ARTICLES_VENDUS"
			+ "(nom_article, description, date_debut_encheres,date_fin_encheres, prix_initial, prix_vente,"
			+ " no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
	@Override
	public List<ArticlesVendus> selectAll() {
		List<ArticlesVendus> donneesArticles = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(selectAll);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				donneesArticles.add(new ArticlesVendus(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getDate(4), rs.getDate(5),rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return donneesArticles;
	}
	
	public List<ArticlesVendus> selectAccueil() {
		List<ArticlesVendus> donneesArticles = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(selectAccueil);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				donneesArticles.add(new ArticlesVendus(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getDate(4),rs.getInt(5), rs.getString(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return donneesArticles;
	}

	@Override
	public ArticlesVendus selectById(int numArticle) {
		ArticlesVendus unArticle = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			
			PreparedStatement pstmt = cnx.prepareStatement(selectById);
			pstmt.setInt(1, numArticle);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next())
			{
				unArticle = new ArticlesVendus(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4),
						rs.getDate(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unArticle;
	}

	@Override
	public void insertArticle(ArticlesVendus article) throws BusinessException {
		if(article == null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(insertArticle, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2,article.getDescription());
			pstmt.setDate(3, article.getDateDebutEnchere());
			pstmt.setDate(4, article.getDateFinEnchere());
			pstmt.setInt(5, article.getPrixInitial());
			pstmt.setInt(6, article.getPrixVente());
			pstmt.setInt(7, article.getNumUtilisateur());
			pstmt.setInt(8, article.getNumCategorie());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			// pense pas que ça soit utile parce que quand j'ai inséré des articles manuellement dans la bdd
			// ça m'a dit que ça autoincrémentait
			// mais je mets ça là au cas où
			/* if(rs.next())
			{
				article.setNumArticle(rs.getInt(1));
			}*/
		} catch (SQLException e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	}
}
