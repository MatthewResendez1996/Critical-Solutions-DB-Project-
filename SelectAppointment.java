package com.realtor.controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.realtor.model.Client;
import com.realtor.model.Listing;
import com.realtor.model.Realtor;
import com.realtor.model.Users;
import com.realtor.model.ClientAppointment;
import com.realtor.service.AppointmentService;
import com.realtor.service.RealtorService;

/**
 * Servlet implementation class SelectAppointment
 */
@WebServlet("/SelectAppointment")
public class SelectAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	AppointmentService as;
	@EJB
	RealtorService rs;
	
	String rlId = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectAppointment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String realtorId = request.getParameter("id");
		rlId = realtorId;
		Realtor rl = rs.getRealtor(realtorId);
		Users us = rs.getUser(rl);
		List<ClientAppointment> caInfo = as.getAppointment();
		List<Listing> listing_list = as.getListingList(caInfo);
		List<Client> client_list = as.getClientList(caInfo);
		request.setAttribute("realtor_info", rl);
		request.setAttribute("user_info", us);
		request.setAttribute("appt_info", caInfo);
		request.setAttribute("listing_info", listing_list);
		request.setAttribute("client_info", client_list);
		RequestDispatcher view = request
				.getRequestDispatcher("WEB-INF/views/RealtorAppointment.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
