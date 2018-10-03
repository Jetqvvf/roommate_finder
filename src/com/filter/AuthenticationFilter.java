package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

	private ServletContext context;
	
    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
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
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        
        if (uri.toString().contains("/LoginControl") || uri.toString().contains("sign_up.jsp")
        	|| uri.toString().contains(".js") || uri.toString().contains(".css") || uri.toString().contains("homepage.jsp")) {
        	if (!uri.toString().contains("user_management.jsp")) {
        		chain.doFilter(request, response);
            	return;
        	}
        }

        HttpSession session = req.getSession(false);
        Cookie[] cookies = req.getCookies();
        String username = null;
        String password = null;
        
        
        if (null != cookies) {
        	for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                	username = cookie.getValue();
                }
                if ("password".equals(cookie.getName())) {
                    password = cookie.getValue();
                }
            }
        }
        
        //added by Qiao Wang 2018/09/30
        if (session != null) {
        	if (uri.toString().contains("user_management.jsp")) {
        		int a = Integer.parseInt(session.getAttribute("priority").toString());

        		if (a == 0) {
        			this.context.log("Unauthorized access request");
                    res.sendRedirect(req.getContextPath() + "/profile.jsp");
                    return;
        		}
        	}
        }
        
        if (session != null && username != null && password != null) {   //checking whether the session exists
            if (username.equals(session.getAttribute("username")) && password.equals(session.getAttribute("password"))) {
            	if(uri.toString().contains("sign_in.jsp")) {
            		res.sendRedirect(req.getContextPath() + "/profile.jsp");
            		return;
            	}
            	chain.doFilter(request, response);
            } else {
            	if(uri.toString().contains("sign_in.jsp")) {
            		chain.doFilter(request, response);
            		return;
            	}
            	this.context.log("Unauthorized access request");
                res.sendRedirect(req.getContextPath() + "/homepage.jsp");
            }
        } else {
            // pass the request along the filter chain
        	this.context.log("Unauthorized access request");
            res.sendRedirect(req.getContextPath() + "/homepage.jsp");
        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
	}
}
