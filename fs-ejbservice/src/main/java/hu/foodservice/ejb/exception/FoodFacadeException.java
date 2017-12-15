package hu.foodservice.ejb.exception;

import javax.ws.rs.core.Response.Status;

public class FoodFacadeException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private final FoodServiceError error;
	private final String fields;
	
	public FoodFacadeException(FoodServiceError error, String message) {
		this(error, message, null);
	}
	
	public FoodFacadeException(FoodServiceError error, String message, String fields) {
		this(error, message, null, fields);
	}
	
	public FoodFacadeException(FoodServiceError error, String message, Throwable cause, String fields) {
		super(message, cause);
		this.error = error;
		this.fields = fields;
	}

	public Status getHttpStatus() {
		return this.error.getHttpStatus();
	}
	
	public ErrorStub build() {
		return this.error.build(fields);
	}
}
