package fr.eni.encheres.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import fr.eni.encheres.bll.RetraitsManager;
import fr.eni.encheres.bo.ArticlesVendus;
import fr.eni.encheres.bo.Categories;
import fr.eni.encheres.bo.Retraits;
import fr.eni.encheres.bo.Utilisateurs;

/**
 * Servlet implementation class ServletVendreArticle
 */
@WebServlet("/utilisateur/vendreArticle")
public class ServletVendreArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletVendreArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusinessException exception = new BusinessException();
		List<Categories> listeCategories = new ArrayList<>();
		List<Integer> listeCodesErreur = new ArrayList<>();
		RequestDispatcher rd = null;

		
		CategoriesManager categoriesManager = new CategoriesManager();
		
		try {
			listeCategories = categoriesManager.selectionnerCategories();
			request.setAttribute("listeCategories", listeCategories);
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
		}
		
		if(request.getAttribute("listeCodesErreur") != null)
		{
			rd = request.getRequestDispatcher("/accueil");
			rd.forward(request, response);
		}
		else
		{
			rd = request.getRequestDispatcher("/WEB-INF/utilisateur/vendreArticle.jsp");
			rd.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = null;
		BusinessException exception = new BusinessException();
		List<Integer> listeCodesErreur = new ArrayList<>();
		CategoriesManager categoriesManager = new CategoriesManager();
		ArticlesVendusManager articleVenduManager = new ArticlesVendusManager();
		RetraitsManager retraitManager = new RetraitsManager();
		HttpSession session = request.getSession();
		
	
		String article = request.getParameter("article");
		String description = request.getParameter("description");
		String categorie = request.getParameter("categories");
		int prix =Integer.parseInt(request.getParameter("prix"));
		String rue = request.getParameter("rue");
		int codePostal = Integer.parseInt(request.getParameter("postal"));
		String ville = request.getParameter("ville");
		
		
		ArticlesVendus articleVendu = null;
		
		Retraits retrait = new Retraits(rue, codePostal, ville);
		
		String debutEnchereStr = request.getParameter("dateDebut");
		String finEnchereStr = request.getParameter("dateFin");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date debutEnchere = null;
		Date finEnchere = null;
		try {
			debutEnchere = sdf.parse(debutEnchereStr);
			finEnchere = sdf.parse(finEnchereStr);
		} catch (ParseException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.ERREUR_FORMAT_DATE);
			request.setAttribute("listeCodesErreur", listeCodesErreur);
		}
		
		try {
			int numCategorie = categoriesManager.selectionnerNumeroCategorie(categorie);
			Utilisateurs utilisateur = (Utilisateurs) session.getAttribute("utilisateur");
			articleVendu = articleVenduManager.ajouterArticle(article, description, debutEnchere, finEnchere, prix, utilisateur.getNumUtilisateur() , numCategorie);
			retrait.setNumArticle(articleVendu.getNumArticle());
			retraitManager.ajouterRetrait(retrait);
			
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
		}
		
		if(request.getAttribute("listeCodesErreur") != null)
		{
			rd = request.getRequestDispatcher("/accueil");
			rd.forward(request, response);
		}
		else
		{
			response.sendRedirect(request.getContextPath()+"/utilisateur/afficherArticle?id="+articleVendu.getNumArticle());
		}
		
		
	}

}
