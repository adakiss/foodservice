package hu.foodservice.ejb.orderfacade;

import java.sql.Date;
import java.util.List;

import javax.ejb.Local;

import hu.foodservice.ejb.customerfacade.CustomerStub;
import hu.foodservice.ejb.exception.FoodFacadeException;
import hu.foodservice.ejb.menufacade.MenuStub;

@Local
public interface OrderFacade {
	
	CustomerOrderStub getCustomerOrder(String orderCode) throws FoodFacadeException;
	
	List<CustomerOrderStub> getAllCustomerOrders() throws FoodFacadeException;
	
	CustomerOrderStub updateOrder(String orderCode, Date deadLine, Boolean nextPhase) throws FoodFacadeException;
	
	CustomerOrderStub addNewOrder(OrderInput input, String email) throws FoodFacadeException;
	
	void cancelOrder(String orderCode) throws FoodFacadeException;
	
	List<CustomerOrderStub> getAllCustomerOrdersByCustomer(String email) throws FoodFacadeException;
}
