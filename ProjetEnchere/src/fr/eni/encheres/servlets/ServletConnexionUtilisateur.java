

import java.io.IOException;
import fr.eni.encheres.dal.UtilisateursDAO;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.Utilisateurs;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletConnexionUtilisateur
 */
@WebServlet("/ServletConnexionUtilisateur")
public class ServletConnexionUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletConnexionUtilisateur() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String identifiant = request.getParameter("identifiant");
        String mdp = request.getParameter("mdp");
        
        
        UtilisateursManager utilisateursManager = new UtilisateursManager();
        try
        {
        	// commentaire ici
        	utilisateursManager.connexion(identifiant, mdp);
            if (utilisateursManager != null)
            {
            	HttpSession session = request.getSession();
                session.setAttribute("utilisateur", utilisateursManager);
            }
            else
            {
            	String message = "Nom d'utilisateur et/ou mot de passe incorrect(s)";
                request.setAttribute("message", message);
    		}
		}
        catch (SQLException | ClassNotFoundException e)
        {
        	throw new ServletException(e);
		}
	}

}
