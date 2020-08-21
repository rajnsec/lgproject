package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.coronakit.exception.CoronaException;
import com.iiht.evaluation.coronakit.service.KitService;
import com.iiht.evaluation.coronakit.service.KitServiceImpl;
import com.iiht.evaluation.coronakit.service.ProductMasterService;
import com.iiht.evaluation.coronakit.service.ProductMasterServiceImpl;
import com.iiht.evaluation.coronokit.dao.KitDao;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.KitDetail;
import com.iiht.evaluation.coronokit.model.ProductMaster;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KitDao kitDAO;
	private ProductMasterDao productMasterDao;
	KitService kitService=null;
	ProductMasterService productMasterService=null;
	public static int customerId=0;
	HttpSession session=null;
	String sessionId=null;

	public void setKitDAO(KitDao kitDAO) {
		this.kitDAO = kitDAO;
	}

	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config. getServletContext().getInitParameter("jdbcPassword");
		
		this.kitDAO = new KitDao(jdbcURL, jdbcUsername, jdbcPassword);
		this.productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);
		 kitService=new KitServiceImpl(kitDAO);
		 productMasterService=new ProductMasterServiceImpl(productMasterDao);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String viewName = "";
		try {
			switch (action) {
			case "newuser":
				viewName = showNewUserForm(request, response);
				break;
			case "insertuser":
				viewName = insertNewUser(request, response);
				break;
			case "showproducts":
				viewName = showAllProducts(request, response);
				break;	
			case "addnewitem":
				viewName = addNewItemToKit(request, response);
				break;
			case "deleteitem":
				viewName = deleteItemFromKit(request, response);
				break;
			case "showkit":
				viewName = showKitDetails(request, response);
				break;
			case "placeorder":
				viewName = showPlaceOrderForm(request, response);
				break;
			case "saveorder":
				viewName = saveOrderForDelivery(request, response);
				break;	
			case "ordersummary":
				viewName = showOrderSummary(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;	
			}
		} catch (Exception ex) {
			
			throw new ServletException(ex.getMessage());
		}
			RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
			dispatch.forward(request, response);
	
	}

	private String showOrderSummary(HttpServletRequest request, HttpServletResponse response) {

		// TODO Auto-generated method stub
		return "index.jsp";
	}

	private String saveOrderForDelivery(HttpServletRequest request, HttpServletResponse response) {
		session=request.getSession();
		if(!(session.getId().equals(sessionId)))
		{
			return "index.jsp";	}
		// TODO Auto-generated method stub
		try
		{
		CoronaKit customer=new CoronaKit();
		customer.setId(customerId);
		customer.setDeliveryAddress(request.getParameter("flat")+" "+request.getParameter("street")+" "+request.getParameter("city")+" "+request.getParameter("pin"));
		kitService.updateCustomerAddress(customer);
		customer=kitService.getCustomerDetailById(customerId);
		request.setAttribute("customer", customer);
		KitDetail kitItem=new KitDetail();
		kitItem.setId(customerId);
		List<KitDetail> items=kitService.getKitDetails(kitItem);
		request.setAttribute("items", items);
		Integer OrderTotal=(Integer)kitService.getOrderTotal(kitItem);
		request.setAttribute("OrderTotal", OrderTotal);
		return "ordersummary.jsp";
		}catch(CoronaException e) {request.setAttribute("errMsg", e.getErrMsg()); return "errorPage.jsp";}
	}

	private String showPlaceOrderForm(HttpServletRequest request, HttpServletResponse response) {
		session=request.getSession();
		if(!(session.getId().equals(sessionId)))
		{
			return "index.jsp";	}
		// TODO Auto-generated method stub
		return "placeorder.jsp";
	}

	private String showKitDetails(HttpServletRequest request, HttpServletResponse response) {
		session=request.getSession();
		if(!(session.getId().equals(sessionId)))
		{
			return "index.jsp";	}
		// TODO Auto-generated method stub
		try
		{
		KitDetail kitItem=new KitDetail();
		kitItem.setId(customerId);
		List<KitDetail> items=kitService.getKitDetails(kitItem);
		request.setAttribute("items", items);
		Integer OrderTotal=(Integer)kitService.getOrderTotal(kitItem);
		request.setAttribute("OrderTotal", OrderTotal);
		return "showkit.jsp";
		}catch(CoronaException e) {request.setAttribute("errMsg", e.getErrMsg()); return "errorPage.jsp";}
	}

	private String deleteItemFromKit(HttpServletRequest request, HttpServletResponse response) {
		session=request.getSession();
		if(!(session.getId().equals(sessionId)))
		{
			return "index.jsp";	}
		try
		{
		// TODO Auto-generated method stub
		KitDetail kitItem=new KitDetail();
		kitItem.setId(customerId);
		kitItem.setQuantity(-1);
		kitItem.setProductId(Integer.parseInt(request.getParameter("pid")));
		kitService.deleteItemFromKit(kitItem);
		request.setAttribute("DelItemMsg","Product# "+request.getParameter("pid")+" has been deleted from cart successfully, if added earlier.");
		return showAllProducts(request,response);
		}catch(CoronaException e) {request.setAttribute("errMsg", e.getErrMsg()); return "errorPage.jsp";}
	}

	private String addNewItemToKit(HttpServletRequest request, HttpServletResponse response) {
		session=request.getSession();
		if(!(session.getId().equals(sessionId)))
		{
			return "index.jsp";	}
		// TODO Auto-generated method stub
		try {
		KitDetail kitItem=new KitDetail();
		kitItem.setId(customerId);
		kitItem.setQuantity(0);
		kitItem.setProductId(Integer.parseInt(request.getParameter("pid")));

		kitService.addItemToKit(kitItem);
		request.setAttribute("AddItemMsg","Product# "+request.getParameter("pid")+" added to cart successfully.");
		return showAllProducts(request,response);
		}catch(CoronaException e) {request.setAttribute("errMsg", e.getErrMsg()); return "errorPage.jsp";}
	}

	private String showAllProducts(HttpServletRequest request, HttpServletResponse response) {
		session=request.getSession();
		if(!(session.getId().equals(sessionId)))
		{
			return "index.jsp";	}
		// TODO Auto-generated method stub
		try
		{

		List<ProductMaster> products=productMasterService.getAll();
		request.setAttribute("products", products);	
		
		return "showproductstoadd.jsp";
		}catch(CoronaException e) {request.setAttribute("errMsg", e.getErrMsg()); return "errorPage.jsp";}
	}

	private String insertNewUser(HttpServletRequest request, HttpServletResponse response) {
		session=request.getSession(true);
		sessionId=session.getId();
		// TODO Auto-generated method stub
		try
		{
		CoronaKit customer=new CoronaKit();
		customer.setId(Integer.parseInt(request.getParameter("cid")));
		customerId=customer.getId();
		customer.setPersonName(request.getParameter("cname"));
		customer.setEmail(request.getParameter("email"));
		customer.setContactNumber(request.getParameter("phone"));
		kitService.addCustomer(customer);
		session.setAttribute("user", request.getParameter("cname"));
		//request.setAttribute("AddMsg", "Welcome "+request.getParameter("cname"));
			
		return showAllProducts(request,response);
		}catch(CoronaException e) {request.setAttribute("errMsg", e.getErrMsg()); return "errorPage.jsp";}
	}

	private String showNewUserForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "newuser.jsp";
	}
}