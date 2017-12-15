package hu.foodservice.webservice.meal;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import hu.foodservice.ejb.exception.FoodFacadeException;
import hu.foodservice.ejb.mealfacade.MealStub;

@Path("/meal")
public interface MealRestService {
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	List<MealStub> getMeals() throws FoodFacadeException;
}
