package hu.foodservice.web;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

@WebServlet("/Order")
public class OrderController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static Logger LOGGER = Logger.getLogger(OrderController.class);
	
	@EJB
	private OrderFacade facade;

	@Override
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		
		String orderCode = arg0.getParameter("orderCode");
		LOGGER.info("Get order with ordercode: " + orderCode);
		
		try {
			CustomerOrderStub custOrder = facade.getCustomerOrder(orderCode);
			arg0.setAttribute("custOrder", custOrder);
		} catch (Exception e) {
			LOGGER.error(e, e);
			//throw e;
		}
		
		RequestDispatcher view = arg0.getRequestDispatcher("order.jsp");
		view.forward(arg0, arg1);
	}

	@Override
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		LOGGER.info("Update customer order");
		
		String orderCode = arg0.getParameter("orderCode");
		String deadLine = arg0.getParameter("deadline");
		Boolean next_phase = Boolean.parseBoolean(arg0.getParameter("next_phase"));
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			Date date = new Date(format.parse(deadLine).getTime());
			facade.updateOrder(orderCode, date, next_phase);
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		
		RequestDispatcher view = arg0.getRequestDispatcher("EmployeeMainPanel.html");
		view.forward(arg0, arg1);
	}
}
