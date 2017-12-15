package hu.foodservice.ejb.converter;

import javax.ejb.Local;

import hu.foodservice.ejb.customerfacade.CustomerStub;
import hu.foodservice.persistence.entity.Customer;

@Local
public interface CustomerConverter {
	
	CustomerStub convert(Customer cust);
	
}
