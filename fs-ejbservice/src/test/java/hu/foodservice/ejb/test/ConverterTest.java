package hu.foodservice.ejb.test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import hu.foodservice.ejb.converter.CustomerConverter;
import hu.foodservice.ejb.converter.CustomerConverterImpl;
import hu.foodservice.ejb.converter.CustomerOrderConverterImpl;
import hu.foodservice.ejb.converter.MealConverter;
import hu.foodservice.ejb.converter.MealConverterImpl;
import hu.foodservice.ejb.converter.MenuConverter;
import hu.foodservice.ejb.converter.MenuConverterImpl;
import hu.foodservice.ejb.customerfacade.CustomerStub;
import hu.foodservice.ejb.mealfacade.MealStub;
import hu.foodservice.ejb.menufacade.MenuStub;
import hu.foodservice.ejb.orderfacade.CustomerOrderStub;
import hu.foodservice.persistence.entity.Customer;
import hu.foodservice.persistence.entity.CustomerOrder;
import hu.foodservice.persistence.entity.Meal;
import hu.foodservice.persistence.entity.Menu;
import hu.foodservice.persistence.trunk.OrderStatus;

public class ConverterTest {
	
	CustomerConverter custConverter;
	CustomerOrderConverterImpl custOrderConverter;
	MealConverter mealConverter;
	MenuConverter menuConverter;
	
	//@Mock
	private Customer cust;
	
	//@Mock
	private CustomerOrder custOrder;
	
	//@Mock
	private Meal meal;
	
	//@Mock
	private Menu menu;
	
	@BeforeMethod
	public void beforeTest() {
		//MockitoAnnotations.initMocks(this);
		
		/*when(cust.getName()).thenReturn("TestCustomerName");
		when(cust.getPhone()).thenReturn(1);
		when(cust.getAddress()).thenReturn("TestCustomerAddress");
		
		when(meal.getDescription()).thenReturn("TestMealDescription");
		when(meal.getIsAllergic()).thenReturn(true);
		when(meal.getName()).thenReturn("TestMealName");
		when(meal.getPrice()).thenReturn(1);
		
		when(menu.getIsGeneric()).thenReturn(true);
		when(menu.getName()).thenReturn("TestMenuName");
		when(menu.getOrderedMeals()).thenReturn(null);
		when(menu.getPrice()).thenReturn(1);
		
		when(custOrder.getBuyer()).thenReturn(cust);
		when(custOrder.getDeadLine()).thenReturn(new Date(0));
		when(custOrder.getOrderCode()).thenReturn("TestCustomerOrderOrderCode");
		when(custOrder.getOrderedMenu()).thenReturn(menu);
		*/
		
		cust=new Customer();
		meal = new Meal();
		menu = new Menu();
		custOrder = new CustomerOrder();
		
		cust.setName("TestCustomerName");
		cust.setPhone(1);
		cust.setAddress("TestCustomerAddress");
		cust.setEmail("test@test.test");
		
		meal.setDescription("TestMealDescription");
		meal.setIsAllergic(true);
		meal.setName("TestMealName");
		meal.setPrice(1);
		
		menu.setIsGeneric(true);
		menu.setName("TestMenuName");
		menu.setOrderedMeals(null);
		menu.setPrice(1);
		
		custOrder.setBuyer(cust);
		custOrder.setDeadLine(new Date(0));
		custOrder.setOrderCode("TestCustomerOrderOrderCode");
		custOrder.setOrderedMenu(menu);
		custOrder.setStatus(OrderStatus.COMPLETED);
		
		mealConverter = new MealConverterImpl();
		menuConverter = new MenuConverterImpl();
		custConverter = new CustomerConverterImpl();
		custOrderConverter = new CustomerOrderConverterImpl();
		
		custOrderConverter.setMenuConverter(menuConverter);
		custOrderConverter.setCustomerConverter(custConverter);
	}
	
	@Test
	public void convertCustomerTest() {
		CustomerStub custStub = custConverter.convert(cust);
		
		assertCustomer(custStub, cust.getName(), cust.getAddress(), cust.getPhone(), cust.getEmail());
		
		/*Assert.assertEquals(custStub.getCustName(), "TestCustomerName");
		Assert.assertEquals(custStub.getCustAddress(), "TestCustomerAddress");
		Assert.assertEquals(custStub.getCustTelNumber(), (Integer)1);
		Assert.assertEquals(custStub.getCustEmail(), "test@test.test");*/
	}
	
	@Test
	public void convertCustomerOrderTest() {
		CustomerOrderStub custOrderStub = custOrderConverter.convert(custOrder);
		
		assertCustomerOrder(custOrderStub, custOrder.getOrderCode(), custConverter.convert(custOrder.getBuyer()), menuConverter.convert(custOrder.getOrderedMenu()), custOrder.getDeadLine(), custOrder.getStatus().toString());
		
		/*Assert.assertEquals(custOrderStub.getBuyer().getCustName(), "TestCustomerName");
		Assert.assertEquals(custOrderStub.getOrderCode(), "TestCustomerOrderOrderCode");
		Assert.assertEquals(custOrderStub.getOrderedMenu().getMenuName(), "TestMenuName");
		Assert.assertEquals(custOrderStub.getDeadLine(), new Date(0));
		Assert.assertEquals(custOrderStub.getStatus(), "COMPLETED");*/
	}
	
	@Test
	public void convertMealTest() {
		MealStub mealStub = mealConverter.convert(meal);
		
		assertMeal(mealStub, meal.getName(), meal.getIsAllergic(), meal.getPrice(), meal.getDescription());
		
		/*Assert.assertEquals(mealStub.getMealDescription(), "TestMealDescription");
		Assert.assertEquals(mealStub.getMealName(), "TestMealName");
		Assert.assertTrue(mealStub.getIsAllergic());
		Assert.assertEquals(mealStub.getPrice(), (Integer)1);*/
	}
	
	@Test
	public void convertMenuTest() {
		MenuStub menuStub = menuConverter.convert(menu);
		
		assertMenu(menuStub, menu.getName(), menu.getIsGeneric(), menu.getPrice(), new ArrayList<String>());
		
		/*Assert.assertEquals(menuStub.getMenuName(), "TestMenuName");
		Assert.assertEquals(menuStub.getIncludedMeals(), new ArrayList<String>());
		Assert.assertEquals(menuStub.getPrice(), (Integer)1);
		Assert.assertTrue(menu.getIsGeneric());*/
	}
	
	private void assertCustomer(CustomerStub stub, String custName, String custAddress, Integer custTel, String custEmail) {
		Assert.assertEquals(stub.getCustName(), custName);
		Assert.assertEquals(stub.getCustAddress(), custAddress);
		Assert.assertEquals(stub.getCustTelNumber(), custTel);
		Assert.assertEquals(stub.getCustEmail(), custEmail);
	}
	
	private void assertMeal(MealStub stub, String mealName, Boolean isAllergic, Integer price, String mealDescription) {
		Assert.assertEquals(stub.getMealName(), mealName);
		Assert.assertTrue(stub.getIsAllergic());
		Assert.assertEquals(stub.getPrice(), price);
		Assert.assertEquals(stub.getMealDescription(), mealDescription);
	}
	
	private void assertMenu(MenuStub stub, String menuName, Boolean isGeneric, Integer price, List<String> meals) {
		Assert.assertEquals(stub.getMenuName(), menuName);
		Assert.assertTrue(stub.getIsGeneric());
		Assert.assertEquals(stub.getPrice(), price);
		Assert.assertEquals(stub.getIncludedMeals(), meals);
	}
	
	private void assertCustomerOrder(CustomerOrderStub stub, String orderCode, CustomerStub buyer, MenuStub orderedMenu, Date deadLine, String status) {
		Assert.assertEquals(stub.getOrderCode(), orderCode);
		assertCustomer(stub.getBuyer(), cust.getName(), cust.getAddress(), cust.getPhone(), cust.getEmail());
		assertMenu(stub.getOrderedMenu(), menu.getName(), menu.getIsGeneric(), menu.getPrice(), new ArrayList<String>());
		Assert.assertEquals(stub.getDeadLine(), deadLine);
		Assert.assertEquals(stub.getStatus(), status);
	}
}
