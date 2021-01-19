package com.realtor.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.realtor.model.Client;
import com.realtor.service.LoginService;

/**
 * Servlet implementation class MainClient
 */
@WebServlet("/MainClient")
public class MainClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	LoginService ls;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("id");
		Client  cl = ls.getClient(userId);
		request.setAttribute("client_info", cl);
		RequestDispatcher view = request
				.getRequestDispatcher("WEB-INF/views/MainClient.jsp");
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

}
