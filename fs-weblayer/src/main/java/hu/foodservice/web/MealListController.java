package hu.foodservice.web;

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

import hu.foodservice.ejb.mealfacade.MealFacade;
import hu.foodservice.ejb.mealfacade.MealStub;

@WebServlet("/MealList")
public class MealListController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = Logger.getLogger(MealListController.class);
	
	@EJB
	private MealFacade facade;

	@Override
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		
		LOGGER.info("Get all meals");
		
		try {
			List<MealStub> meals = facade.getAllMeals();
			arg0.setAttribute("meals", meals);
		} catch (Exception e) {
			LOGGER.error(e, e);
			//throw e;
		}
		
		RequestDispatcher view = arg0.getRequestDispatcher("meallist.jsp");
		view.forward(arg0, arg1);
	}
	
	@Override
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		
		if (arg0.getParameter("add") != null) {
			LOGGER.info("Adding new meal to the database");
			
			try {
				facade.addMeal(arg0.getParameter("meal_name"), arg0.getParameter("meal_description"), Boolean.parseBoolean(arg0.getParameter("meal_isAllergic")), Integer.parseInt(arg0.getParameter("meal_price")));
			} catch (Exception e) {
				LOGGER.error(e, e);
			}
			
			RequestDispatcher view = arg0.getRequestDispatcher("EmployeeMainPanel.html");
			view.forward(arg0, arg1);
		} else if (arg0.getParameter("remove") != null) {
			LOGGER.info("Delete meal from database");
			
			try {
				facade.removeMeal(arg0.getParameter("meal_name"));
			} catch (Exception e) {
				LOGGER.error(e, e);
			}
			RequestDispatcher view = arg0.getRequestDispatcher("EmployeeMainPanel.html");
			view.forward(arg0, arg1);
		}
	}
}
