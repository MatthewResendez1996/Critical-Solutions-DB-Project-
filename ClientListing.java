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

import com.realtor.model.Client;
import com.realtor.model.Listing;
import com.realtor.model.Users;
import com.realtor.service.ClientService;
import com.realtor.service.ListingService;

/**
 * Servlet implementation class ClientListing
 */
@WebServlet("/ClientListing")
public class ClientListing extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	ListingService ls;
	
	@EJB
	ClientService cs;
	
	String cltId = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientListing() {
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
				.getRequestDispatcher("WEB-INF/views/SearchClientListing.jsp");
		view.forward(request, response);
		
		/*String clientId = request.getParameter("id");
		Client clObj = cs.getClient(clientId);
		float clBudget = clObj.getBudget();
		List<Listing>  lList = ls.getListingByClient(clBudget);
		request.setAttribute("listing_list", lList);
		request.setAttribute("client_info", clObj);
		RequestDispatcher view = request
				.getRequestDispatcher("WEB-INF/views/ClientListing.jsp");
		view.forward(request, response);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String listingTypeName = request.getParameter("list_type");
		String propertyTypeName = request.getParameter("proper_type");
		String countyName = request.getParameter("county");
		String budgetListing = request.getParameter("budget");
		Client clObj = cs.getClient(cltId);
		List<Listing>  lList = null;
		Integer countyId = 0;
		Integer propertyTypeId = 0;
		Integer listingTypeId = 0;
		
		if(!listingTypeName.equals("") && !propertyTypeName.equals("") && !countyName.equals("") && !budgetListing.equals("")){
			
			countyId = ls.getCountyId(countyName);
			propertyTypeId = ls.getPropertyTypeId(propertyTypeName);
			listingTypeId = ls.getListingTypeId(listingTypeName);
			lList = ls.getListingByCountyListingTypePropertyBudget(listingTypeId, propertyTypeId, countyId, budgetListing);
			
			if(!lList.isEmpty()){
				request.setAttribute("client_info", clObj);
				request.setAttribute("listing_list", lList);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListing.jsp");
				view.forward(request, response);
			}else{
				request.setAttribute("client_info", clObj);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListingNoResult.jsp");
				view.forward(request, response);
			}
			
		}else if(!listingTypeName.equals("") && !propertyTypeName.equals("") && !countyName.equals("")){
			
			countyId = ls.getCountyId(countyName);
			propertyTypeId = ls.getPropertyTypeId(propertyTypeName);
			listingTypeId = ls.getListingTypeId(listingTypeName);
			lList = ls.getListingByCountyListingTypeProperty(listingTypeId, propertyTypeId, countyId);
			
			if(!lList.isEmpty()){
				request.setAttribute("client_info", clObj);
				request.setAttribute("listing_list", lList);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListing.jsp");
				view.forward(request, response);
			}else{
				request.setAttribute("client_info", clObj);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListingNoResult.jsp");
				view.forward(request, response);
			}
			
		}else if(!propertyTypeName.equals("") && !countyName.equals("") && !budgetListing.equals("")){
			
			countyId = ls.getCountyId(countyName);
			propertyTypeId = ls.getPropertyTypeId(propertyTypeName);
			lList = ls.getListingByCountyPropertyTypeBudget(propertyTypeId, countyId, budgetListing);
			
			if(!lList.isEmpty()){
				request.setAttribute("client_info", clObj);
				request.setAttribute("listing_list", lList);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListing.jsp");
				view.forward(request, response);
			}else{
				request.setAttribute("client_info", clObj);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListingNoResult.jsp");
				view.forward(request, response);
			}
		}else if(!listingTypeName.equals("") && !countyName.equals("") && !budgetListing.equals("")){
			
			countyId = ls.getCountyId(countyName);
			listingTypeId = ls.getListingTypeId(listingTypeName);
			lList = ls.getListingByCountyListingTypeBudget(listingTypeId, countyId, budgetListing);
			
			if(!lList.isEmpty()){
				request.setAttribute("client_info", clObj);
				request.setAttribute("listing_list", lList);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListing.jsp");
				view.forward(request, response);
			}else{
				request.setAttribute("client_info", clObj);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListingNoResult.jsp");
				view.forward(request, response);
			}
		}else if(!listingTypeName.equals("") && !propertyTypeName.equals("") && !budgetListing.equals("")){
			
			propertyTypeId = ls.getPropertyTypeId(propertyTypeName);
			listingTypeId = ls.getListingTypeId(listingTypeName);
			lList = ls.getListingByListingTypePropertyBudget(listingTypeId, propertyTypeId, budgetListing);
			
			if(!lList.isEmpty()){
				request.setAttribute("client_info", clObj);
				request.setAttribute("listing_list", lList);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListing.jsp");
				view.forward(request, response);
			}else{
				request.setAttribute("client_info", clObj);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListingNoResult.jsp");
				view.forward(request, response);
			}
			
		}else if(!listingTypeName.equals("") && !propertyTypeName.equals("")){
			
			propertyTypeId = ls.getPropertyTypeId(propertyTypeName);
			listingTypeId = ls.getListingTypeId(listingTypeName);
			lList = ls.getListingByListingPropertyType(listingTypeId, propertyTypeId);
			
			if(!lList.isEmpty()){
				request.setAttribute("client_info", clObj);
				request.setAttribute("listing_list", lList);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListing.jsp");
				view.forward(request, response);
			}else{
				request.setAttribute("client_info", clObj);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListingNoResult.jsp");
				view.forward(request, response);
			}
			
		}else if(!listingTypeName.equals("") && !countyName.equals("")){
			
			countyId = ls.getCountyId(countyName);
			listingTypeId = ls.getListingTypeId(listingTypeName);
			lList = ls.getListingByCountyListingType(listingTypeId, countyId);
			
			if(!lList.isEmpty()){
				request.setAttribute("client_info", clObj);
				request.setAttribute("listing_list", lList);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListing.jsp");
				view.forward(request, response);
			}else{
				request.setAttribute("client_info", clObj);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListingNoResult.jsp");
				view.forward(request, response);
			}
			
		}else if(!listingTypeName.equals("") && !budgetListing.equals("")){
			
			listingTypeId = ls.getListingTypeId(listingTypeName);
			lList = ls.getListingByListingTypeBudget(listingTypeId, budgetListing);
			
			if(!lList.isEmpty()){
				request.setAttribute("client_info", clObj);
				request.setAttribute("listing_list", lList);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListing.jsp");
				view.forward(request, response);
			}else{
				request.setAttribute("client_info", clObj);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListingNoResult.jsp");
				view.forward(request, response);
			}
			
		}else if(!propertyTypeName.equals("") && !countyName.equals("")){
			
			countyId = ls.getCountyId(countyName);
			propertyTypeId = ls.getPropertyTypeId(propertyTypeName);
			lList = ls.getListingByCountyPropertyType(propertyTypeId, countyId);
			
			if(!lList.isEmpty()){
				request.setAttribute("client_info", clObj);
				request.setAttribute("listing_list", lList);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListing.jsp");
				view.forward(request, response);
			}else{
				request.setAttribute("client_info", clObj);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListingNoResult.jsp");
				view.forward(request, response);
			}
			
		}else if(!propertyTypeName.equals("") && !budgetListing.equals("")){
			
			propertyTypeId = ls.getPropertyTypeId(propertyTypeName);
			lList = ls.getListingByPropertyTypeBudget(propertyTypeId, budgetListing);
			
			if(!lList.isEmpty()){
				request.setAttribute("client_info", clObj);
				request.setAttribute("listing_list", lList);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListing.jsp");
				view.forward(request, response);
			}else{
				request.setAttribute("client_info", clObj);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListingNoResult.jsp");
				view.forward(request, response);
			}
			
		}else if(!listingTypeName.equals("")){
			
			listingTypeId = ls.getListingTypeId(listingTypeName);
			lList = ls.getListingByListingType(listingTypeId);
			
			if(!lList.isEmpty()){
				request.setAttribute("client_info", clObj);
				request.setAttribute("listing_list", lList);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListing.jsp");
				view.forward(request, response);
			}else{
				request.setAttribute("client_info", clObj);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListingNoResult.jsp");
				view.forward(request, response);
			}
			
		}else if(!propertyTypeName.equals("")){
			
			propertyTypeId = ls.getPropertyTypeId(propertyTypeName);
			lList = ls.getListingByPropertyType(propertyTypeId);
			
			if(!lList.isEmpty()){
				request.setAttribute("client_info", clObj);
				request.setAttribute("listing_list", lList);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListing.jsp");
				view.forward(request, response);
			}else{
				request.setAttribute("client_info", clObj);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListingNoResult.jsp");
				view.forward(request, response);
			}
			
		}else if(!countyName.equals("")){
			
			countyId = ls.getCountyId(countyName);
			lList = ls.getListingByCounty(countyId);
			
			if(!lList.isEmpty()){
				request.setAttribute("client_info", clObj);
				request.setAttribute("listing_list", lList);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListing.jsp");
				view.forward(request, response);
			}else{
				request.setAttribute("client_info", clObj);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListingNoResult.jsp");
				view.forward(request, response);
			}
			
		}else if(!budgetListing.equals("")){
			
			lList = ls.getListingByBudget(budgetListing);
			
			if(!lList.isEmpty()){
				request.setAttribute("client_info", clObj);
				request.setAttribute("listing_list", lList);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListing.jsp");
				view.forward(request, response);
			}else{
				request.setAttribute("client_info", clObj);
				RequestDispatcher view = request
						.getRequestDispatcher("WEB-INF/views/ClientListingNoResult.jsp");
				view.forward(request, response);
			}
			
		}else{
			request.setAttribute("client_info", clObj);
			RequestDispatcher view = request
					.getRequestDispatcher("WEB-INF/views/ClientListingNoCriteria.jsp");
			view.forward(request, response);
		}
		
	}

}
