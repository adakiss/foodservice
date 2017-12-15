package hu.foodservice.ejb.customerfacade;

public class CustomerStub {
	
	private String custName;
	
	private Integer custTelNumber;
	
	private String custAddress;
	
	private String custEmail;

	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Integer getCustTelNumber() {
		return custTelNumber;
	}

	public void setCustTelNumber(Integer custTelNumber) {
		this.custTelNumber = custTelNumber;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	
	@Override
	public String toString() {
		return "CustomerData: " + this.custName + ", " + this.custAddress + ", " + this.custTelNumber + ", " + this.custEmail;
	}
}
