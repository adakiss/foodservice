package hu.foodservice.persistence.exception;

public class FoodServiceException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	String thrower;
	
	public FoodServiceException(String msg, String thrower) {
		super(msg);
		this.thrower = thrower;
	}

}