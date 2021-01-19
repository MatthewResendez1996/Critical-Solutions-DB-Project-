package com.realtor.controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.realtor.model.Contractor;
import com.realtor.model.Listing;
import com.realtor.model.Service;
import com.realtor.model.Users;
import com.realtor.model.ContractorListing;
import com.realtor.service.ContractorService;
import com.realtor.service.ListingService;
import com.realtor.service.ServiceTypeService;

/**
 * Servlet implementation class ContractorListing
 */
@WebServlet("/ListingContractor")
public class ListingContractor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	@EJB
	ContractorService cs;
	
	@EJB
	ListingService ls;
	
	@EJB
	ServiceTypeService sv;
	
	String contId = "";
	
    public ListingContractor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contractorId = request.getParameter("id");
		contId = contractorId;
		Contractor cont = cs.getContractor(contractorId);
		Users us = cs.getUser(cont);
		request.setAttribute("contractor_info", cont);
		request.setAttribute("user_info", us);
		RequestDispatcher view = request
				.getRequestDispatcher("WEB-INF/views/SearchListingContractor.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String listingId = request.getParameter("listing_id");
		String serviceStatus = request.getParameter("job_status");
		String serviceType = request.getParameter("service_type");
		Contractor contractor_info = cs.getContractor(contId);
		List<ContractorListing> contractList = null;
		List<Service> servList = null;
		List<Listing> listingList = null;
		Integer serviceId = 0;
		if(!listingId.equals("")){
			contractList = (List<ContractorListing>) cs.getContractorListingByContractorId(contId, listingId);
		}else if(!serviceStatus.equals("") && !serviceType.equals("")){
			serviceId = sv.getServiceId(serviceType);
			contractList = (List<ContractorListing>) cs.getContractorListingByServiceTypeStatus(contId, serviceStatus, serviceId);
		}else if(!serviceStatus.equals("")){
			contractList = (List<ContractorListing>) cs.getContractorListingByServiceStatus(contId, serviceStatus);
		}else if(!serviceType.equals("")){
			serviceId = sv.getServiceId(serviceType);
			contractList = (List<ContractorListing>) cs.getContractorListingByServiceType(contId, serviceId);
		}
		
		if(!contractList.isEmpty()){
			servList = sv.getService(contractList);
			listingList = sv.getListing(contractList);
			request.setAttribute("contract_list", contractList);
			request.setAttribute("service_list", servList);
			request.setAttribute("listing_list", listingList);
			request.setAttribute("contractor_info", contractor_info);
			RequestDispatcher view = request
					.getRequestDispatcher("WEB-INF/views/ListingContractor.jsp");
			view.forward(request, response);
		}else{
			request.setAttribute("contractor_info", contractor_info);
			RequestDispatcher view = request
					.getRequestDispatcher("WEB-INF/views/NoExistListingContractor.jsp");
			view.forward(request, response);
		}
		
		
	}

}
