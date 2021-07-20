package fr.eni.encheres.servlets;

import java.io.IOException;

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
 * Servlet implementation class servletInscription
 */
@WebServlet("/inscription")
public class servletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Inscription.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String pseudo;
		String nom;
		String prenom;
		String email;
		String telephone;
		String rue;
		int codePostal;
		String ville;
		String motDePasse;
		String confirmation;
		int admin;
		int credit = 0;
		
		motDePasse = request.getParameter("mdp");
		confirmation = request.getParameter("mdpConfirm");
		
		if(motDePasse.compareTo(confirmation) != 0)
		{
			RequestDispatcher rd2 = request.getRequestDispatcher("/WEB-INF/Inscription.jsp");
			rd2.forward(request, response);
		}
		
		pseudo = request.getParameter("pseudo");
		nom = request.getParameter("nom");
		prenom = request.getParameter("prenom");
		email = request.getParameter("email");
		telephone = request.getParameter("telephone");
		rue = request.getParameter("rue");
		codePostal =Integer.parseInt(request.getParameter("postal"));
		ville = request.getParameter("ville");
		admin =Integer.parseInt(request.getParameter("adm"));
		

		
		UtilisateursManager utilisateurManager = new UtilisateursManager();
		
		try {
			Utilisateurs utilisateur =  utilisateurManager.ajouter(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit,admin);
			request.setAttribute("utilisateur", utilisateur);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ok");
		RequestDispatcher rd3 = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd3.forward(request, response);
		
		
	}

}
