package hu.foodservice.persistence.customerservice;

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

import hu.foodservice.persistence.entity.Customer;
import hu.foodservice.persistence.exception.FoodServiceException;

public class CustomerServiceImplIntegrationTest {
	
	private static final String PERSISTENCE_UNIT = "fs-persistence-test-unit";
	private CustomerServiceImpl object;
	
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
		
		this.object = new CustomerServiceImpl();
		this.object.setEm(em);
	}
	
	@Test(groups="integration")
	private void readCustomerByName() throws FoodServiceException {
		Customer cust = object.readCustomer("Adam Kidd");
		assertCustomer(cust, "Adam Kidd", "1066 Budapest, Terez korut 58 fsz. 2", 706225472);
	}
	
	private void assertCustomer(Customer cust, String name, String address, Integer phone) {
		Assert.assertEquals(cust.getAddress(), address);
		Assert.assertEquals(cust.getName(), name);
		Assert.assertEquals(cust.getPhone(), phone);
	}
}
