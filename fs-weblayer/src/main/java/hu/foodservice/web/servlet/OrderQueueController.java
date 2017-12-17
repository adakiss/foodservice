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

import hu.foodservice.ejb.orderfacade.CustomerOrderStub;
import hu.foodservice.ejb.orderfacade.OrderFacade;

@WebServlet("/OrderQueue")
public class OrderQueueController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = Logger.getLogger(OrderQueueController.class);
	
	@EJB
	private OrderFacade facade;

	@Override
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		
		LOGGER.info("Get all orders");
		
		try {
			List<CustomerOrderStub> custOrders = facade.getAllCustomerOrders();
			arg0.setAttribute("custOrders", custOrders);
		} catch (Exception e) {
			LOGGER.error(e, e);
			//throw e;
		}
		
		RequestDispatcher view = arg0.getRequestDispatcher("orderqueue.jsp");
		view.forward(arg0, arg1);
	}
	
}
