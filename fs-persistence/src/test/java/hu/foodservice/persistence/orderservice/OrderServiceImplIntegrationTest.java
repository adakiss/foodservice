package hu.foodservice.persistence.orderservice;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import hu.foodservice.persistence.customerservice.CustomerServiceImpl;
import hu.foodservice.persistence.entity.Customer;
import hu.foodservice.persistence.entity.CustomerOrder;
import hu.foodservice.persistence.entity.Menu;
import hu.foodservice.persistence.exception.FoodServiceException;
import hu.foodservice.persistence.menuservice.MenuServiceImpl;
import hu.foodservice.persistence.orderservice.OrderServiceImpl;

public class OrderServiceImplIntegrationTest {
	
	private static final String PERSISTENCE_UNIT = "fs-persistence-test-unit";
	private OrderServiceImpl object;
	private CustomerServiceImpl customerService;
	private MenuServiceImpl menuService;
	
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
		
		this.object = new OrderServiceImpl();
		this.object.setEm(em);
		this.menuService = new MenuServiceImpl();
		this.menuService.setEm(em);
		this.customerService = new CustomerServiceImpl();
		this.customerService.setEm(em); //TODO - mock out these dependencies
	}
	
	@Test(groups="integration")
	private void readCustOrderById() throws FoodServiceException {
		CustomerOrder custOrder = object.readCustOrder("T0");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			assertCustomerOrder(custOrder, "T0", menuService.readMenu("A menu"), new Date(format.parse("2017-12-02").getTime()), customerService.readCustomer("Adam Kidd"));
		} catch (ParseException e) {
			Logger.getLogger(getClass()).error("Failed to parse the string to the date");
		}	
	}
	
	@Test(groups="integration")
	private void readCustOrderByOrderCode() throws FoodServiceException {
		CustomerOrder custOrder = object.readCustOrder("T0");
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			assertCustomerOrder(custOrder, "T0", menuService.readMenu("A menu"), new Date(format.parse("2017-12-02").getTime()), customerService.readCustomer("Adam Kidd"));
		} catch (ParseException e) {
			Logger.getLogger(getClass()).error("Failed to parse the string to the date");
		}	
	}
	
	@Test(groups="integration")
	private void updateOrder() throws FoodServiceException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			this.object.getEm().getTransaction().begin();
			this.object.updateOrder("T0", new Date(format.parse("2017-11-30").getTime()), true);
			assertCustomerOrder(this.object.readCustOrder("T0"), "T0", menuService.readMenu("A menu"), new Date(format.parse("2017-11-30").getTime()), this.customerService.readCustomer("Adam Kidd"));
			this.object.getEm().getTransaction().rollback();
		} catch (ParseException e) {
			Logger.getLogger(getClass()).error("Failed to parse the string to the date");
		}
	}
	
	@Test(groups="integration")
	private void addNewOrder() throws FoodServiceException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String testOrderCode = "AAA666";
		Date testDate = new Date(1);
		try {
			testDate.setTime(format.parse("2017-11-30").getTime());
		} catch (ParseException e) {
			Logger.getLogger(getClass()).error("Failed to parse the string to the date");
		}
		Customer cust = customerService.readCustomer("Adam Kidd");
		Menu menu = menuService.readMenu("A menu");
		
		if (!this.object.existsOrder(testOrderCode)) {
			this.object.getEm().getTransaction().begin();
			this.object.addNewOrder(cust, testDate, menu, testOrderCode);
			this.object.getEm().getTransaction().commit();
		}
		CustomerOrder myOrder = this.object.readCustOrder(testOrderCode);
		assertCustomerOrder(myOrder, testOrderCode, menu, testDate, cust);
		if (this.object.existsOrder(testOrderCode)) {
			this.object.getEm().getTransaction().begin();
			this.object.deleteOrder(testOrderCode);
			this.object.getEm().getTransaction().commit();
		}
		
	}
	
	private void assertCustomerOrder(CustomerOrder custOrder, String orderCode, Menu orderedMenu, Date deadLine, Customer buyer) {
		Assert.assertEquals(custOrder.getOrderCode(), orderCode);
		Assert.assertEquals(custOrder.getBuyer(), buyer);
		Assert.assertEquals(custOrder.getDeadLine(), deadLine);
		Assert.assertEquals(custOrder.getOrderedMenu(), orderedMenu);
	}
	
	
	
	@AfterClass
	private void tearDown() {
		this.object.getEm().close();
	}
	
}
