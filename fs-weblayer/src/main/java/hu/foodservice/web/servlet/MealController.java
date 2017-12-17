package hu.foodservice.web.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import hu.foodservice.ejb.mealfacade.MealFacade;
import hu.foodservice.ejb.mealfacade.MealStub;

@WebServlet("/Meal")
public class MealController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(MealController.class);
	
	@EJB
	private MealFacade facade;

	@Override
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		
		String mealName = arg0.getParameter("name");
		
		try {
			MealStub meal = facade.getMeal(mealName);
			arg0.setAttribute("meal", meal);
		} catch (Exception e) {
			LOGGER.error(e, e);
			//throw e;
		}
		
		RequestDispatcher view = arg0.getRequestDispatcher("meal.jsp");
		view.forward(arg0, arg1);
	}
}
