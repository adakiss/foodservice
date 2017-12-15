package hu.foodservice.ejb.menufacade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import hu.foodservice.ejb.converter.MenuConverter;
import hu.foodservice.ejb.exception.FoodFacadeException;
import hu.foodservice.ejb.exception.FoodServiceError;
import hu.foodservice.persistence.entity.Meal;
import hu.foodservice.persistence.entity.Menu;
import hu.foodservice.persistence.exception.FoodServiceException;
import hu.foodservice.persistence.mealservice.MealService;
import hu.foodservice.persistence.menuservice.MenuService;

@Stateless
public class MenuFacadeImpl implements MenuFacade {
	
	private static final Logger LOGGER = Logger.getLogger(MenuFacadeImpl.class);
	
	@EJB
	private MenuService service;
	
	@EJB
	private MealService mealService;
	
	@EJB
	private MenuConverter menuConverter;
	
	@Override
	public MenuStub getMenu(String name) throws FoodFacadeException {
		
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get MenuStub with name: " + name);
		}
		
		MenuStub menu;
		try {
			menu = menuConverter.convert(service.readMenu(name));
		} catch (FoodServiceException e) {
			throw new FoodFacadeException(FoodServiceError.UNEXPECTED, e.getLocalizedMessage());
		}
		
		return menu;
	}
	
	@Override
	public List<MenuStub> getAllMenus() throws FoodFacadeException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get all MenuStubs");
		}
		
		List<MenuStub> menuStubs = new ArrayList<MenuStub>();
		
		List<Menu> menus;
		try {
			menus = service.readAllMenu();
		} catch (FoodServiceException e) {
			throw new FoodFacadeException(FoodServiceError.UNEXPECTED, e.getLocalizedMessage());
		}
		Iterator<Menu> it = menus.iterator();
		while(it.hasNext()) {
			menuStubs.add(menuConverter.convert(it.next()));
		}
		
		return menuStubs;
	}

	@Override
	public MenuStub addMenu(String name, List<String> meals, Boolean isGeneric, Integer price) throws FoodFacadeException {
		
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Adding new menu");
		}
		
		MenuStub menu = null;
		
		try {
			if (!service.existsMenu(name)) {
				HashSet<Meal> meals2 = new HashSet<Meal>();
				Iterator<String> it = meals.iterator();
				while(it.hasNext()) {
					String next = it.next();
					if(mealService.existsMeal(next)) {
						meals2.add(mealService.readMeal(next));
					} else {
						throw new FoodFacadeException(FoodServiceError.NOT_EXISTS, next + " as meal not exists", next);
					}
				}
				service.addMenu(name, meals2, price, isGeneric);
				
				menu = menuConverter.convert(service.readMenu(name));
			} else {
				throw new FoodFacadeException(FoodServiceError.RESOURCE_EXISTS, "Menu to be added already exists", name);
			}
		} catch (FoodServiceException e) {
			throw new FoodFacadeException(FoodServiceError.UNEXPECTED, e.getLocalizedMessage());
		}
		
		return menu;
	}

	@Override
	public void removeMenu(String name) throws FoodFacadeException {
		
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Deleting a menu");
		}
		
		try {
			if(service.existsMenu(name)) {
				service.deleteMenu(name);
			} else {
				throw new FoodFacadeException(FoodServiceError.NOT_EXISTS, "Menu to delete does not exists", name);
			}
		} catch (FoodServiceException e) {
			throw new FoodFacadeException(FoodServiceError.UNEXPECTED, e.getLocalizedMessage());
		}
	}

	@Override
	public List<MenuStub> getAllGenericMenus() throws FoodFacadeException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get all generic MenuStubs");
		}
		
		List<MenuStub> menuStubs = new ArrayList<MenuStub>();
		
		List<Menu> menus;
		try {
			menus = service.readAllGenericMenu();
		} catch (FoodServiceException e) {
			throw new FoodFacadeException(FoodServiceError.UNEXPECTED, e.getLocalizedMessage());
		}
		Iterator<Menu> it = menus.iterator();
		while(it.hasNext()) {
			menuStubs.add(menuConverter.convert(it.next()));
		}
		
		return menuStubs;
	}
}
