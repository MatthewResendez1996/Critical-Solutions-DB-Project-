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
import com.realtor.model.Gender;
import com.realtor.model.Realtor;
import com.realtor.model.Users;
import com.realtor.service.ClientService;
import com.realtor.service.RealtorService;

/**
 * Servlet implementation class UpdateRealtor
 */
@WebServlet("/UpdateRealtor")
public class UpdateRealtor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	RealtorService rs;
	String rlId = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRealtor() {
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
		request.setAttribute("realtor_info", rl);
		request.setAttribute("user_info", us);
		RequestDispatcher view = request
				.getRequestDispatcher("WEB-INF/views/UpdateRealtor.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Realtor  rlObj = rs.getRealtor(rlId);
		
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
		
		r.setRealtor_id(rlObj.getRealtor_id());
		r.setFirst_name(firstName);
		r.setLast_name(lastName);
		r.setGender(Gender.valueOf(gender));
		r.setJob_title_id(rlObj.getJob_title_id());
		/*if (job_title.equals("Junior")) {
			r.setJob_title_id(1);
		} else {
			r.setJob_title_id(2);
		}*/
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
		r.setDob(dob);
		
		r.setStartDate(rlObj.getStartDate());
		rs.updateRealtor(r);
		request.setAttribute("realtor_info", rlObj);
		RequestDispatcher view = request
				.getRequestDispatcher("WEB-INF/views/UpdateRealtorSuccess.jsp");
		view.forward(request, response);
	}

}
