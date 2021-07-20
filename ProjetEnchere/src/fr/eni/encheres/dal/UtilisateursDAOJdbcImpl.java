package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateurs;




public class UtilisateursDAOJdbcImpl implements UtilisateursDAO {
	
	private static final String selectAll = "SELECT pseudo,nom,prenom,email,telephone,rue,code_postal,ville,credit,administrateur FROM Utilisateurs;";

	
	private static final String confirmConnection = "SELECT pseudo,nom,prenom,email,telephone,rue,code_postal,ville,credit,administrateur FROM Utilisateurs WHERE pseudo = ? AND mot_de_passe = ?;";
	
	
	private static final String insert = "INSERT INTO UTILISATEURS(pseudo,nom,prenom,email,telephone,rue,code_postal"
									   + ",ville,mot_de_passe,credit,administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?); ";

	@Override
	public List<Utilisateurs> selectAll() {

		List<Utilisateurs> donnee = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			
			PreparedStatement pstmt = cnx.prepareStatement(selectAll, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				donnee.add(new Utilisateurs(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getInt(10), rs.getInt(11)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return donnee;
	}
	

	@Override
	public Utilisateurs confirmConnection(String pseudo, String motDePasse) throws BusinessException {
		if(pseudo == null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_PSEUDO_NULL);
			throw businessException;
		}
		if(motDePasse == null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_MDP_NULL);
			throw businessException;
		}
		Utilisateurs donnee = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			
			PreparedStatement pstmt = cnx.prepareStatement(confirmConnection, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, pseudo);
			pstmt.setString(2, motDePasse);
			pstmt.executeQuery();
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.last();
			if(rs.getRow() == 0)
			{
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.UTILISATEUR_INCONNU);
				throw businessException;
			}
			rs.first();
			if(rs.next())
			{
				donnee =new Utilisateurs(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getInt(10), rs.getInt(11));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return donnee;
	}

	public void insert(Utilisateurs utilisateur, String motDePasse) throws BusinessException {
		if(utilisateur == null)
		{
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2,utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setInt(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, motDePasse);
			pstmt.setInt(10, utilisateur.getCredit());
			if(utilisateur.isAdministrateur() == true)
			{
				pstmt.setInt(11, 1);
			}
			else
			{
				pstmt.setInt(11, 0);
			}
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				utilisateur.setNumUtilisateur(rs.getInt(1));
			}
		} catch (SQLException e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	}




}