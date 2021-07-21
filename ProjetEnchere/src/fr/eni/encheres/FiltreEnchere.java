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
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FiltreEnchere
 */
@WebFilter(urlPatterns ="/enchere/*",
dispatcherTypes = {
		   				DispatcherType.REQUEST,
		   				DispatcherType.INCLUDE,
		   				DispatcherType.FORWARD,
		   				DispatcherType.ERROR
					  }
)
public class FiltreEnchere implements Filter {

    /**
     * Default constructor. 
     */
    public FiltreEnchere() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		if(session.getAttribute("connexion") != "true")
		{
			RequestDispatcher rd = request.getRequestDispatcher("/connexion");
			rd.forward(httpRequest, response);
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
