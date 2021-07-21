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
		System.out.println(utilisateur.getEmail());
		
		if(utilisateur == null)
		{
			listeCodesErreur.add(CodesResultatServlets.PARAM_PSEUDO_NULL);
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			rd = request.getRequestDispatcher("/accueil");
			rd.forward(request, response);
		}
		else
		{
			rd = request.getRequestDispatcher("/WEB-INF/utilisateur/modifierprofil.jsp");
			rd.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		List<Integer> listeCodesErreur=new ArrayList<>();
		HttpSession session = request.getSession();
		Utilisateurs utilisateurSession = (Utilisateurs)  session.getAttribute("utilisateur");
		Utilisateurs utilisateur = new Utilisateurs();
		utilisateur.clone(utilisateurSession);
		String motDePasseActuel = request.getParameter("mdpAct");
		String motDePasse = request.getParameter("newMdp");
		String confirmation = request.getParameter("mdpConfirm");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		Integer codePostal = null;
		String ville = request.getParameter("ville");
		
		if(motDePasse.compareTo(confirmation) != 0)
		{
			listeCodesErreur.add(CodesResultatServlets.ERROR_MDP_CONFIRM);
			request.setAttribute("listeCodesErreur", listeCodesErreur);
		}
		else
		{
		
			if(request.getParameter("codePostal") != null)
			{
				codePostal = Integer.parseInt(request.getParameter("codePostal"));
			}
		
		
			if(utilisateur == null)
			{
				listeCodesErreur.add(CodesResultatServlets.PARAM_PSEUDO_NULL);
				request.setAttribute("listeCodesErreur", listeCodesErreur);
			}
			else
			{
				if(utilisateur.getNom().compareTo(nom) != 0)
				{
					utilisateur.setNom(nom);
				}
				if(utilisateur.getPrenom().compareTo(prenom) != 0)
				{
					utilisateur.setPrenom(prenom);
				}
				if(utilisateur.getEmail().compareTo(email) != 0)
				{
					utilisateur.setEmail(email);
				}
				if(utilisateur.getTelephone().compareTo(telephone) != 0)
				{
					utilisateur.setTelephone(telephone);
				}
				if(utilisateur.getRue().compareTo(rue) != 0)
				{
					utilisateur.setRue(rue);
				}
				if((Integer) utilisateur.getCodePostal() != codePostal && codePostal != null)
				{
					utilisateur.setCodePostal(codePostal);
				}
				if(utilisateur.getVille().compareTo(ville) != 0)
				{
					utilisateur.setVille(ville);
				}
			}

		
			UtilisateursManager utilisateurManager = new UtilisateursManager();
		
		
				try {
					utilisateurManager.modifierProfil(utilisateur,motDePasseActuel, motDePasse);
					
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BusinessException e) {
					e.printStackTrace();
					request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				}

		}
			
			if((List<Integer>)request.getAttribute("listeCodesErreur") == null)
			{
				System.out.println("oui");
				session.setAttribute("utilisateur", utilisateur);
			
				if(motDePasse.compareTo("") != 0)
				{
					request.setAttribute("mdpOk", "true");
				}
			}
		
			
		doGet(request, response);
		
	}

}
