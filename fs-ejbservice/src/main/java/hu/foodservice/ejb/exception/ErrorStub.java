package hu.foodservice.ejb.exception;

public class ErrorStub {
	
	private int code;
	
	private String message;
	
	private String fields;
	
	public ErrorStub() {
		this(0, null, null);
	}

	public ErrorStub(int code, String message, String fields) {
		this.code = code;
		this.message = message;
		this.fields = fields;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		return "ErrorStub [code=" + code + ", message=" + message + ", fields=" + fields + "]";
	}

}
