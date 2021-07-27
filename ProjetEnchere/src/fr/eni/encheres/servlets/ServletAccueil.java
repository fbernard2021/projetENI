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
import fr.eni.encheres.bll.ArticlesVendusManager;
import fr.eni.encheres.bll.CategoriesManager;
import fr.eni.encheres.bll.CodesResultatBLL;

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
		ArticlesVendusManager articles = new ArticlesVendusManager();
		CategoriesManager categories = new CategoriesManager();
		
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
			request.setAttribute("articles", articles.selectionnerListeArticlesAccueil());
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
		ArticlesVendusManager articles = new ArticlesVendusManager();
		CategoriesManager categories = new CategoriesManager();
		
		// Si aucune catégorie n'est sélectionnée
		if(request.getParameter("nomCategorie") == "toutesCategories")
		{
			// JUSTE RECHERCHE
			if(request.getParameter("recherche") != null)
			{
				try
				{
					String recherche = request.getParameter("recherche");
					request.setAttribute("articles", articles.rechercherArticlesSansCategorie(recherche));
				}
				catch (BusinessException e)
				{
					listeCodesErreur.add(CodesResultatBLL.LISTE_ARTICLES_RECHERCHE_SIMPLE_NULL);
		            request.setAttribute("listeCodesErreur", listeCodesErreur);
				}
			}
			// NI RECHERCHE NI CATEGORIE
			else
			{
				try
				{
					request.setAttribute("articles", articles.selectionnerListeArticlesAccueil());
				}
				catch (BusinessException e)
				{
					listeCodesErreur.add(CodesResultatBLL.LISTE_ARTICLES_ACCUEIL_NULL);
		            request.setAttribute("listeCodesErreur", listeCodesErreur);
				}
			}
		}
		
		// Si une catégorie est sélectionnée
		else
		{
			// JUSTE CATEGORIE
			if(request.getParameter("recherche") == null)
			{
				try
				{
					String nomCategorie = request.getParameter("nomCategorie");
					request.setAttribute("articles", articles.selectionnerListeArticlesParCategorie(nomCategorie));
				}
				catch (BusinessException e)
				{
					listeCodesErreur.add(CodesResultatBLL.LISTE_ARTICLES_CATEGORIE_NULL);
		            request.setAttribute("listeCodesErreur", listeCodesErreur);
				}
			}
			// RECHERCHE ET CATEGORIE
			else
			{
				try
				{
					String recherche = request.getParameter("recherche");
					String nomCategorie = request.getParameter("nomCategorie");
					request.setAttribute("articles", articles.rechercherArticlesAvecCategorie(recherche, nomCategorie));
				}
				catch (BusinessException e)
				{
					listeCodesErreur.add(CodesResultatBLL.LISTE_ARTICLES_RECHERCHE_AVEC_CATEGORIE_NULL);
			        request.setAttribute("listeCodesErreur", listeCodesErreur);
				}
			}
		}

		// on remet les catégorie dans le select
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
