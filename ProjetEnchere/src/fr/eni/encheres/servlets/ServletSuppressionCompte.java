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
import fr.eni.encheres.bll.ArticlesVendusManager;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.ArticlesVendus;
import fr.eni.encheres.bo.Utilisateurs;

/**
 * Servlet implementation class ServletSuppressionCompte
 */
@WebServlet("/utilisateur/suppressionCompte")
public class ServletSuppressionCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSuppressionCompte() {
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
			rd = request.getRequestDispatcher("/WEB-INF/utilisateur/supprimerProfil.jsp");
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
		String motDePasse = request.getParameter("mdp");
		Utilisateurs utilisateur = (Utilisateurs) session.getAttribute("utilisateur");
		List<ArticlesVendus> listeArticles = null;
		int countArticleVente = 0;
		
		
		if(utilisateur == null)
		{
			listeCodesErreur.add(CodesResultatServlets.PARAM_PSEUDO_NULL);
			request.setAttribute("listeCodesErreur", listeCodesErreur);
		}
		else
		{
			UtilisateursManager utilisateurManager = new UtilisateursManager();
			ArticlesVendusManager articlesVendusManager = new ArticlesVendusManager();
			try {
				listeArticles =  articlesVendusManager.selectionnerParNumeroUtilisateur(utilisateur.getNumUtilisateur());
			} catch (BusinessException e1) {
				e1.printStackTrace();
				request.setAttribute("listeCodesErreur", e1.getListeCodesErreur());
			}

			for(ArticlesVendus article : listeArticles)
			{
				if(article.getEtatVente().equals("EC") || article.getEtatVente().equals("NC"))
				{
					countArticleVente +=1;
				}
			}
			if(countArticleVente == 0)
			{
			
				try {
					utilisateurManager.supprimerProfil(utilisateur, motDePasse);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BusinessException e) {
					e.printStackTrace();
					request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				}
			}
			else
			{
				listeCodesErreur.add(CodesResultatServlets.ERREUR_ARTICLE_EN_VENTE);
				request.setAttribute("listeCodesErreur", listeCodesErreur);
			}
			
		
		if(request.getAttribute("listeCodesErreur") == null)
		{

			session.setAttribute("utilisateur", null);
			
			response.sendRedirect(request.getContextPath()+"/accueil");
			
		}
		else
		{
			doGet(request, response);
		}
		
		
		
		
		
		}
		
			
	}

}
