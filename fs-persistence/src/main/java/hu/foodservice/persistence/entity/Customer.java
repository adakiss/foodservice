package hu.foodservice.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="customer")
@NamedQueries({
	@NamedQuery(name="Customer.getById", query="SELECT c FROM Customer c WHERE c.id=:id"),
	@NamedQuery(name="Customer.getByName", query="SELECT c FROM Customer c WHERE c.name=:name"),
	@NamedQuery(name="Customer.getAll", query="SELECT c FROM Customer c"),
	@NamedQuery(name="Customer.countByEmail", query="SELECT COUNT(c) FROM Customer c WHERE c.email=:email"),
	@NamedQuery(name="Customer.getByEmail", query="SELECT c FROM Customer c WHERE c.email=:email"),
	@NamedQuery(name="Customer.deleteByEmail", query="DELETE FROM Customer c WHERE c.email=:email")
})
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="generatorCustomer", sequenceName="customer_customer_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generatorCustomer")
	@Column(name="customer_id", nullable=false)
	private Long id;
	
	@Column(name="customer_name", nullable=false)
	private String name;
	
	@Column(name="customer_address", nullable=false)
	private String address;
	
	@Column(name="customer_phone", nullable=false)
	private Integer phone;
	
	@Column(name="customer_email", nullable=false)
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
