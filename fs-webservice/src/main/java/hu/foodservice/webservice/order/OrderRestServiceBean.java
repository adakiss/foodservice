package hu.foodservice.webservice.order;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import hu.foodservice.ejb.exception.FoodFacadeException;
import hu.foodservice.ejb.orderfacade.CustomerOrderStub;
import hu.foodservice.ejb.orderfacade.OrderFacade;
import hu.foodservice.ejb.orderfacade.OrderInput;

@Stateless
public class OrderRestServiceBean implements OrderRestService {
	
	private static final Logger LOGGER = Logger.getLogger(OrderRestServiceBean.class);
	
	@EJB
	private OrderFacade facade;

	@Override
	public List<CustomerOrderStub> getOrdersOfCustomer(String email) throws FoodFacadeException {
		LOGGER.info("Getting all orders from customer: " + email);
		return facade.getAllCustomerOrdersByCustomer(email);
	}

	@Override
	public List<CustomerOrderStub> getAllOrders() throws FoodFacadeException {
		LOGGER.info("Getting all orders");
		return facade.getAllCustomerOrders();
	}

	@Override
	public List<CustomerOrderStub> addOrder(List<OrderInput> order, String email) throws FoodFacadeException {
		LOGGER.info("Posting new orders");
		
		ArrayList<CustomerOrderStub> result = new ArrayList<CustomerOrderStub>();
		
		Iterator<OrderInput> it = order.iterator();
		while (it.hasNext()) {
			result.add(facade.addNewOrder(it.next(), email));
		}
		return result;
	}

	@Override
	public void cancelOrder(String orderCode) throws FoodFacadeException {
		LOGGER.info("Deleteing order");
		facade.cancelOrder(orderCode);
	}

}
