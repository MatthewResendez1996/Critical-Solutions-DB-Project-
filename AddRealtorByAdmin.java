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

import com.realtor.model.Gender;
import com.realtor.model.Realtor;
import com.realtor.model.UserType;
import com.realtor.model.Users;
import com.realtor.service.LoginService;
import com.realtor.service.RealtorService;

/**
 * Servlet implementation class AddRealtorByAdmin
 */
@WebServlet("/AddRealtorByAdmin")
public class AddRealtorByAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	@EJB
	RealtorService rs;

	@EJB
	LoginService ls;
	
	String userGlob = "";

	public AddRealtorByAdmin() {
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
				.getRequestDispatcher("WEB-INF/views/AddRealtor.jsp");
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
		Realtor r = new Realtor();

		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String gender = request.getParameter("gender");
		String dob_raw = request.getParameter("dob");
		String job_title = request.getParameter("job_title");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state_name");
		String zipCode = request.getParameter("zip_code");
		String startDate_raw = request.getParameter("start_date");
		String phoneNumber = request.getParameter("phone_number");
		String email = request.getParameter("email");

		r.setFirst_name(firstName);
		r.setLast_name(lastName);
		r.setGender(Gender.valueOf(gender));
		if (job_title.equals("Junior")) {
			r.setJob_title_id(1);
		} else {
			r.setJob_title_id(2);
		}
		r.setAddress(address);
		r.setCity(city);
		r.setState(state);
		r.setZipCode(zipCode);
		r.setPhoneNumber(phoneNumber);
		r.setEmail(email);
		r.setIs_delete(0);

		String[] dobArr = dob_raw.split("\\-");

		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, Integer.parseInt(dobArr[0]));
		cal.set(Calendar.MONTH, Integer.parseInt(dobArr[1]) - 1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dobArr[2]));

		Date dob = cal.getTime();

		String[] dobArr1 = startDate_raw.split("\\-");

		Calendar cal1 = Calendar.getInstance();

		cal1.set(Calendar.YEAR, Integer.parseInt(dobArr1[0]));
		cal1.set(Calendar.MONTH, Integer.parseInt(dobArr1[1]) - 1);
		cal1.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dobArr1[2]));

		Date startDate = cal1.getTime();
		r.setDob(dob);
		r.setStartDate(startDate);

		Users us = new Users();
		UserType ut = new UserType();
		ut.setUser_type_id(3);

		us.setPassword(String.valueOf(generatePassword()));
		us.setClient_id(null);
		us.setContractor_id(null);
		us.setRealtor_id(r);
		us.setUser_type_id(ut);

		rs.addRealtor(r);
		ls.addUser(us);

		RequestDispatcher view = request
				.getRequestDispatcher("WEB-INF/views/AddRealtorSuccess.jsp");
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
