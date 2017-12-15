package hu.foodservice.webservice.meal;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import hu.foodservice.ejb.exception.FoodFacadeException;
import hu.foodservice.ejb.mealfacade.MealFacade;
import hu.foodservice.ejb.mealfacade.MealStub;

@Stateless
public class MealRestServiceBean implements MealRestService {

	private static final Logger LOGGER = Logger.getLogger(MealRestServiceBean.class);
	
	@EJB
	private MealFacade facade;
	
	@Override
	public List<MealStub> getMeals() throws FoodFacadeException {
		LOGGER.info("Getting all meals");
		return facade.getAllMeals();
	}

}
