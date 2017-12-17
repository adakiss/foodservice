package hu.foodservice.web.servlet;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/CustomerList")
public class CustomerListController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = Logger.getLogger(CustomerListController.class);
	
	@EJB
	private CustomerFacade facade;

	@Override
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		
		LOGGER.info("Get all customers");
		
		try {
			List<CustomerStub> custs = facade.getAllCustomers();
			arg0.setAttribute("custs", custs);
		} catch (Exception e) {
			LOGGER.error(e, e);
			//throw e;
		}
		
		RequestDispatcher view = arg0.getRequestDispatcher("customerlist.jsp");
		view.forward(arg0, arg1);
	}

	@Override
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		
		if (arg0.getParameter("add") != null) {
			LOGGER.info("Adding new customer to the database");
			
			try {
				facade.addCustomer(arg0.getParameter("customer_name"), arg0.getParameter("customer_email"), arg0.getParameter("customer_address"), Integer.parseInt(arg0.getParameter("customer_phone")));
			} catch (Exception e) {
				LOGGER.error(e, e);
			}
			
			RequestDispatcher view = arg0.getRequestDispatcher("EmployeeMainPanel.html");
			view.forward(arg0, arg1);
		} else if (arg0.getParameter("remove") != null) {
			LOGGER.info("Delete customer from database");
			
			try {
				facade.deleteCustomer(arg0.getParameter("customer_email"));
			} catch (Exception e) {
				LOGGER.error(e, e);
			}
			RequestDispatcher view = arg0.getRequestDispatcher("EmployeeMainPanel.html");
			view.forward(arg0, arg1);
		}
	}
}
