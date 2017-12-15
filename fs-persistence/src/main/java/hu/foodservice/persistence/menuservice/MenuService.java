package hu.foodservice.persistence.menuservice;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import hu.foodservice.persistence.entity.Meal;
import hu.foodservice.persistence.entity.Menu;
import hu.foodservice.persistence.exception.FoodServiceException;

@Local
public interface MenuService {
	
	Menu readMenu(String name) throws FoodServiceException;
	
	Menu addMenu(String name, Set<Meal> meals, Integer price, Boolean isGeneric) throws FoodServiceException;
	
	Boolean existsMenu(String name) throws FoodServiceException;
	
	void deleteMenu(String name) throws FoodServiceException;
	
	List<Menu> readAllMenu() throws FoodServiceException;
	
	List<Menu> readAllGenericMenu() throws FoodServiceException;
}
