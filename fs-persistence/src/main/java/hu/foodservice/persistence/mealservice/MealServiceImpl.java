package hu.foodservice.persistence.mealservice;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import hu.foodservice.persistence.entity.Meal;
import hu.foodservice.persistence.exception.FoodServiceException;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class MealServiceImpl implements MealService {
	
	private static final Logger LOGGER = Logger.getLogger(MealServiceImpl.class);
	
	private static final String MEAL_GET_BY_NAME = "Meal.getByName";
	private static final String MEAL_GET_ALL = "Meal.getAll";
	private static final String MEAL_COUNT_BY_NAME = "Meal.countByName";
	private static final String MEAL_DELETE_BY_NAME = "Meal.deleteByName";
	
	@PersistenceContext(unitName="fs-persistence-unit")
	private EntityManager em;
	
	@Override
	public Meal readMeal(String name) throws FoodServiceException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Read meal with ordercode: " + name);
		}
		
		Meal result = null;
		
		try {
			result = em.createNamedQuery(MEAL_GET_BY_NAME, Meal.class).setParameter("name", name).getSingleResult();
		} catch (final Exception e) {
			LOGGER.error("Error occured during the execution of the named query");
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
		
		return result;
	}

	@Override
	public List<Meal> readAllMeal() throws FoodServiceException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Read all meal");
		}
		
		List<Meal> result = null;
		
		try {
			result = em.createNamedQuery(MEAL_GET_ALL, Meal.class).getResultList();
		} catch (final Exception e) {
			LOGGER.error("Error occured during the execution of the named query");
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
		
		return result;
	}

	@Override
	public Meal addMeal(String name, String description, Boolean isAllergic, Integer price) throws FoodServiceException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Adding book: (" + name + ", " + description + ", " + isAllergic + ", " + price + ")");
		}
		
		try {
			Meal meal = new Meal();
			meal.setName(name);
			meal.setDescription(description);
			meal.setIsAllergic(isAllergic);
			meal.setPrice(price);
			
			this.em.persist(meal);
			this.em.flush();
			
			return meal;
		} catch (Exception e) {
			LOGGER.error("Error during adding new meal");
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
	}
	
	@Override
	public Boolean existsMeal(String name) throws FoodServiceException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Counting meals with name: " + name);
		}
		
		try {
			return (em.createNamedQuery(MEAL_COUNT_BY_NAME, Long.class).setParameter("name", name).getSingleResult() == 1);
		} catch (Exception e) {
			LOGGER.error("Error during checking a meal in the database");
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
	}

	@Override
	public void deleteMeal(String name) throws FoodServiceException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Deleting menu with name: " + name);
		}
		
		try {
			em.createNamedQuery(MEAL_DELETE_BY_NAME).setParameter("name", name).executeUpdate();
		} catch (Exception e) {
			LOGGER.error("Error during deleting a meal from the database");
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
		
	}
	
	public void setEm(EntityManager em) {
		this.em = em;
	}

	protected EntityManager getEm() {
		return em;
	}
}
