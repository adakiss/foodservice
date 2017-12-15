package hu.foodservice.persistence.customerservice;

import java.util.List;

import hu.foodservice.persistence.entity.Customer;
import hu.foodservice.persistence.exception.FoodServiceException;

public interface CustomerService {
	
	Customer readCustomer(String name) throws FoodServiceException;
	
	List<Customer> readAllCustomer() throws FoodServiceException;
	
	Boolean existsCustomer(String email) throws FoodServiceException;
	
	Customer readCustomerByEmail(String email) throws FoodServiceException;
	
	void deleteCustomer(String email) throws FoodServiceException;
	
	Customer addNewCustomer(String name, Integer tel, String address, String email) throws FoodServiceException;
}
