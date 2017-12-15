package hu.foodservice.ejb.mealfacade;

import java.util.List;

import javax.ejb.Local;

import hu.foodservice.ejb.exception.FoodFacadeException;

@Local
public interface MealFacade {

	MealStub getMeal(String name) throws FoodFacadeException;
	
	List<MealStub> getAllMeals() throws FoodFacadeException;
	
	MealStub addMeal(String name, String description, Boolean isAllergic, Integer price) throws FoodFacadeException;
	
	void removeMeal(String name) throws FoodFacadeException;
}
