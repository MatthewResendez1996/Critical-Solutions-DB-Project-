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
import com.realtor.model.Contractor;
import com.realtor.service.LoginService;

/**
 * Servlet implementation class MainContractor
 */
@WebServlet("/MainContractor")
public class MainContractor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	LoginService ls;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainContractor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("id");
		Contractor  col = ls.getContractor(userId);
		request.setAttribute("contractor_info", col);
		RequestDispatcher view = request
				.getRequestDispatcher("WEB-INF/views/MainContractor.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
