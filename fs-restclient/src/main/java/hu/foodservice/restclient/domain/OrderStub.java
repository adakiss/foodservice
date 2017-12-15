package hu.foodservice.restclient.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "order")
public class OrderStub {
	String orderCode;
	CustomerStub buyer;
	MenuStub orderedMenu;
	String deadLine;
	String status;
	
	public OrderStub(String orderCode, CustomerStub buyer, MenuStub orderedMenu, String deadLine, String status) {
		this.orderCode = orderCode;
		this.buyer = buyer;
		this.orderedMenu = orderedMenu;
		this.deadLine = deadLine;
		this.status = status;
	}
	
	public OrderStub() {
		
	}
	
	@XmlElement(name = "ordercode")
	public String getOrderCode() {
		return orderCode;
	}
	
	@XmlElement(name = "deadline")
	public String getDeadLine() {
		return deadLine;
	}
	
	@XmlElement(name = "status")
	public String getStatus() {
		return status;
	}
	
	@XmlElement(name = "buyer")
	public CustomerStub getBuyer() {
		return buyer;
	}
	
	@XmlElement(name = "orderedmenu")
	public MenuStub getOrderedMenu() {
		return orderedMenu;
	}
}
