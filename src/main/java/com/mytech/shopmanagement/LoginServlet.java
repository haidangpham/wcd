package com.mytech.shopmanagement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Login Page initialized");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Login Page destroyed");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username= request.getParameter("username");
		String password= request.getParameter("password");
		
		PrintWriter outPrintWriter= response.getWriter();
		outPrintWriter.append("Logged in with:" + username + "::" + password);
		
		
		//username= admin & password =123456
		if (username.equals("admin") && password.equals("123456")) {
//		    RequestDispatcher requestDispatcher= request.getRequestDispatcher("dashboard.jsp");
//		    requestDispatcher.forward(request, response);
		  //Cookies
		    Cookie ckUsername= new Cookie("username", username);
		    Cookie ckLoginDate= new Cookie("loginDate", System.currentTimeMillis() + "");
		    
		    response.addCookie(ckUsername);
		    response.addCookie(ckLoginDate);
		    
		    
		    //Session
		    HttpSession session= request.getSession();
		    session.setAttribute("username", username);
		    session.setAttribute("loginDate", System.currentTimeMillis() + "");
		    
		    response.sendRedirect("dashboard.jsp");
		} else {
		    RequestDispatcher requestDispatcher= request.getRequestDispatcher("error.jsp");
		    requestDispatcher.include(request, response);
		}
	}

}
