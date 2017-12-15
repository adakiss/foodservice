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

import hu.foodservice.ejb.menufacade.MenuFacade;
import hu.foodservice.ejb.menufacade.MenuStub;

@WebServlet("/Menu")
public class MenuController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(MenuController.class);
	
	@EJB
	private MenuFacade facade;

	@Override
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		
		String menuName = arg0.getParameter("name");
		LOGGER.info("Get menu with name: " + menuName);
		
		try {
			MenuStub menu = facade.getMenu(menuName);
			arg0.setAttribute("menu", menu);
		} catch (Exception e) {
			LOGGER.error(e, e);
			//throw e;
		}
		
		RequestDispatcher view = arg0.getRequestDispatcher("menu.jsp");
		view.forward(arg0, arg1);
	}
	
	
}
