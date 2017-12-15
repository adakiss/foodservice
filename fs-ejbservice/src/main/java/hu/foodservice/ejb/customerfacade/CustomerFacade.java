package hu.foodservice.ejb.customerfacade;

import java.util.List;

import javax.ejb.Local;

import hu.foodservice.ejb.exception.FoodFacadeException;

@Local
public interface CustomerFacade {
	
	CustomerStub getCustomer(String name) throws FoodFacadeException;
	
	List<CustomerStub> getAllCustomers() throws FoodFacadeException;
	
	CustomerStub addCustomer(String name, String email, String address, Integer tel) throws FoodFacadeException;
	
	void deleteCustomer(String email) throws FoodFacadeException;

}
