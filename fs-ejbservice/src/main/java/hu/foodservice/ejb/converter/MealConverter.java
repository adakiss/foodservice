package hu.foodservice.ejb.converter;

import javax.ejb.Local;

import hu.foodservice.ejb.mealfacade.MealStub;
import hu.foodservice.persistence.entity.Meal;

@Local
public interface MealConverter {
	
	MealStub convert(Meal meal);
	
}
