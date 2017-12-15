package hu.foodservice.ejb.orderfacade;

import java.util.Date;

import hu.foodservice.ejb.customerfacade.CustomerStub;
import hu.foodservice.ejb.menufacade.MenuStub;

public class CustomerOrderStub {

	private String orderCode;
	
	private CustomerStub buyer;
	
	private MenuStub orderedMenu;
	
	private Date deadLine;
	
	private String status;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public CustomerStub getBuyer() {
		return buyer;
	}

	public void setBuyer(CustomerStub buyer) {
		this.buyer = buyer;
	}

	public MenuStub getOrderedMenu() {
		return orderedMenu;
	}

	public void setOrderedMenu(MenuStub orderedMenu) {
		this.orderedMenu = orderedMenu;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CustomerOrderData: " + this.orderCode + ", " + this.buyer + ", " + this.orderedMenu + ", " + this.deadLine.toString() + ", " + this.status; 
	}

	
}
