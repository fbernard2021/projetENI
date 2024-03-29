package fr.eni.encheres;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FiltreEnchere
 */
@WebFilter(urlPatterns ={"/enchere/*", "/utilisateur/*", "/deconnexion"},
dispatcherTypes = {
		   				DispatcherType.REQUEST,
		   				DispatcherType.INCLUDE,
		   				DispatcherType.FORWARD,
		   				DispatcherType.ERROR
					  }
)
public class FiltreVerificationConnexion implements Filter {



	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		if(session.getAttribute("utilisateur") == null)
		{
			httpResponse.sendRedirect(httpRequest.getContextPath()+"/connexion");
		}
		else
		{
		chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
