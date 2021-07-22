package fr.eni.encheres.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.Utilisateurs;
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
        RequestDispatcher rd = null;
		List<Integer> listeCodesErreur=new ArrayList<>();
        Utilisateurs utilisateur = null;
        

        
        UtilisateursManager utilisateursManager = new UtilisateursManager();

        	// vérifier les informations de connexion

			try {
				utilisateur = utilisateursManager.connexion(identifiant, mdp);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			}
			
			
            if(utilisateur == null)
            {
            	// envoyer un message d'erreur
            	listeCodesErreur.add(CodesResultatServlets.ERROR_CONNEXION);
                request.setAttribute("listeCodesErreur", listeCodesErreur);
                
            }
			
			
			
			if(request.getAttribute("listeCodesErreur") != null)
			{
            	rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
            	rd.forward(request, response);
			}


            else if (utilisateur.getPseudo().compareTo(identifiant) == 0 ||  utilisateur.getEmail().compareTo(identifiant) == 0 )
              {
            	HttpSession session = request.getSession();
                session.setAttribute("utilisateur", utilisateur);
                session.setAttribute("connexion", "true");
                
                // rediriger vers l'accueil
                response.sendRedirect(request.getContextPath()+"/accueil");
             }

		}
	
	}


