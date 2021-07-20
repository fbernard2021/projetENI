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

public class ArticlesVendusDAOJdbcImpl implements ArticlesVendusDAO{
	private static final String selectAll = "SELECT no_article, nom_article, description, date_debut_encheres,"
			+ " date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM Articles_Vendus;";

	@Override
	public List<ArticlesVendus> selectAll() {
		List<ArticlesVendus> donneesArticles = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			
			PreparedStatement pstmt = cnx.prepareStatement(selectAll, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				donneesArticles.add(new ArticlesVendus(rs.getInt(0), rs.getString(1), rs.getString(2),
						rs.getDate(3), rs.getDate(4),rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return donneesArticles;
	}
}
