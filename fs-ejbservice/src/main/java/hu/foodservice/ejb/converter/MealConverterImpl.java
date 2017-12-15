package hu.foodservice.ejb.converter;

import javax.ejb.Stateless;

import hu.foodservice.ejb.mealfacade.MealStub;
import hu.foodservice.persistence.entity.Meal;

@Stateless
public class MealConverterImpl implements MealConverter {

	@Override
	public MealStub convert(Meal meal) {
		
		MealStub ms = new MealStub();
		
		ms.setIsAllergic(meal.getIsAllergic());
		ms.setMealDescription(meal.getDescription());
		ms.setMealName(meal.getName());
		ms.setPrice(meal.getPrice());
		
		return ms;
	}

}
