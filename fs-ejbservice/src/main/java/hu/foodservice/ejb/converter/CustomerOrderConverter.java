package hu.foodservice.ejb.converter;

import javax.ejb.Local;

import hu.foodservice.ejb.orderfacade.CustomerOrderStub;
import hu.foodservice.persistence.entity.CustomerOrder;

@Local
public interface CustomerOrderConverter {
	
	CustomerOrderStub convert(CustomerOrder custOrder);
	
}
