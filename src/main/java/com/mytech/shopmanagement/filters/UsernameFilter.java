package com.mytech.shopmanagement.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.mytech.shopmanagement.helpers.ServletHelper;

/**
 * Servlet Filter implementation class UsernameFilter
 */
@WebFilter(urlPatterns = { "/users",
		"/login" }, initParams = @WebInitParam(name = "notAllowedNames", value = "google, facebook,zalo"))
public class UsernameFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 0;
	String[] names;
	
	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public UsernameFilter() {
		super();
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
	

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String username = request.getParameter("username");
		if (checkUsernameNotAllowed(username)) {
			httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
			httpRequest.setAttribute("message", "username is not allowed");
			ServletHelper.forward(httpRequest, httpResponse, "login");
		} else {
			chain.doFilter(request, response);
		}

		chain.doFilter(request, response);
	}
	// CHECK USERNAME NOT ALLOWED
			private boolean checkUsernameNotAllowed(String username) {
				for (String name : names) {
					if (name.equals(username)) {
						return true;
					}
				}
				return false;
			}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		String notAllowedNames= fConfig.getInitParameter("notAllowedNames");
		names= notAllowedNames.split(",");
		System.out.println("Name are not allowed: "+ names);
	}

}
