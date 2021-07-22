package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateurs;






public class UtilisateursDAOJdbcImpl implements UtilisateursDAO {
	
	private static final String selectAll = "SELECT pseudo,nom,prenom,email,telephone,rue,code_postal,ville,credit,administrateur FROM UTILISATEURS;";
	
	
	private static final String selectByPseudo = "SELECT pseudo,nom,prenom,email,telephone,rue,code_postal,ville,credit,administrateur FROM UTILISATEURS WHERE pseudo = ?;";

	
	private static final String confirmConnection = "SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit, administrateur FROM UTILISATEURS WHERE pseudo = ? AND mot_de_passe = ? OR email = ? AND mot_de_passe = ? ;";
	
	
	private static final String insert = "INSERT INTO UTILISATEURS(pseudo,nom,prenom,email,telephone,rue,code_postal"
									   + ",ville,mot_de_passe,credit,administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?); ";
	
	private static final String alterUserWithNewMdp = "UPDATE UTILISATEURS "
											  + "SET pseudo = ? , nom = ? ,prenom = ? ,email = ? ,telephone = ? ,rue = ? ,code_postal = ? ,ville = ? ,mot_de_passe = ? "
											  + "WHERE pseudo = ? AND mot_de_passe = ? ;";
	private static final String alterUser = "UPDATE UTILISATEURS "
			  							  + "SET pseudo = ? , nom = ? ,prenom = ? ,email = ? ,telephone = ? ,rue = ? ,code_postal = ? ,ville = ? "
			  							  + "WHERE pseudo = ? AND mot_de_passe = ? ;";
	
	private static final String deleteUser = "DELETE FROM UTILISATEURS where pseudo = ? AND mot_de_passe = ? ;";
	

	@Override
	public List<Utilisateurs> selectAll() {

		List<Utilisateurs> donnee = new ArrayList<>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			
			PreparedStatement pstmt = cnx.prepareStatement(selectAll);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.executeQuery();

			while(rs.next())
			{
				donnee.add(new Utilisateurs(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return donnee;
	}
	

	@Override
	public Utilisateurs connection(String id, String motDePasse) throws BusinessException {
		if(id == null)
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
			
			PreparedStatement pstmt = cnx.prepareStatement(confirmConnection);
			pstmt.setString(1, id);
			pstmt.setString(2, motDePasse);
			pstmt.setString(3, id);
			pstmt.setString(4, motDePasse);

			ResultSet rs = pstmt.executeQuery();

			while(rs.next())
			{

				donnee =new Utilisateurs(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10));
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
			if(e.getMessage().contains("uc_utilisateur_mail"))
			{
				businessException.ajouterErreur(CodesResultatDAL.INSERT_MAIL_ECHEC);
				throw businessException;
			}
			if(e.getMessage().contains("uc_utilisateur_pseudo"))
			{
				businessException.ajouterErreur(CodesResultatDAL.INSERT_PSEUDO_ECHEC);
				throw businessException;
			}

			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			
			throw businessException;
		}
	}


	@Override
	public Utilisateurs selectByPseudo(String pseudo) {
		
		Utilisateurs donnee = null;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			
			PreparedStatement pstmt = cnx.prepareStatement(selectByPseudo);
			pstmt.setString(1, pseudo);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next())
			{
				donnee = new Utilisateurs(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return donnee;
	}


	@Override
	public void updateUser(Utilisateurs utilisateur,String motDePasse ,String newMotDePasse, String ancienPseudo) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = null;

			if(newMotDePasse.compareTo("null") == 0)
			{
				
				pstmt = cnx.prepareStatement(alterUser);
				pstmt.setString(1, utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getNom());
				pstmt.setString(3, utilisateur.getPrenom());
				pstmt.setString(4, utilisateur.getEmail());
				pstmt.setString(5, utilisateur.getTelephone());
				pstmt.setString(6, utilisateur.getRue());
				pstmt.setInt(7, utilisateur.getCodePostal());
				pstmt.setString(8, utilisateur.getVille());
				pstmt.setString(9, ancienPseudo);
				pstmt.setString(10, motDePasse);
			}
			else
			{
				pstmt = cnx.prepareStatement(alterUserWithNewMdp);
				pstmt.setString(1, utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getNom());
				pstmt.setString(3, utilisateur.getPrenom());
				pstmt.setString(4, utilisateur.getEmail());
				pstmt.setString(5, utilisateur.getTelephone());
				pstmt.setString(6, utilisateur.getRue());
				pstmt.setInt(7, utilisateur.getCodePostal());
				pstmt.setString(8, utilisateur.getVille());
				pstmt.setString(9, newMotDePasse);
				pstmt.setString(10, ancienPseudo);
				pstmt.setString(11, motDePasse);
			}
				
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			if(e.getMessage().contains("uc_utilisateur_mail"))
			{
				businessException.ajouterErreur(CodesResultatDAL.INSERT_MAIL_ECHEC);
			}
			if(e.getMessage().contains("uc_utilisateur_pseudo"))
			{
				businessException.ajouterErreur(CodesResultatDAL.INSERT_PSEUDO_ECHEC);
			}
			businessException.ajouterErreur(CodesResultatDAL.ERREUR_MODIFICATION_UTILISATEUR);
			throw businessException;
		}
	}


	@Override
	public void deleteUser(Utilisateurs utilisateur, String motDePasse) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection();) 
		{
			PreparedStatement pstmt = cnx.prepareStatement(deleteUser);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, motDePasse);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.ERREUR_DELETE_USER);
			throw businessException;
		}
		
	}




}