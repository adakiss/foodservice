package hu.foodservice.webservice.menu;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import hu.foodservice.ejb.exception.FoodFacadeException;
import hu.foodservice.ejb.menufacade.MenuFacade;
import hu.foodservice.ejb.menufacade.MenuInput;
import hu.foodservice.ejb.menufacade.MenuStub;

@Stateless
public class MenuRestServiceBean implements MenuRestService {

	private static final Logger LOGGER = Logger.getLogger(MenuRestServiceBean.class);
	
	@EJB
	private MenuFacade facade;
	
	@Override
	public List<MenuStub> getMenus() throws FoodFacadeException {
		LOGGER.info("Getting all menus");
		return facade.getAllGenericMenus();
	}

	@Override
	public MenuStub createMenu(MenuInput input) throws FoodFacadeException {
		LOGGER.info("Creating new menu: " + input.getMenuName());
		return facade.addMenu(input.getMenuName(), input.getMeals(), false, 0);
	}

}
