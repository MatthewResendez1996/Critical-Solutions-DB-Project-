package com.realtor.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.realtor.model.Contractor;
import com.realtor.model.ContractorListing;
import com.realtor.model.Listing;
import com.realtor.model.Users;
import com.realtor.model.Service;
import com.realtor.service.ContractorService;
import com.realtor.service.ListingService;
import com.realtor.service.ServiceTypeService;

/**
 * Servlet implementation class SubmitBillListing
 */
@WebServlet("/SubmitBillListing")
public class SubmitBillListing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	@EJB
	ContractorService co;
	@EJB
	ListingService ls;
	@EJB
	ServiceTypeService st;
	String contId = "";
	Integer contractListingId = 0;
	
    public SubmitBillListing() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String contractorId = request.getParameter("id");
		String contractorListingId = request.getParameter("cid");
		String listingId = request.getParameter("lid");
		String serviceId = request.getParameter("sid");
		contId = contractorId;
		Listing listObj = ls.getListingById(listingId);
		Service servObj = st.getServiceById(serviceId);
		Contractor cont = co.getContractor(contractorId);
		ContractorListing contractListObj = co.getContractorListingById(contractorListingId);
		contractListingId = contractListObj.getContractorListingId();
		Users us = co.getUser(cont);
		
		if(contractListObj != null){
			request.setAttribute("contractor_info", cont);
			request.setAttribute("user_info", us);
			request.setAttribute("listing_info", listObj);
			request.setAttribute("service_info", servObj);
			request.setAttribute("contractlist_info", contractListObj);
			RequestDispatcher view = request
					.getRequestDispatcher("WEB-INF/views/UpdateContractorListing.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String servStatus = request.getParameter("job_status");
		String servComment = request.getParameter("serv_comment");
		co.updateContractorListing(contractListingId, servStatus, servComment);
		Contractor cont = co.getContractor(contId);
		request.setAttribute("contractor_info", cont);
		RequestDispatcher view = request
				.getRequestDispatcher("WEB-INF/views/UpdateContractorListingSuccess.jsp");
		view.forward(request, response);
		
	}

}
