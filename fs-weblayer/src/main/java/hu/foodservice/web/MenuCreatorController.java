package hu.foodservice.web;

import java.io.IOException;
import java.util.ArrayList;
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
import hu.foodservice.ejb.menufacade.MenuFacade;

@WebServlet("/MenuCreator")
public class MenuCreatorController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(MenuCreatorController.class);
	
	@EJB
	private MenuFacade facade;
	
	@EJB
	private MealFacade mealFacade;

	@Override
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		
		LOGGER.info("Initializing menu creator");
		
		try {
			List<MealStub> meals = mealFacade.getAllMeals();
			arg0.setAttribute("meals", meals);
		} catch (Exception e) {
			LOGGER.error(e, e);
			//throw e;
		}
		
		RequestDispatcher view = arg0.getRequestDispatcher("menucreator.jsp");
		view.forward(arg0, arg1);
	}

	@Override
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		
		if (arg0.getParameter("create") != null) {
			
			LOGGER.info("Creating new menu");
			
			try {
				List<String> meals = new ArrayList<String>();
				String[] mealsSelected = arg0.getParameterValues("meals");
				if (mealsSelected != null) {
					for(int i = 0; i < mealsSelected.length; i++) {
						if(mealsSelected[i] != null) {
							meals.add(mealsSelected[i]);
						}
					}
					
					facade.addMenu(arg0.getParameter("menu_name"), meals, true, Integer.parseInt(arg0.getParameter("menu_price")));
				}		
			} catch (Exception e) {
				LOGGER.error(e, e);
			}
			
			RequestDispatcher view = arg0.getRequestDispatcher("EmployeeMainPanel.html");
			view.forward(arg0, arg1);
		}
	}
}
