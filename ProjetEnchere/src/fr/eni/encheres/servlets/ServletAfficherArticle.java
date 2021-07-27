package fr.eni.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import fr.eni.encheres.bll.CategoriesManager;
import fr.eni.encheres.bll.EncheresManager;
import fr.eni.encheres.bll.RetraitsManager;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.ArticlesVendus;
import fr.eni.encheres.bo.Encheres;
import fr.eni.encheres.bo.Retraits;
import fr.eni.encheres.bo.Utilisateurs;



/**
 * Servlet implementation class ServletAfficherArticle
 */
@WebServlet("/utilisateur/afficherArticle")
public class ServletAfficherArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAfficherArticle() {
        super();
        // selectionnerunArticle();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int paramId;
		List<Integer> listeCodesErreur=new ArrayList<>();
		RequestDispatcher rd = null;
		paramId = Integer.parseInt(request.getParameter("id"));
		ArticlesVendus articleVendu = null;
		String nomCategorie;
		String pseudoMeilleureOffre = null;
		Retraits retrait = null;
		Encheres enchere = null;
		
		if((Integer) paramId == null)
		{
			listeCodesErreur.add(CodesResultatServlets.PARAM_ARTICLE_NULL);
			request.setAttribute("listeCodesErreur", listeCodesErreur);
		}
		else
		{
			ArticlesVendusManager articleVenduManager = new ArticlesVendusManager();
			CategoriesManager categorieManager = new CategoriesManager();
			RetraitsManager retraitManager = new RetraitsManager();
			EncheresManager enchereManager = new EncheresManager();
			UtilisateursManager utilisateurManager = new UtilisateursManager();
			
			try {
				articleVendu = articleVenduManager.selectionnerunArticle(paramId);
				nomCategorie = categorieManager.selectionnerNomCategorie(articleVendu.getNumCategorie());
				retrait = retraitManager.selectionnerRetrait(paramId);
				enchere = enchereManager.selectionnerDerniereEnchere(articleVendu);
				
				if(enchere != null)
				{
					pseudoMeilleureOffre = utilisateurManager.selectionnerPseudo(enchere.getNumUtilisateur());
				}
				
				
				request.setAttribute("articleVendu", articleVendu);
				request.setAttribute("nomCategorie", nomCategorie);
				request.setAttribute("retrait", retrait);
				request.setAttribute("enchere", enchere);
				request.setAttribute("pseudoMeilleureOffre", pseudoMeilleureOffre);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			}
		}


			rd = request.getRequestDispatcher("/WEB-INF/utilisateur/afficherArticle.jsp");
			rd.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		Utilisateurs utilisateurTemp = (Utilisateurs) session.getAttribute("utilisateur");
		Utilisateurs utilisateur = new Utilisateurs();
		utilisateur.clone(utilisateurTemp);
		int offre = Integer.parseInt(request.getParameter("offre"));
		int articleId = Integer.parseInt(request.getParameter("id"));
		int meilleureOffre;
		String pseudoMeilleureOffre = null;
		Utilisateurs utilisateurMeilleureOffre = null;
		Encheres nouvelEnchere = null;
		Date date = new Date();
		
		UtilisateursManager utilisateurManager = new UtilisateursManager();
		EncheresManager enchereManager = new EncheresManager();
		
		if(request.getParameter("meilleureOffre") != null)
		{
			meilleureOffre = Integer.parseInt(request.getParameter("meilleureOffre"));
			pseudoMeilleureOffre = request.getParameter("pseudoMeilleureOffre");
			try {
				utilisateurMeilleureOffre = utilisateurManager.selectionnerUtilisateur(pseudoMeilleureOffre);
				utilisateurMeilleureOffre.setCredit(utilisateurMeilleureOffre.getCredit()+ meilleureOffre);
				utilisateurManager.modifierCredit(utilisateurMeilleureOffre);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			}
			
		}
		
		utilisateur.setCredit(utilisateur.getCredit()-offre);
		try {
			utilisateurManager.modifierCredit(utilisateur);
			nouvelEnchere = new Encheres(utilisateur.getNumUtilisateur(),articleId, date, offre);
			enchereManager.insererEnchere(nouvelEnchere);
			
			
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
		}
		
		doGet(request, response);
	}

}
