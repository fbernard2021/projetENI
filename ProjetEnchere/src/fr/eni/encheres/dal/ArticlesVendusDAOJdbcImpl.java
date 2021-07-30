package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.ArticlesVendus;
import fr.eni.encheres.bo.Encheres;


public class ArticlesVendusDAOJdbcImpl implements ArticlesVendusDAO{
	private static final String selectAll = "SELECT no_article, nom_article, description, date_debut_encheres,"
			+ " date_fin_encheres, prix_initial, no_utilisateur, no_categorie, etat_vente FROM Articles_Vendus;";

	private static final String selectAccueil = "SELECT no_article, nom_article, description, date_fin_encheres,"
			+ " prix_initial, prix_vente, pseudo, etat_vente FROM Articles_Vendus a INNER JOIN Utilisateurs u"
			+ " ON a.no_utilisateur = u.no_utilisateur;";
	
	private static final String selectById = "SELECT no_article, nom_article, description, date_debut_encheres,"
			+ " date_fin_encheres, prix_initial, no_utilisateur, no_categorie, etat_vente "
			+ " FROM Articles_Vendus WHERE no_article=?;";
	
	private static final String insertArticle = "INSERT INTO ARTICLES_VENDUS "
			+ "(nom_article, description, date_debut_encheres,date_fin_encheres, prix_initial, prix_vente,"
			+ " no_utilisateur, no_categorie, etat_vente) VALUES (?,?,?,?,?,?,?,?,?);";
	
	private static final String selectByUser = "SELECT no_article, nom_article, description, date_debut_encheres,"
			+ " date_fin_encheres, prix_initial, no_utilisateur, no_categorie, etat_vente "
			+ " FROM Articles_Vendus WHERE no_utilisateur= ? ;";
	
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
				donneesArticles.add(new ArticlesVendus(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(),rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9)));
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
				
				//no_article, nom_article, description, date_fin_encheres,prix_initial, prix_vente, pseudo, etat_vente
				donneesArticles.add(new ArticlesVendus(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getDate(4).toLocalDate(),rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8)));
				
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
				
			
				
				unArticle = new ArticlesVendus(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(),rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return unArticle;
	}
	
	@Override
	public List<ArticlesVendus> rechercherArticles(String recherche, String nomCategorie)
	{
		List<ArticlesVendus> donneesArticles = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			// on construit une requête
			String selectFrom ="SELECT no_article, nom_article, description, date_fin_encheres, prix_initial, prix_vente, pseudo, etat_vente FROM Articles_Vendus a"; 	
			String where ="";
				
			String inner =" INNER JOIN Utilisateurs u ON a.no_utilisateur = u.no_utilisateur";
			if(!recherche.equals(""))
			{
				where = " WHERE a.nom_article like ?";
			}
			
			// Si l'utilisateur a sélectionné une catégorie
			if (! nomCategorie.equals("toutesCategories"))
			{
				inner += " INNER JOIN Categories c ON a.no_categorie = c.no_categorie";
				if(!recherche.equals(""))
				{
					where += " AND c.libelle=?";
				}
				else
				{
					where = " WHERE c.libelle=?";
				}
			}
			
			// on concatène les morceaux de requête
			String requete = selectFrom + inner + where + ";";
			PreparedStatement pstmt = cnx.prepareStatement(requete);
			
			if(!recherche.equals(""))
			{
				pstmt.setString(1, "%"+recherche+"%");
			}
			if (! nomCategorie.equals("toutesCategories"))
			{
				if(!recherche.equals(""))
				{
					pstmt.setString(2, nomCategorie);
				}
				else
				{
					pstmt.setString(1, nomCategorie);
				}
			}
			
			// on exécute et on récupère les résultats
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				donneesArticles.add(new ArticlesVendus(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getDate(4).toLocalDate(),rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return donneesArticles;
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
			pstmt.setDate(3, Date.valueOf(article.getDateDebutEnchere()));
			pstmt.setDate(4, Date.valueOf(article.getDateFinEnchere()));
			pstmt.setInt(5, article.getPrixInitial());
			pstmt.setInt(6, article.getPrixVente());
			pstmt.setInt(7, article.getNumUtilisateur());
			pstmt.setInt(8, article.getNumCategorie());
			pstmt.setString(9, article.getEtatVente());
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

	@Override
	public List<ArticlesVendus> selectByUser(int numUser) {
		List<ArticlesVendus> liste = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			
			PreparedStatement pstmt = cnx.prepareStatement(selectById);
			pstmt.setInt(1, numUser);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next())
			{
				liste.add( new ArticlesVendus(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate(),rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}
}
