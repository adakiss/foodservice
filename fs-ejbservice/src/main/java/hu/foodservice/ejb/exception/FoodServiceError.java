package hu.foodservice.ejb.exception;

import javax.ws.rs.core.Response.Status;

public enum FoodServiceError {
	
	UNEXPECTED(10, Status.INTERNAL_SERVER_ERROR, "Unexpected error"),
	NOT_EXISTS(40, Status.BAD_REQUEST, "Resource not found"),
	RESOURCE_EXISTS(50, Status.PRECONDITION_FAILED, "Resource already exists"),
	INVALID_FORMAT(60, Status.NOT_ACCEPTABLE, "Invalid format on input");
	
	private final int code;
	private final Status httpStatus;
	private final String message;
	
	private FoodServiceError(int code, Status httpStatus, String message) {
		this.code = code;
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public Status getHttpStatus() {
		return httpStatus;
	}
	
	public int getHttpStatusCode() {
		return httpStatus.getStatusCode();
	}
	
	public ErrorStub build(String fields) {
		return new ErrorStub(code, message, fields);
	}

}
