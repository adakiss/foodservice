package hu.foodservice.ejb.orderfacade;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import hu.foodservice.ejb.converter.CustomerOrderConverter;
import hu.foodservice.ejb.customerfacade.CustomerStub;
import hu.foodservice.ejb.exception.FoodFacadeException;
import hu.foodservice.ejb.exception.FoodServiceError;
import hu.foodservice.ejb.menufacade.MenuStub;
import hu.foodservice.persistence.customerservice.CustomerService;
import hu.foodservice.persistence.entity.CustomerOrder;
import hu.foodservice.persistence.exception.FoodServiceException;
import hu.foodservice.persistence.menuservice.MenuService;
import hu.foodservice.persistence.orderservice.OrderService;

@Stateless(mappedName = "ejb/orderFacade")
public class OrderFacadeImpl implements OrderFacade {
	
	private static final Logger LOGGER = Logger.getLogger(OrderFacadeImpl.class);
	
	@EJB
	private OrderService service;
	
	@EJB
	private CustomerService custService;
	
	@EJB
	private MenuService menuService;
	
	@EJB
	private CustomerOrderConverter custOrderconverter;

	@Override
	public CustomerOrderStub getCustomerOrder(String orderCode) throws FoodFacadeException {
		
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get CustomerOrderStub with Ordercode: " + orderCode);
		}
		
		CustomerOrderStub custOrder = null;
		try {
			custOrder = custOrderconverter.convert(service.readCustOrder(orderCode));
		} catch (FoodServiceException e) {
			throw new FoodFacadeException(FoodServiceError.UNEXPECTED, e.getLocalizedMessage());
		}
		
		return custOrder;
	}

	@Override
	public List<CustomerOrderStub> getAllCustomerOrders() throws FoodFacadeException {
		
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get all CustomerOrders");
		}
		
		List<CustomerOrderStub> custOrders = new ArrayList<CustomerOrderStub>();
		
		List<CustomerOrder> orders;
		
		try {
			orders = service.readAllCustOrder();
		} catch (FoodServiceException e) {
			throw new FoodFacadeException(FoodServiceError.UNEXPECTED, e.getLocalizedMessage());
		}
		
		Iterator<CustomerOrder> it = orders.iterator();
		while(it.hasNext()) {
			custOrders.add(custOrderconverter.convert(it.next()));
		}
		
		return custOrders;
	}

	@Override
	public CustomerOrderStub updateOrder(String orderCode, Date deadLine, Boolean nextPhase) throws FoodFacadeException {
		
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Update customer order: " + orderCode);
		}
		
		try {
			return custOrderconverter.convert(service.updateOrder(orderCode, deadLine, nextPhase));
		} catch (FoodServiceException e) {
			throw new FoodFacadeException(FoodServiceError.UNEXPECTED, e.getLocalizedMessage());
		}
	}

	@Override
	public CustomerOrderStub addNewOrder(OrderInput input, String email) throws FoodFacadeException {
		
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Adding order");
		}
		
		CustomerOrderStub custOrder = new CustomerOrderStub();
		
		try {
			if(!custService.existsCustomer(email)) {
				throw new FoodFacadeException(FoodServiceError.NOT_EXISTS, "Given customer is not valid", email);
			}
			if(!menuService.existsMenu(input.getMenuName())) {
				throw new FoodFacadeException(FoodServiceError.NOT_EXISTS, "Given menu is not valid", input.getMenuName());
			}
			
			custOrder = custOrderconverter.convert(service.addNewOrder(custService.readCustomerByEmail(email), input.deadLine, menuService.readMenu(input.getMenuName()), email + "-" + service.getMaxId()));
		} catch (FoodServiceException e) {
			throw new FoodFacadeException(FoodServiceError.UNEXPECTED, e.getLocalizedMessage());
		}
		return custOrder;
	}

	@Override
	public void cancelOrder(String orderCode) throws FoodFacadeException {
		
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Cancelling order: " + orderCode);
		}
		
		try {
			if(service.existsOrder(orderCode)) {
				service.deleteOrder(orderCode);
			} else {
				throw new FoodFacadeException(FoodServiceError.NOT_EXISTS, "Given order does not exists", orderCode);
			}
		} catch (FoodServiceException e) {
			throw new FoodFacadeException(FoodServiceError.UNEXPECTED, e.getLocalizedMessage());
		}
	}

	@Override
	public List<CustomerOrderStub> getAllCustomerOrdersByCustomer(String email) throws FoodFacadeException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get all CustomerOrders by customer: " + email);
		}
		
		List<CustomerOrderStub> custOrders = new ArrayList<CustomerOrderStub>();
		
		List<CustomerOrder> orders;
		
		try {
			orders = service.readAllCustOrderByCustomer(email);
		} catch (FoodServiceException e) {
			throw new FoodFacadeException(FoodServiceError.UNEXPECTED, e.getLocalizedMessage());
		}
		
		Iterator<CustomerOrder> it = orders.iterator();
		while(it.hasNext()) {
			custOrders.add(custOrderconverter.convert(it.next()));
		}
		
		return custOrders;
	}
}
