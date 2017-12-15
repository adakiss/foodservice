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

import hu.foodservice.ejb.menufacade.MenuFacade;
import hu.foodservice.ejb.menufacade.MenuStub;

@WebServlet("/MenuList")
public class MenuListController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = Logger.getLogger(MenuListController.class);
	
	@EJB
	private MenuFacade facade;

	@Override
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		
		LOGGER.info("Get all menus");
		
		try {
			List<MenuStub> menus = facade.getAllMenus();
			arg0.setAttribute("menus", menus);
		} catch (Exception e) {
			LOGGER.error(e, e);
			//throw e;
		}
		
		RequestDispatcher view = arg0.getRequestDispatcher("menulist.jsp");
		view.forward(arg0, arg1);
	}

	@Override
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		if (arg0.getParameter("remove") != null) {
			LOGGER.info("Delete menu from database");
			
			try {
				facade.removeMenu(arg0.getParameter("menu_name"));
			} catch (Exception e) {
				LOGGER.error(e, e);
			}
			RequestDispatcher view = arg0.getRequestDispatcher("EmployeeMainPanel.html");
			view.forward(arg0, arg1);
		}
	}
}
