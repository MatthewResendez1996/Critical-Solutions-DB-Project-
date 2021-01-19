package com.realtor.controller;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.realtor.model.ClientAppointment;
import com.realtor.model.Users;
import com.realtor.service.AppointmentService;

/**
 * Servlet implementation class AddAppointment
 */
@WebServlet("/AddAppointment")
public class AddAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	AppointmentService as;
	
	String cltId = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAppointment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String clientId = request.getParameter("id");
		cltId = clientId;
		Client cl = as.getClient(clientId);
		Users us = as.getUser(cl);
		request.setAttribute("client_info", cl);
		request.setAttribute("user_info", us);
		RequestDispatcher view = request
				.getRequestDispatcher("WEB-INF/views/ClientAppointment.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Client  clObj = as.getClient(cltId);
		Integer uId = as.getUserId(clObj);
		String uIdToSent = String.valueOf(uId);
		request.setAttribute("client_info", clObj);
		ClientAppointment ca = new ClientAppointment();
		
		String listingId = request.getParameter("listing_id");
		String apptDate_raw = request.getParameter("appt_date");
		String apptTime = request.getParameter("appt_time");
		String apptMessage = request.getParameter("appt_message");
		
		String[] apptDateArr = apptDate_raw.split("\\-");

		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, Integer.parseInt(apptDateArr[0]));
		cal.set(Calendar.MONTH, Integer.parseInt(apptDateArr[1]) - 1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(apptDateArr[2]));

		Date apptDate = cal.getTime();
		
		ca.setApptDate(apptDate);
		ca.setClientId(Integer.parseInt(cltId));
		ca.setListingId(Integer.parseInt(listingId));
		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date apptTimeDate = null;
		Time appTimeSave = null;
		try {
			apptTimeDate = (Date) format.parse(apptTime);
			appTimeSave = new Time(apptTimeDate.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ca.setApptTime(appTimeSave);
		ca.setApptMessage(apptMessage);
		as.addAppointment(ca);
		
		response.sendRedirect("MainClient?id="+uIdToSent);
		
	}

}
