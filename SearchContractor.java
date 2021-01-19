package com.realtor.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.realtor.model.Client;
import com.realtor.model.Contractor;
import com.realtor.model.Users;
import com.realtor.service.ClientService;
import com.realtor.service.ContractorService;
import com.realtor.service.LoginService;

/**
 * Servlet implementation class SearchContractor
 */
@WebServlet("/SearchContractor")
public class SearchContractor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	@EJB
	ContractorService cs;
	
	@EJB
	LoginService ls;
	
	String userGlob = "";
	
    public SearchContractor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("id");
		userGlob = userId;
		Users userInf = ls.getUser(userId);
		request.setAttribute("user_info", userInf);
		RequestDispatcher view = request
				.getRequestDispatcher("WEB-INF/views/SearchContractor.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Contractor ctInfo = null;
		Users userInfo = ls.getUser(userGlob);
		String contractorId = request.getParameter("contractor_id");
		
		ctInfo = cs.getContractor(contractorId);
		
		
		if(ctInfo != null){
			request.setAttribute("user_info", userInfo);
			request.setAttribute("contractor_info", ctInfo);
			RequestDispatcher view = request
					.getRequestDispatcher("WEB-INF/views/DeleteContractor.jsp");
			view.forward(request, response);
		}else{
			request.setAttribute("user_info", userInfo);
			RequestDispatcher view = request
					.getRequestDispatcher("WEB-INF/views/NoExistContractor.jsp");
			view.forward(request, response);
		}
	}

}
