package hu.foodservice.persistence.mealservice;

import java.util.List;

import javax.ejb.Local;

import hu.foodservice.persistence.entity.Meal;
import hu.foodservice.persistence.exception.FoodServiceException;

@Local
public interface MealService {
	
	Meal readMeal(String name) throws FoodServiceException;
	
	Boolean existsMeal(String name) throws FoodServiceException;
	
	void deleteMeal(String name) throws FoodServiceException;
	
	Meal addMeal(String name, String description, Boolean isAllergic, Integer price) throws FoodServiceException;
	
	List<Meal> readAllMeal() throws FoodServiceException;
}
