package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Encheres;

public class EncheresDAOJdbcImpl implements EncheresDAO {
	
	private static final String selectAll = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM Encheres;";

	@Override
	public List<Encheres> selectAll() {
		List<Encheres> donneesEncheres = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			
			PreparedStatement pstmt = cnx.prepareStatement(selectAll, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				donneesEncheres.add(new Encheres(rs.getInt(0), rs.getInt(1), rs.getDate(2), rs.getInt(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return donneesEncheres;
	}

}
