package hu.foodservice.ejb.orderfacade;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import hu.foodservice.ejb.exception.FoodFacadeException;
import hu.foodservice.ejb.exception.FoodServiceError;

public class OrderInput {

	private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	String menuName;
	Date deadLine;
	
	public OrderInput() {

	}
	
	public OrderInput(String menuName, String deadLine) {
		this.menuName = menuName;
		try {
			this.deadLine = new Date(format.parse(deadLine).getTime());
		} catch (ParseException e) {
			//throw new FoodFacadeException(FoodServiceError.INVALID_FORMAT, e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	public String getMenuName() {
		return menuName;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	@Override
	public String toString() {

		return "OrderInput [menu: " + menuName + ", deadline: " + deadLine + "]";
	}
	
}
