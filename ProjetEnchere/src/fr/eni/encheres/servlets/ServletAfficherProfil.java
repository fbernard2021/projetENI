package fr.eni.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.Utilisateurs;

/**
 * Servlet implementation class ServletAfficherProfil
 */
@WebServlet("/profil")
public class ServletAfficherProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAfficherProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramPseudo;
		Utilisateurs utilisateur = null;
		List<Integer> listeCodesErreur=new ArrayList<>();
		RequestDispatcher rd = null;
		paramPseudo = request.getParameter("pseudo");
		
		if(paramPseudo == null)
		{
			listeCodesErreur.add(CodesResultatServlets.PARAM_PSEUDO_NULL);
			request.setAttribute("listeCodesErreur", listeCodesErreur);
		}
		else
		{
			UtilisateursManager utilisateurManager = new UtilisateursManager();
			try {
				utilisateur = utilisateurManager.selectionnerUtilisateur(paramPseudo);
				request.setAttribute("profilAffiche", utilisateur);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			}
		}
		
		if(request.getAttribute("listeCodesErreur") != null || listeCodesErreur.size() > 0)
		{
			rd = request.getRequestDispatcher("/accueil");
			rd.forward(request, response);
		}
		else
		{
			rd = request.getRequestDispatcher("/WEB-INF/profil.jsp");
			rd.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
