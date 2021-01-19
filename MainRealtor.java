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
import com.realtor.model.Realtor;
import com.realtor.service.LoginService;

/**
 * Servlet implementation class MainRealtor
 */
@WebServlet("/MainRealtor")
public class MainRealtor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	LoginService ls;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainRealtor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("id");
		Realtor rl = ls.getRealtor(userId);
		request.setAttribute("realtor_info", rl);
		RequestDispatcher view = request
				.getRequestDispatcher("WEB-INF/views/MainRealtor.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
