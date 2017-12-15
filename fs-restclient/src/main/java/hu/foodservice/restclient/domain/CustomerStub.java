package hu.foodservice.restclient.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "buyer")
public class CustomerStub {
	String custName;
	String custEmail;
	String custAddress;
	Integer custTelNumber;
	
	public CustomerStub(String custName, String custEmail, String custAddress, Integer custTelNumber) {
		super();
		this.custName = custName;
		this.custEmail = custEmail;
		this.custAddress = custAddress;
		this.custTelNumber = custTelNumber;
	}
	
	public CustomerStub() {}
	
	@XmlElement(name = "custname")
	public String getCustName() {
		return custName;
	}

	@XmlElement(name = "custemail")
	public String getCustEmail() {
		return custEmail;
	}
	
	@XmlElement(name = "custaddress")
	public String getCustAddress() {
		return custAddress;
	}
	
	@XmlElement(name = "custtelnumber")
	public Integer getCustTelNumber() {
		return custTelNumber;
	}
}
