package fr.eni.encheres.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.Utilisateurs;

/**
 * Servlet implementation class ServletModifierProfil
 */
@WebServlet("/utilisateur/modifierProfil")
public class ServletModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModifierProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		List<Integer> listeCodesErreur=new ArrayList<>();
		HttpSession session = request.getSession();
		Utilisateurs utilisateur = (Utilisateurs) session.getAttribute("utilisateur");
		
		if(utilisateur == null)
		{
			listeCodesErreur.add(CodesResultatServlets.PARAM_PSEUDO_NULL);
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			rd = request.getRequestDispatcher("/accueil");
			rd.forward(request, response);
		}
		else
		{
			rd = request.getRequestDispatcher("/WEB-INF/utilisateur/modifierProfil.jsp");
			rd.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		List<Integer> listeCodesErreur=new ArrayList<>();
		HttpSession session = request.getSession();
		Utilisateurs utilisateur = (Utilisateurs) session.getAttribute("utilisateur");
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		int codePostal = Integer.parseInt(request.getParameter("codePostal"));
		String ville = request.getParameter("ville");
		String motDePasse = request.getParameter("mdp");
		
		if(utilisateur == null)
		{
			listeCodesErreur.add(CodesResultatServlets.PARAM_PSEUDO_NULL);
			request.setAttribute("listeCodesErreur", listeCodesErreur);
		}
		else
		{
			if(utilisateur.getNom().compareTo(nom) != 0 && nom != null)
			{
				utilisateur.setNom(nom);
			}
			if(utilisateur.getPrenom().compareTo(prenom) != 0 && prenom != null)
			{
				utilisateur.setPrenom(prenom);
			}
			if(utilisateur.getEmail().compareTo(email) != 0 && email != null)
			{
				utilisateur.setEmail(email);
			}
			if(utilisateur.getTelephone().compareTo(telephone) != 0 && telephone != null)
			{
				utilisateur.setTelephone(telephone);
			}
			if(utilisateur.getRue().compareTo(rue) != 0 && rue != null)
			{
				utilisateur.setRue(rue);
			}
			if(utilisateur.getCodePostal() != codePostal && (Integer) codePostal != null)
			{
				utilisateur.setCodePostal(codePostal);
			}
			if(utilisateur.getVille().compareTo(ville) != 0 && ville != null)
			{
				utilisateur.setVille(ville);
			}
		}
		
		UtilisateursManager utilisateurManager = new UtilisateursManager();
		
		
			try {
				utilisateurManager.modifierProfil(utilisateur, motDePasse);
				session.setAttribute("utilisateur", utilisateur);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			}


			rd = request.getRequestDispatcher("/WEB-INF/utilisateur/modifierProfil.jsp");
			rd.forward(request, response);
			
		
		
	}

}
