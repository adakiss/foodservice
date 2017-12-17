package hu.foodservice.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import hu.foodservice.ejb.customerfacade.CustomerFacade;
import hu.foodservice.ejb.customerfacade.CustomerStub;
import hu.foodservice.ejb.exception.FoodFacadeException;
import hu.foodservice.ejb.mealfacade.MealFacade;
import hu.foodservice.ejb.mealfacade.MealStub;
import hu.foodservice.ejb.menufacade.MenuFacade;
import hu.foodservice.ejb.menufacade.MenuStub;
import hu.foodservice.ejb.orderfacade.CustomerOrderStub;
import hu.foodservice.ejb.orderfacade.OrderFacade;



@WebServlet("/OrderPing")
public class OrderPingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@EJB
	OrderFacade facade;
	
	@EJB
	CustomerFacade custFacade;
	
	@EJB
	MealFacade mealFacade;
	
	@EJB
	MenuFacade menuFacade;
	
	private static final Logger LOGGER = Logger.getLogger(OrderPingServlet.class);

	@Override
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		
		LOGGER.debug("OrderPingServlet ping received.");
		
		arg1.setCharacterEncoding("UTF-8");
		PrintWriter out = arg1.getWriter();
		
		CustomerOrderStub custOrder;
		try {
			custOrder = this.facade.getCustomerOrder("T0");
		
			out.println(custOrder.toString());
			
			CustomerStub customer = this.custFacade.getCustomer("Adam Kidd");
			out.println(customer.toString());
			
			MenuStub menu = this.menuFacade.getMenu("A menu");
			out.println(menu.toString());
			
			MealStub meal = this.mealFacade.getMeal("pizza");
			out.println(meal.toString());
		} catch (FoodFacadeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		out.close();
	}
	
	
	
}
