package hu.foodservice.persistence.orderservice;

import java.sql.Date;
import java.util.List;

import javax.ejb.Local;

import hu.foodservice.persistence.entity.Customer;
import hu.foodservice.persistence.entity.CustomerOrder;
import hu.foodservice.persistence.entity.Menu;
import hu.foodservice.persistence.exception.FoodServiceException;

@Local
public interface OrderService {
	
	CustomerOrder readCustOrder(String orderCode) throws FoodServiceException;
	
	List<CustomerOrder> readAllCustOrder() throws FoodServiceException;
	
	CustomerOrder updateOrder(String orderCode, Date deadLine, boolean nextPhase) throws FoodServiceException;
	
	CustomerOrder addNewOrder(Customer buyer, Date deadLine, Menu orderedMenu, String orderCode) throws FoodServiceException;
	
	Boolean existsOrder(String orderCode) throws FoodServiceException;
	
	void deleteOrder(String orderCode) throws FoodServiceException;
	
	List<CustomerOrder> readAllCustOrderByCustomer(String email) throws FoodServiceException;
	
	Long getMaxId() throws FoodServiceException;
}
