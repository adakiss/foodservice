package hu.foodservice.ejb.mealfacade;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import hu.foodservice.ejb.converter.MealConverter;
import hu.foodservice.ejb.exception.FoodFacadeException;
import hu.foodservice.ejb.exception.FoodServiceError;
import hu.foodservice.persistence.entity.Meal;
import hu.foodservice.persistence.exception.FoodServiceException;
import hu.foodservice.persistence.mealservice.MealService;

@Stateless
public class MealFacadeImpl implements MealFacade {
	
	private static final Logger LOGGER = Logger.getLogger(MealFacadeImpl.class);
	
	@EJB
	private MealService service;
	
	@EJB
	private MealConverter mealConverter;
	
	@Override
	public MealStub getMeal(String name) throws FoodFacadeException {
		
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get MealStub with name: " + name);
		}
		
		MealStub meal;
		try {
			meal = mealConverter.convert(service.readMeal(name));
		} catch (FoodServiceException e) {
			throw new FoodFacadeException(FoodServiceError.UNEXPECTED, e.getLocalizedMessage());
		}
		
		return meal;
	}
	
	@Override
	public List<MealStub> getAllMeals() throws FoodFacadeException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get all MealStubs");
		}
		
		List<MealStub> mealStubs = new ArrayList<MealStub>();
		
		List<Meal> meals;
		try {
			meals = service.readAllMeal();
		} catch (FoodServiceException e) {
			throw new FoodFacadeException(FoodServiceError.UNEXPECTED, e.getLocalizedMessage());
		}
		Iterator<Meal> it = meals.iterator();
		while(it.hasNext()) {
			mealStubs.add(mealConverter.convert(it.next()));
		}
		
		return mealStubs;
	}
	
	@Override
	public MealStub addMeal(String name, String description, Boolean isAllergic, Integer price) throws FoodFacadeException {
		
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Adding new meal");
		}
		
		MealStub meal = null;
		try {
			if(!service.existsMeal(name)) {
				service.addMeal(name, description, isAllergic, price);
			} else {
				throw new FoodFacadeException(FoodServiceError.RESOURCE_EXISTS, "Meal already exists", name);
			}
			meal = mealConverter.convert(service.readMeal(name));
		} catch (FoodServiceException e) {
			throw new FoodFacadeException(FoodServiceError.UNEXPECTED, e.getLocalizedMessage());
		}
		
		
		return meal;
	}
	
	@Override
	public void removeMeal(String name) throws FoodFacadeException {
		
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Deleting a menu");
		}
		
		try {
			if(service.existsMeal(name)) {
				service.deleteMeal(name);
			} else {
				throw new FoodFacadeException(FoodServiceError.NOT_EXISTS, "Meal to delete does not exists", name);
			}
		} catch (FoodServiceException e) {
			throw new FoodFacadeException(FoodServiceError.UNEXPECTED, e.getLocalizedMessage());
		}
	}

}
