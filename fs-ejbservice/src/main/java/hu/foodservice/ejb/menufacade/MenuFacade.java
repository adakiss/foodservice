package hu.foodservice.ejb.menufacade;

import java.util.List;

import javax.ejb.Local;

import hu.foodservice.ejb.exception.FoodFacadeException;

@Local
public interface MenuFacade {
	
	MenuStub getMenu(String name) throws FoodFacadeException;
	
	MenuStub addMenu(String name, List<String> meals, Boolean isGeneric, Integer price) throws FoodFacadeException;
	
	void removeMenu(String name) throws FoodFacadeException;
	
	List<MenuStub> getAllMenus() throws FoodFacadeException;
	
	List<MenuStub> getAllGenericMenus() throws FoodFacadeException;
}
