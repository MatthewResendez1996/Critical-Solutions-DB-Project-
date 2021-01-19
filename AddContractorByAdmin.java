package com.realtor.controller;

import java.io.IOException;
import java.util.Random;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.realtor.model.Contractor;
import com.realtor.model.UserType;
import com.realtor.model.Users;
import com.realtor.service.ContractorService;
import com.realtor.service.LoginService;

/**
 * Servlet implementation class AddContractorByAdmin
 */
@WebServlet("/AddContractorByAdmin")
public class AddContractorByAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	@EJB
	ContractorService cs;

	@EJB
	LoginService ls;
	
	String userGlob = "";

	public AddContractorByAdmin() {
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
				.getRequestDispatcher("WEB-INF/views/AddContractor.jsp");
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
		Contractor c = new Contractor();

		String companyName = request.getParameter("company_name");
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String companyAddress = request.getParameter("company_address");
		String city = request.getParameter("city");
		String companyState = request.getParameter("state_name");
		String zipCode = request.getParameter("zip_code");
		String phoneNumber = request.getParameter("phone_number");
		String email = request.getParameter("email");

		if (companyName.length()>0) {

			c.setCompanyName(companyName);

		} else {

			c.setCompanyName(null);

		}

		if (firstName.length()>0) {

			c.setFirstName(firstName);

		} else {

			c.setFirstName(null);

		}

		if (lastName.length()>0) {

			c.setLastName(lastName);

		} else {

			c.setLastName(null);

		}

		c.setCompanyAddress(companyAddress);
		c.setCity(city);
		c.setState(companyState);
		c.setZipCode(zipCode);
		c.setPhoneNumber(phoneNumber);
		c.setEmail(email);
		c.setIs_delete(0);

		Users us = new Users();
		UserType ut = new UserType();
		ut.setUser_type_id(1);

		us.setPassword(String.valueOf(generatePassword()));
		us.setClient_id(null);
		us.setContractor_id(c);
		us.setRealtor_id(null);
		us.setUser_type_id(ut);
		cs.addContractor(c);
		ls.addUser(us);

		RequestDispatcher view = request
				.getRequestDispatcher("WEB-INF/views/AddContractorSuccess.jsp");
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
