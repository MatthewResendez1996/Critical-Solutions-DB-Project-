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
import com.realtor.model.Realtor;
import com.realtor.model.Users;
import com.realtor.service.ClientService;
import com.realtor.service.LoginService;
import com.realtor.service.RealtorService;

/**
 * Servlet implementation class SearchRealtor
 */
@WebServlet("/SearchRealtor")
public class SearchRealtor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	@EJB
	RealtorService rs;
	
	@EJB
	LoginService ls;
	
	String userGlob = "";
	
    public SearchRealtor() {
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
				.getRequestDispatcher("WEB-INF/views/SearchRealtor.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Realtor rlInfo = null;
		Users userInfo = ls.getUser(userGlob);
		String realtorId = request.getParameter("realtor_id");
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String dateBirth_raw = request.getParameter("dob");
		Date dob = null;
		if(!dateBirth_raw.equals("")){
			String[] dobArr = dateBirth_raw.split("\\-");

			Calendar cal = Calendar.getInstance();

			cal.set(Calendar.YEAR, Integer.parseInt(dobArr[0]));
			cal.set(Calendar.MONTH, Integer.parseInt(dobArr[1]) - 1);
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dobArr[2]));

			dob = cal.getTime();
		}
		
		
		if(!realtorId.equals("")){
			rlInfo = rs.getRealtor(realtorId);
		}else{
			rlInfo = rs.getRealtorByNameDob(firstName, lastName, dob);
		}
		
		if(rlInfo != null){
			request.setAttribute("user_info", userInfo);
			request.setAttribute("realtor_info", rlInfo);
			RequestDispatcher view = request
					.getRequestDispatcher("WEB-INF/views/DeleteRealtor.jsp");
			view.forward(request, response);
		}else{
			request.setAttribute("user_info", userInfo);
			RequestDispatcher view = request
					.getRequestDispatcher("WEB-INF/views/NoExistRealtor.jsp");
			view.forward(request, response);
		}
	}

}
