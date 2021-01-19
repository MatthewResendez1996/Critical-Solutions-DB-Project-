package com.realtor.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.realtor.model.Client;
import com.realtor.service.LoginService;

/**
 * Servlet implementation class loginUser
 */
@WebServlet("/LoginUser")
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	LoginService ls;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request
				.getRequestDispatcher("WEB-INF/views/Login.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("user_id");
		String pwd = request.getParameter("password");
		String userType = request.getParameter("user_type");

		int userPage = ls.initSession(userId, pwd, userType);
		HttpSession session = request.getSession();

		if (userPage == 1) {
			
			response.sendRedirect("MainContractor?id="+userId);

		} else if (userPage == 2) {
			
			response.sendRedirect("MainClient?id="+userId);

		} else if (userPage == 3) {

			response.sendRedirect("MainRealtor?id="+userId);

		} else if (userPage == 4) {
			response.sendRedirect("AdminPage?id="+userId);
		} else if (userPage == 5) {
			request.setAttribute("errorMessage",
					"Selected User Type does not match.");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("WEB-INF/views/InvalidUserTypeLogin.jsp");
			dispatcher.forward(request, response);

		} else {

			request.setAttribute("errorMessage", "Invalid username or password");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("WEB-INF/views/ErrorLogin.jsp");
			dispatcher.forward(request, response);
		}

	}

}
