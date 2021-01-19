package com.realtor.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.realtor.model.Client;
import com.realtor.model.Gender;
import com.realtor.model.UserType;
import com.realtor.model.Users;
import com.realtor.service.ClientService;
import com.realtor.service.LoginService;

/**
 * Servlet implementation class AddClientByAdmin
 */
@WebServlet("/AddClientByAdmin")
public class AddClientByAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ClientService cs;

	@EJB
	LoginService ls;
	
	String userGlob = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddClientByAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("id");
		userGlob = userId;
		Users userInf = ls.getUser(userId);
		request.setAttribute("user_info", userInf);
		RequestDispatcher view = request
				.getRequestDispatcher("WEB-INF/views/AddClient.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Users userInf = ls.getUser(userGlob);
		request.setAttribute("user_info", userInf);
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

		Users us = new Users();
		UserType ut = new UserType();
		ut.setUser_type_id(2);

		us.setPassword(String.valueOf(generatePassword()));
		us.setClient_id(cl);
		us.setContractor_id(null);
		us.setRealtor_id(null);
		us.setUser_type_id(ut);
		cs.addClient(cl);
		ls.addUser(us);
		
		
		
		RequestDispatcher view = request
				.getRequestDispatcher("WEB-INF/views/AddClientSuccess.jsp");
		view.forward(request, response);

	}

	public char[] generatePassword() {
		String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
		String specialCharacters = "!@#$";
		String numbers = "1234567890";
		String combinedChars = capitalCaseLetters + lowerCaseLetters
				+ specialCharacters + numbers;
		Random random = new Random();
		char[] password = new char[8];

		password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters
				.length()));
		password[1] = capitalCaseLetters.charAt(random
				.nextInt(capitalCaseLetters.length()));
		password[2] = specialCharacters.charAt(random.nextInt(specialCharacters
				.length()));
		password[3] = numbers.charAt(random.nextInt(numbers.length()));

		for (int i = 4; i < password.length; i++) {
			password[i] = combinedChars.charAt(random.nextInt(combinedChars
					.length()));
		}
		return password;
	}

}
