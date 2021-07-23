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
import fr.eni.encheres.bll.CategoriesManager;
import fr.eni.encheres.bo.Categories;
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
