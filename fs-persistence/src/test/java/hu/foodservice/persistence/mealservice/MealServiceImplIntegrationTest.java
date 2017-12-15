package hu.foodservice.persistence.mealservice;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import hu.foodservice.persistence.entity.Meal;
import hu.foodservice.persistence.exception.FoodServiceException;
import hu.foodservice.persistence.mealservice.MealServiceImpl;

public class MealServiceImplIntegrationTest {

	private static final String PERSISTENCE_UNIT = "fs-persistence-test-unit";
	private MealServiceImpl object;
	
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
		
		this.object = new MealServiceImpl();
		this.object.setEm(em);
	}
	
	@Test(groups="integration")
	private void readMealByName() throws FoodServiceException {
		Meal meal = object.readMeal("gulash");
		assertMeal(meal, "gulash", "traditional hungarian soup", false, 500);
	}
	
	@Test(groups="integration")
	private void addMealByName() throws FoodServiceException {
		String testMealName = "TestMealName";
		if(this.object.existsMeal(testMealName)) {
			this.object.getEm().getTransaction().begin();
			this.object.deleteMeal(testMealName);
			this.object.getEm().getTransaction().commit();
		}
		
		this.object.getEm().getTransaction().begin();
		this.object.addMeal(testMealName, "TestMealDescription", true, 178);
		this.object.getEm().getTransaction().commit();
		
		Meal meal = this.object.readMeal(testMealName);
		assertMeal(meal, testMealName, "TestMealDescription", true, 178);
		
		this.object.getEm().getTransaction().begin();
		this.object.deleteMeal(testMealName);
		this.object.getEm().getTransaction().commit();
	}
	
	private void assertMeal(Meal meal, String mealName, String mealDescription, Boolean isAllergic, Integer price) {
		Assert.assertEquals(meal.getDescription(), mealDescription);
		Assert.assertEquals(meal.getName(), mealName);
		Assert.assertEquals(meal.getIsAllergic(), isAllergic);
		Assert.assertEquals(meal.getPrice(), price);
	}
}
