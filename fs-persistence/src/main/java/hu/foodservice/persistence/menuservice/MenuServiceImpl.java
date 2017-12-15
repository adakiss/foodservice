package hu.foodservice.persistence.menuservice;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import hu.foodservice.persistence.entity.Meal;
import hu.foodservice.persistence.entity.Menu;
import hu.foodservice.persistence.exception.FoodServiceException;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class MenuServiceImpl implements MenuService {
	
	private static final Logger LOGGER = Logger.getLogger(MenuServiceImpl.class);
	
	private static final String MENU_GET_BY_NAME = "Menu.getByName";
	private static final String MENU_GET_ALL = "Menu.getAll";
	private static final String MENU_COUNT_BY_NAME = "Menu.countByName";
	private static final String MENU_DELETE_BY_NAME = "Menu.deleteByName";
	private static final String MENU_GET_ALL_GENERIC = "Menu.getAllGeneric";
	
	@PersistenceContext(unitName="fs-persistence-unit")
	private EntityManager em;
	
	@Override
	public Menu readMenu(String name) throws FoodServiceException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Read menu with ordercode: " + name);
		}
		
		Menu result = null;
		
		try {
			result = em.createNamedQuery(MENU_GET_BY_NAME, Menu.class).setParameter("name", name).getSingleResult();
		} catch (final Exception e) {
			LOGGER.error("Error occured during the execution of the named query");
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
		
		return result;
	}

	@Override
	public List<Menu> readAllMenu() throws FoodServiceException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Read all menu");
		}
		
		List<Menu> result = null;
		
		try {
			result = em.createNamedQuery(MENU_GET_ALL, Menu.class).getResultList();
		} catch (final Exception e) {
			LOGGER.error("Error occured during the execution of the named query");
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
		
		return result;
	}
	

	@Override
	public Menu addMenu(String name, Set<Meal> meals, Integer price, Boolean isGeneric) throws FoodServiceException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Creating menu: (" + name + ")");
		}
		
		try {
			Menu menu = new Menu();
			menu.setIsGeneric(isGeneric);
			menu.setName(name);
			menu.setOrderedMeals(meals);
			menu.setPrice(getMenuPrice(menu.getOrderedMeals(), price, isGeneric));
			
			this.em.persist(menu);
			this.em.flush();
			
			return menu;
		} catch (Exception e) {
			LOGGER.error("Error occured during the creation of a new menu");
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
	}

	private Integer getMenuPrice(Set<Meal> set, Integer price, Boolean isGeneric) {
		if (isGeneric) {
			return price;
		} else {
			Integer price2 = 0;
			Iterator<Meal> it = set.iterator();
			while (it.hasNext()) {
				price2+=it.next().getPrice();
			}
			return price2;
		}
	}

	@Override
	public Boolean existsMenu(String name) throws FoodServiceException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Counting menus with name: " + name);
		}
		
		try {
			return (em.createNamedQuery(MENU_COUNT_BY_NAME, Long.class).setParameter("name", name).getSingleResult() == 1);
		} catch (Exception e) {
			LOGGER.error("Error during checking a menu in the database");
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
	}

	@Override
	public void deleteMenu(String name) throws FoodServiceException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Deleting menu with name: " + name);
		}
		
		try {
			em.createNamedQuery(MENU_DELETE_BY_NAME).setParameter("name", name).executeUpdate();
		} catch (Exception e) {
			LOGGER.error("Error during deleting a menu from the database");
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
	}
	
	@Override
	public List<Menu> readAllGenericMenu() throws FoodServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Getting all generic menu");
		}
		
		List<Menu> result = null;
		
		try {
			result = em.createNamedQuery(MENU_GET_ALL_GENERIC, Menu.class).getResultList();
		} catch (final Exception e) {
			LOGGER.error("Error occured during the execution of the named query");
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
		
		return result;
	}
	
	public void setEm(EntityManager em) {
		this.em = em;
	}

	protected EntityManager getEm() {
		return em;
	}
}
