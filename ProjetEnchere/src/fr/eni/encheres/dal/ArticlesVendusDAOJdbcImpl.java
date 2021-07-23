package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticlesVendus;
import fr.eni.encheres.bo.Encheres;
import fr.eni.encheres.bo.Utilisateurs;

public class ArticlesVendusDAOJdbcImpl implements ArticlesVendusDAO{
	private static final String selectAll = "SELECT nom_article, description, date_debut_encheres,"
			+ " date_fin_encheres, prix_initial, no_utilisateur, no_categorie FROM Articles_Vendus;";

	private static final String selectAccueil = "SELECT no_article, nom_article, description, date_fin_encheres,"
			+ "prix_vente, pseudo FROM Articles_Vendus a INNER JOIN Utilisateurs u "
			+ "ON a.no_utilisateur = u.no_utilisateur;";
	
	private static final String selectListeParCategorie = "SELECT no_article, nom_article, description, date_fin_encheres,"
			+ "prix_vente, pseudo FROM Articles_Vendus a "
			+ "INNER JOIN Utilisateurs u ON a.no_utilisateur = u.no_utilisateur"
			+ "INNER JOIN Categories c ON a.no_categorie = c.no_categorie"
			+ "WHERE a.no_categorie =?;";
	
	private static final String selectById = "SELECT no_article, nom_article, description, date_debut_encheres,"
			+ " date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie"
			+ " FROM Articles_Vendus WHERE no_article=?;";
	
	private static final String insertArticle = "INSERT INTO ARTICLES_VENDUS "
			+ "(nom_article, description, date_debut_encheres,date_fin_encheres, prix_initial, prix_vente,"
			+ " no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?,?);";
	@Override
	public List<ArticlesVendus> selectAll()
	{
		List<ArticlesVendus> donneesArticles = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(selectAll);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				donneesArticles.add(new ArticlesVendus(rs.getString(1), rs.getString(2),
						rs.getDate(3), rs.getDate(4),rs.getInt(5), rs.getInt(6), rs.getInt(7)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return donneesArticles;
	}
	
	@Override
	public List<ArticlesVendus> selectAccueil()
	{
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
	public List<ArticlesVendus> selectListeParCategorie(String nomCategorie)
	{
		List<ArticlesVendus> donneesArticles = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(selectListeParCategorie);
			pstmt.setString(1, nomCategorie);
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
	public ArticlesVendus selectById(int numArticle)
	{
		ArticlesVendus unArticle = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			
			PreparedStatement pstmt = cnx.prepareStatement(selectById);
			pstmt.setInt(1, numArticle);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next())
			{
				unArticle = new ArticlesVendus(rs.getString(1), rs.getString(2),
						rs.getDate(3), rs.getDate(4),rs.getInt(5), rs.getInt(6), rs.getInt(7));
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateDebutStr = sdf.format(article.getDateDebutEnchere());
			String dateFinStr = sdf.format(article.getDateFinEnchere());
			pstmt.setDate(3,  Date.valueOf(dateDebutStr));
			pstmt.setDate(4, Date.valueOf(dateFinStr));
			pstmt.setInt(5, article.getPrixInitial());
			pstmt.setInt(6, article.getPrixVente());
			pstmt.setInt(7, article.getNumUtilisateur());
			pstmt.setInt(8, article.getNumCategorie());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next())
			{
				article.setNumArticle(rs.getInt(1));
			}
		} catch (SQLException e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	}
}
