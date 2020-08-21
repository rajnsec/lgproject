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
import com.iiht.evaluation.coronakit.service.ProductMasterService;
import com.iiht.evaluation.coronakit.service.ProductMasterServiceImpl;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.ProductMaster; 

@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductMasterDao productMasterDao;
	private ProductMasterService productMasterService;
	HttpSession session=null;
	String sessionId=null;
	
	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");

		this.productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);
		
		productMasterService=new ProductMasterServiceImpl(productMasterDao);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action =  request.getParameter("action");
		String viewName = "";
		try {
			switch (action) {
			case "login" : 
				viewName = adminLogin(request, response);
				break;
			case "newproduct":
				viewName = showNewProductForm(request, response);
				break;
			case "insertproduct":
				viewName = insertProduct(request, response);
				break;
			case "deleteproduct":
				viewName = deleteProduct(request, response);
				break;
			case "editproduct":
				viewName = showEditProductForm(request, response);
				break;
			case "updateproduct":
				viewName = updateProduct(request, response);
				break;
			case "list":
				viewName = listAllProducts(request, response);
				break;	
			case "logout":
				viewName = adminLogout(request, response);
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

	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		session=request.getSession();
		session.removeAttribute("username");
		session.invalidate();
		sessionId=null;
		return "index.jsp";
	}

	private String listAllProducts(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		session=request.getSession();
		if(!(session.getId().equals(sessionId)))
		{
			return "index.jsp";	}
	try	{
		List<ProductMaster> products=productMasterService.getAll();
		request.setAttribute("products", products);				
		return "listproducts.jsp";
	    }catch(CoronaException e) {request.setAttribute("errMsg", e.getErrMsg()); return "errorPage.jsp";}
		
		
	}

	private String updateProduct(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		session=request.getSession();
		if(!(session.getId().equals(sessionId)))
		{
			return "index.jsp";	}
		try
		{
		ProductMaster product= new ProductMaster();
		product.setId(Integer.parseInt(request.getParameter("pid")));
		product.setProductName(request.getParameter("pname"));
		product.setCost(request.getParameter("cost"));
		product.setProductDescription(request.getParameter("desc"));
		productMasterService.updateProduct(product);
		request.setAttribute("UpdateMsg", "Product Id: "+request.getParameter("pid")+" updated Successfully");
		
		return listAllProducts(request,response);
		}catch(CoronaException e) {request.setAttribute("errMsg", e.getErrMsg()); return "errorPage.jsp";}
	}

	private String showEditProductForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		session=request.getSession();
		if(!(session.getId().equals(sessionId)))
		{
			return "index.jsp";	}
		try
		{
		ProductMaster product=productMasterService.getProductById(Integer.parseInt(request.getParameter("pid")));
		
		request.setAttribute("product", product);
		return "editproduct.jsp";
		}catch(CoronaException e) {request.setAttribute("errMsg", e.getErrMsg()); return "errorPage.jsp";}
	}

	private String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		session=request.getSession();
		if(!(session.getId().equals(sessionId)))
		{
			return "index.jsp";	}
		try {
		// TODO Auto-generated method stub
		productMasterService.deleteProduct(Integer.parseInt(request.getParameter("pid")));
		request.setAttribute("DelMsg", "Product Id: "+request.getParameter("pid")+" deleted Successfully");
		return listAllProducts(request,response);
		}catch(CoronaException e) {request.setAttribute("errMsg", e.getErrMsg()); return "errorPage.jsp";}
	}

	private String insertProduct(HttpServletRequest request, HttpServletResponse response) {
		session=request.getSession();
		if(!(session.getId().equals(sessionId)))
		{
			return "index.jsp";	}
		// TODO Auto-generated method stub
		try {
		ProductMaster product= new ProductMaster();
		product.setId(Integer.parseInt(request.getParameter("pid")));
		product.setProductName(request.getParameter("pname"));
		product.setCost(request.getParameter("cost"));
		product.setProductDescription(request.getParameter("desc"));
		productMasterService.addProduct(product);
		request.setAttribute("AddMsg", "Product Id: "+request.getParameter("pid")+" added Successfully");
		
		return listAllProducts(request,response);
		}catch(CoronaException e) {request.setAttribute("errMsg", e.getErrMsg()); return "errorPage.jsp";}
	}

	private String showNewProductForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		session=request.getSession();
		if(!(session.getId().equals(sessionId)))
		{
			return "index.jsp";	}
		
		return "newproduct.jsp";
	}

	private String adminLogin(HttpServletRequest request, HttpServletResponse response) {
		
		if (request.getParameter("loginid").equals("admin") && request.getParameter("password").equals("admin") )
		{
			session=request.getSession(true);
			sessionId=session.getId();
			session.setAttribute("username", "admin");
			return listAllProducts(request,response);
		}		
		else
		{
			request.setAttribute("errorMsg","Username or password is wrong!");
			return "index.jsp";
		}
	}

	
}