package com.realtor.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.realtor.model.Users;
import com.realtor.service.ClientService;
import com.realtor.service.LoginService;

/**
 * Servlet implementation class DeleteClient
 */
@WebServlet("/DeleteClient")
public class DeleteClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	@EJB
	ClientService cs;
	
	@EJB
	LoginService ls;
	
    public DeleteClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String clientId = request.getParameter("id");
		String userId = request.getParameter("uid");
		Users usInfo = ls.getUser(userId);
		cs.updateDeleteClient(clientId);
		request.setAttribute("user_info", usInfo);
		RequestDispatcher view = request
				.getRequestDispatcher("WEB-INF/views/DeleteClientSuccess.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
