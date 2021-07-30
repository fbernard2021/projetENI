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
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticlesVendusManager;
import fr.eni.encheres.bll.CategoriesManager;
import fr.eni.encheres.bll.CodesResultatBLL;
import fr.eni.encheres.bll.EncheresManager;
import fr.eni.encheres.bo.ArticlesVendus;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/accueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<Integer> listeCodesErreur=new ArrayList<>();
		ArticlesVendusManager articlesVendusManager = new ArticlesVendusManager();
		List <ArticlesVendus> articles = null;
		CategoriesManager categories = new CategoriesManager();
		EncheresManager encheresManager = new EncheresManager();
		
		// on récupère les catégories pour les afficher dans le select
		try
		{
			request.setAttribute("categories", categories.selectionnerCategories());
		}
		catch (BusinessException e)
		{
			listeCodesErreur.add(CodesResultatBLL.LISTE_CATEGORIES_NULL);
            request.setAttribute("listeCodesErreur", listeCodesErreur);
		}
		
		// on récupère tous les articles
		try
		{
			articles = articlesVendusManager.selectionnerListeArticlesAccueil();
			
			// on vérifie pour chaque article si quelqu'un a enchéri
			for (ArticlesVendus article : articles)
			{
				if(encheresManager.selectionnerMeilleurOffre(article.getNumArticle()).getMontantEnchere() != 0)
				{
					// si c'est le cas, on récupère le montant
					int meilleureOffre = encheresManager.selectionnerMeilleurOffre(article.getNumArticle()).getMontantEnchere();
					article.setPrixVente(meilleureOffre);
				}
				else
				{
					// sinon, on assigne le prix initial
					article.setPrixVente(article.getPrixInitial());
				}
			}
			request.setAttribute("articles", articles);
		}
		catch (BusinessException e)
		{
			listeCodesErreur.add(CodesResultatBLL.LISTE_ARTICLES_ACCUEIL_NULL);
            request.setAttribute("listeCodesErreur", listeCodesErreur);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<Integer> listeCodesErreur=new ArrayList<>();
		ArticlesVendusManager articlesVendusManager = new ArticlesVendusManager();
		List <ArticlesVendus> articles = null;
		CategoriesManager categories = new CategoriesManager();
		EncheresManager encheresManager = new EncheresManager();
		
		try
		{
			String recherche = request.getParameter("recherche");
			String nomCategorie = request.getParameter("nomCategorie");
			
			// on récupère les articles correspondant aux critères de recherche
			articles = articlesVendusManager.rechercherArticles(recherche, nomCategorie);
			
			// on vérifie pour chaque article si quelqu'un a enchéri
			for (ArticlesVendus article : articles)
			{
				if(encheresManager.selectionnerMeilleurOffre(article.getNumArticle()).getMontantEnchere() != 0)
				{
					// si c'est le cas, on récupère le montant
					int meilleureOffre = encheresManager.selectionnerMeilleurOffre(article.getNumArticle()).getMontantEnchere();
					article.setPrixVente(meilleureOffre);
				}
				else
				{
					// sinon, on assigne le prix initial
					article.setPrixVente(article.getPrixInitial());
				}
			}
			request.setAttribute("articles", articles);
		}
		catch (BusinessException e)
		{
			listeCodesErreur.add(CodesResultatBLL.LISTE_ARTICLES_RECHERCHE_SIMPLE_NULL);
            request.setAttribute("listeCodesErreur", listeCodesErreur);
		}

		// on remet les catégories dans le select
		try
		{
			request.setAttribute("categories", categories.selectionnerCategories());
		}
		catch (BusinessException e)
		{
			listeCodesErreur.add(CodesResultatBLL.LISTE_CATEGORIES_NULL);
            request.setAttribute("listeCodesErreur", listeCodesErreur);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
	}

}
