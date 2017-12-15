package hu.foodservice.persistence.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import hu.foodservice.persistence.trunk.OrderStatus;

@Entity
@Table(name="customerorder")
@NamedQueries( value={
	@NamedQuery(name="CustomerOrder.getById", query="SELECT co FROM CustomerOrder co WHERE co.id=:id"),
	@NamedQuery(name="CustomerOrder.getByOrderCode", query="SELECT co FROM CustomerOrder co JOIN FETCH co.buyer JOIN FETCH co.orderedMenu mn JOIN FETCH mn.orderedMeals JOIN FETCH co.buyer WHERE co.orderCode=:orderCode"),
	@NamedQuery(name="CustomerOrder.getAll", query="SELECT DISTINCT co FROM CustomerOrder co JOIN FETCH co.buyer JOIN FETCH co.orderedMenu mn JOIN FETCH mn.orderedMeals JOIN FETCH co.buyer"),
	@NamedQuery(name="CustomerOrder.deleteByCode", query="DELETE FROM CustomerOrder co WHERE co.orderCode=:orderCode"),
	@NamedQuery(name="CustomerOrder.countByCode", query="SELECT COUNT(co) FROM CustomerOrder co WHERE co.orderCode=:orderCode"),
	@NamedQuery(name="CustomerOrder.getAllByCustomer", query="SELECT DISTINCT co FROM CustomerOrder co JOIN FETCH co.buyer c JOIN FETCH co.orderedMenu mn JOIN FETCH mn.orderedMeals JOIN FETCH co.buyer WHERE c.email=:email"),
	@NamedQuery(name="CustomerOrder.maxId", query="SELECT MAX(co.id) FROM CustomerOrder co")
})
public class CustomerOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="generatorCustomerOrder", sequenceName="customerorder_customerorder_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generatorCustomerOrder")
	@Column(name="customerorder_id", nullable=false)
	private Long id;
	
	@Column(name="customerorder_ordercode", nullable=false)
	private String orderCode;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customerorder_buyer")
	private Customer buyer;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customerorder_menu")
	private Menu orderedMenu;
	
	@Column(name="customerorder_deadline", nullable=false)
	private Date deadLine;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="customerorder_status", nullable=false)
	private OrderStatus status;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Customer getBuyer() {
		return buyer;
	}

	public void setBuyer(Customer buyer) {
		this.buyer = buyer;
	}

	public Menu getOrderedMenu() {
		return orderedMenu;
	}

	public void setOrderedMenu(Menu orderedMenu) {
		this.orderedMenu = orderedMenu;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public Long getId() {
		return id;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

}
