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
import com.realtor.model.UserType;
import com.realtor.model.Users;
import com.realtor.service.ContractorService;

/**
 * Servlet implementation class UpdateContractor
 */
@WebServlet("/UpdateContractor")
public class UpdateContractor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	ContractorService co;
	String contId = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateContractor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contractorId = request.getParameter("id");
		contId = contractorId;
		Contractor cont = co.getContractor(contractorId);
		Users us = co.getUser(cont);
		request.setAttribute("contractor_info", cont);
		request.setAttribute("user_info", us);
		RequestDispatcher view = request
				.getRequestDispatcher("WEB-INF/views/UpdateContractor.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Contractor  contObj = co.getContractor(contId);
		
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
		c.setContractor_id(contObj.getContractor_id());
		c.setCompanyAddress(companyAddress);
		c.setCity(city);
		c.setState(companyState);
		c.setZipCode(zipCode);
		c.setPhoneNumber(phoneNumber);
		c.setEmail(email);
		c.setIs_delete(0);
		co.updateContractor(c);
		request.setAttribute("contractor_info", contObj);
		RequestDispatcher view = request
				.getRequestDispatcher("WEB-INF/views/UpdateContractorSuccess.jsp");
		view.forward(request, response);
	}

}
