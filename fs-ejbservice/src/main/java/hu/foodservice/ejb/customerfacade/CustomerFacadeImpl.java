package hu.foodservice.ejb.customerfacade;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import hu.foodservice.ejb.converter.CustomerConverter;
import hu.foodservice.ejb.exception.FoodFacadeException;
import hu.foodservice.ejb.exception.FoodServiceError;
import hu.foodservice.persistence.customerservice.CustomerService;
import hu.foodservice.persistence.entity.Customer;
import hu.foodservice.persistence.exception.FoodServiceException;

@Stateless
public class CustomerFacadeImpl implements CustomerFacade {
	
	private static final Logger LOGGER = Logger.getLogger(CustomerFacadeImpl.class);
	
	@EJB
	private CustomerService service;
	
	@EJB
	private CustomerConverter customerConverter;
	
	@Override
	public List<CustomerStub> getAllCustomers() throws FoodFacadeException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get all CustomerStubs");
		}
		
		List<CustomerStub> custStubs = new ArrayList<CustomerStub>();
		
		List<Customer> custs;
		try {
			custs = service.readAllCustomer();
		} catch (FoodServiceException e) {
			throw new FoodFacadeException(FoodServiceError.UNEXPECTED, e.getLocalizedMessage());
		}
		Iterator<Customer> it = custs.iterator();
		while(it.hasNext()) {
			custStubs.add(customerConverter.convert(it.next()));
		}
		
		return custStubs;
	}
	
	@Override
	public CustomerStub getCustomer(String name) throws FoodFacadeException {
		
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Get CustomerStub with name: " + name);
		}
		
		CustomerStub customer;
		try {
			customer = customerConverter.convert(service.readCustomer(name));
		} catch (FoodServiceException e) {
			throw new FoodFacadeException(FoodServiceError.UNEXPECTED, e.getLocalizedMessage());
		}
		
		return customer;
	}

	@Override
	public CustomerStub addCustomer(String name, String email, String address, Integer tel) throws FoodFacadeException {
		
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Adding new customer: " + name);
		}
		
		CustomerStub c = new CustomerStub();
		
		try {
			if(!service.existsCustomer(email)) {
				c = customerConverter.convert(service.addNewCustomer(name, tel, address, email));
			} else {
				throw new FoodFacadeException(FoodServiceError.RESOURCE_EXISTS, "Customer with this email already exists", email);
			}
		} catch (FoodServiceException e) {
			throw new FoodFacadeException(FoodServiceError.UNEXPECTED, e.getLocalizedMessage());
		}
		
		return c;
	}

	@Override
	public void deleteCustomer(String email) throws FoodFacadeException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Deleting customer: " + email);
		}
		
		try {
			if (service.existsCustomer(email)) {
				service.deleteCustomer(email);
			} else {
				throw new FoodFacadeException(FoodServiceError.NOT_EXISTS, "Customer to delete does not exists", email);
			}
		} catch (FoodServiceException e) {
			throw new FoodFacadeException(FoodServiceError.UNEXPECTED, e.getLocalizedMessage());
		}
	}
	
}
