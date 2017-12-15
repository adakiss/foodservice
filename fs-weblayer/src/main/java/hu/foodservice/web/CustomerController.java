package hu.foodservice.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import hu.foodservice.ejb.customerfacade.CustomerFacade;
import hu.foodservice.ejb.customerfacade.CustomerStub;

@WebServlet("/Customer")
public class CustomerController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	Logger LOGGER = Logger.getLogger(CustomerController.class);
	
	@EJB
	private CustomerFacade facade;

	@Override
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		
		String custName = arg0.getParameter("name");
		LOGGER.info("Get customer with name: " + custName);
		
		try {
			CustomerStub cust = facade.getCustomer(custName);
			arg0.setAttribute("cust", cust);
		} catch (Exception e) {
			LOGGER.error(e, e);
			//throw e;
		}
		
		RequestDispatcher view = arg0.getRequestDispatcher("customer.jsp");
		view.forward(arg0, arg1);
	}
}
