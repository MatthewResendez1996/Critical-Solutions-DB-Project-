package com.realtor.controller;

import java.io.IOException;
import java.sql.Clob;
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
import com.realtor.model.Gender;
import com.realtor.model.Users;
import com.realtor.service.ClientService;
import com.realtor.service.LoginService;

/**
 * Servlet implementation class UpdateClient
 */
@WebServlet("/UpdateClient")
public class UpdateClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	ClientService cs;
	String cltId = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String clientId = request.getParameter("id");
		cltId = clientId;
		Client cl = cs.getClient(clientId);
		Users us = cs.getUser(cl);
		request.setAttribute("client_info", cl);
		request.setAttribute("user_info", us);
		RequestDispatcher view = request
				.getRequestDispatcher("WEB-INF/views/UpdateClient.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Client  clObj = cs.getClient(cltId);
		
		Client cl = new Client();
		
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String gender = request.getParameter("gender");
		String dob_raw = request.getParameter("dob");
		String clientType = request.getParameter("client_type");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state_name");
		String zipCode = request.getParameter("zip_code");
		String phoneNumber = request.getParameter("phone_number");
		String email = request.getParameter("email");
		String budget = request.getParameter("budget");

		String[] dobArr = dob_raw.split("\\-");

		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, Integer.parseInt(dobArr[0]));
		cal.set(Calendar.MONTH, Integer.parseInt(dobArr[1]) - 1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dobArr[2]));

		Date dob = cal.getTime();
		
		cl.setClient_id(clObj.getClient_id());
		cl.setFirst_name(firstName);
		cl.setLast_name(lastName);
		cl.setGender(Gender.valueOf(gender));
		cl.setDob(dob);
		if (clientType.equals("New Client")) {
			cl.setClientType_id(1);
		} else {
			cl.setClientType_id(2);
		}
		cl.setHomeAddress(address);
		cl.setCity(city);
		cl.setHomeState(state);
		cl.setZipCode(zipCode);
		cl.setPhoneNumber(phoneNumber);
		cl.setEmail(email);
		cl.setBudget(Float.parseFloat(budget));
		cl.setIs_Delete(0);
		cs.updateClient(cl);
		request.setAttribute("client_info", clObj);
		RequestDispatcher view = request
				.getRequestDispatcher("WEB-INF/views/UpdateClientSuccess.jsp");
		view.forward(request, response);
	}

}
