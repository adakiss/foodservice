package hu.foodservice.ejb.converter;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import hu.foodservice.ejb.orderfacade.CustomerOrderStub;
import hu.foodservice.persistence.entity.CustomerOrder;

@Stateless
public class CustomerOrderConverterImpl implements CustomerOrderConverter {
	
	@EJB
	private MenuConverter menuConverter;
	
	@EJB
	private CustomerConverter customerConverter;
	
	@Override
	public CustomerOrderStub convert(CustomerOrder custOrder) {
		
		CustomerOrderStub cos = new CustomerOrderStub();
		
		cos.setBuyer(customerConverter.convert(custOrder.getBuyer()));
		cos.setDeadLine(custOrder.getDeadLine());
		cos.setOrderCode(custOrder.getOrderCode());
		cos.setOrderedMenu(menuConverter.convert(custOrder.getOrderedMenu()));
		cos.setStatus(custOrder.getStatus().toString());
		
		return cos;
	}

	public void setMenuConverter(MenuConverter menuConverter) {
		this.menuConverter = menuConverter;
	}

	public void setCustomerConverter(CustomerConverter customerConverter) {
		this.customerConverter = customerConverter;
	}
}
