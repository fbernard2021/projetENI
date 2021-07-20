package fr.eni.encheres.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.Utilisateurs;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletConnexionUtilisateur
 */
@WebServlet("/connexion")
public class ServletConnexionUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String identifiant = request.getParameter("pseudo");
        String mdp = request.getParameter("mdp");
        Utilisateurs utilisateur;
        
        
        UtilisateursManager utilisateursManager = new UtilisateursManager();
        try
        {
        	// vérifier les informations de connexion
        	utilisateur = utilisateursManager.connexion(identifiant, mdp);
       //     if (utilisateur.getPseudo() == identifiant || utilisateur.getEmail() == identifiant)
       //     {
            	System.out.println(utilisateur.getPseudo());
            	HttpSession session = request.getSession();
                session.setAttribute("utilisateur", utilisateur);
                
                // rediriger vers l'accueil
                RequestDispatcher rdA = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
        		rdA.forward(request, response);
           // }
          //  else
          //  {
            	// envoyer un message d'erreur
            	String message = "Nom d'utilisateur et/ou mot de passe incorrect(s)";
                request.setAttribute("message", message);
    		//}
		}
        catch (NoSuchAlgorithmException | BusinessException e)
        {
        	throw new ServletException(e);
		}
	}

}
