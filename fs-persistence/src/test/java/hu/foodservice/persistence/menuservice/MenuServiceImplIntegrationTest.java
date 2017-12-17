package hu.foodservice.persistence.menuservice;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import hu.foodservice.persistence.entity.Meal;
import hu.foodservice.persistence.entity.Menu;
import hu.foodservice.persistence.exception.FoodServiceException;
import hu.foodservice.persistence.mealservice.MealServiceImpl;

public class MenuServiceImplIntegrationTest {
	
	private static final String PERSISTENCE_UNIT = "fs-persistence-test-unit";
	
	private MenuServiceImpl object;
	private MealServiceImpl mealService;

	@BeforeClass
	private void setUp() {
		
		Thread.currentThread().setContextClassLoader(new ClassLoader() {
			@Override
			public Enumeration<URL> getResources(String arg0) throws IOException {
				if(arg0.equals("META-INF/persistence.xml")) {
					return Collections.enumeration(Arrays.asList(new File("src/test/resources/persistence.xml").toURI().toURL()));
				}
				return super.getResources(arg0);
			} 
		});
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager em = factory.createEntityManager();
		
		this.object = new MenuServiceImpl();
		this.mealService = new MealServiceImpl();
		this.object.setEm(em);
		this.mealService.setEm(em);
	}
	
	@Test(groups="integration")
	private void readMenuByName() throws FoodServiceException {
		
		Menu menu = object.readMenu("A menu");
		Meal[] arr = new Meal[] {
			mealService.readMeal("gulash"),
			mealService.readMeal("pasta with cottage cheese")
		};
		assertMenu(menu, "A menu", arr, true, 1000);
	}
	
	@Test(groups="integration")
	private void addMenuByName() throws FoodServiceException {
		String testMenuName = "TestMenuName";
		if(this.object.existsMenu(testMenuName)) {
			this.object.getEm().getTransaction().begin();
			this.object.deleteMenu(testMenuName);
			this.object.getEm().getTransaction().commit();
		}
		
		Set<Meal> meals = new HashSet<Meal>();
		meals.add(this.mealService.readMeal("gulash"));
		meals.add(this.mealService.readMeal("clear soup"));
		meals.add(this.mealService.readMeal("pasta with cottage cheese"));
		meals.add(this.mealService.readMeal("pizza"));
		
		this.object.getEm().getTransaction().begin();
		this.object.addMenu(testMenuName, meals, 1000, true);
		this.object.getEm().getTransaction().commit();
		
		Menu menu = this.object.readMenu(testMenuName);
		
		Meal[] meals2 = new Meal[4];
		meals.toArray(meals2);
		Arrays.sort(meals2);
		assertMenu(menu, testMenuName, meals2, true, 1000);
		
		this.object.getEm().getTransaction().begin();
		this.object.deleteMenu(testMenuName);
		this.object.getEm().getTransaction().commit();
	}
	
	@Test(groups="integration")
	private void addMenuByName2() throws FoodServiceException {
		String testMenuName = "TestMenuName";
		if(this.object.existsMenu(testMenuName)) {
			this.object.getEm().getTransaction().begin();
			this.object.deleteMenu(testMenuName);
			this.object.getEm().getTransaction().commit();
		}
		
		Set<Meal> meals = new HashSet<Meal>();
		meals.add(this.mealService.readMeal("gulash"));
		meals.add(this.mealService.readMeal("clear soup"));
		meals.add(this.mealService.readMeal("pasta with cottage cheese"));
		meals.add(this.mealService.readMeal("pizza"));
		
		this.object.getEm().getTransaction().begin();
		this.object.addMenu(testMenuName, meals, 1000, false);
		this.object.getEm().getTransaction().commit();
		
		Menu menu = this.object.readMenu(testMenuName);
		
		Meal[] meals2 = new Meal[4];
		meals.toArray(meals2);
		Arrays.sort(meals2);
		assertMenu(menu, testMenuName, meals2, false, 2750);
		
		this.object.getEm().getTransaction().begin();
		this.object.deleteMenu(testMenuName);
		this.object.getEm().getTransaction().commit();
	}
	
	
	private void assertMenu(Menu menu, String name, Meal[] includedMeals, Boolean isGeneric, Integer price) {
		Assert.assertEquals(menu.getName(), name);
		Assert.assertEquals(menu.getIsGeneric(), isGeneric);
		Assert.assertEquals(menu.getPrice(), price);
		
		Object[] actMeals = menu.getOrderedMeals().toArray();
		Arrays.sort(actMeals);
		Assert.assertEquals(actMeals, includedMeals);
	}
}
