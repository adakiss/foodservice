package hu.foodservice.ejb.converter;

import javax.ejb.Stateless;

import hu.foodservice.ejb.customerfacade.CustomerStub;
import hu.foodservice.persistence.entity.Customer;

@Stateless
public class CustomerConverterImpl implements CustomerConverter {

	@Override
	public CustomerStub convert(Customer cust) {
		
		CustomerStub cs = new CustomerStub();
		
		cs.setCustName(cust.getName());
		cs.setCustAddress(cust.getAddress());
		cs.setCustTelNumber(cust.getPhone());
		cs.setCustEmail(cust.getEmail());
		
		return cs;
	}

}
