package hu.foodservice.persistence.orderservice;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import hu.foodservice.persistence.entity.Customer;
import hu.foodservice.persistence.entity.CustomerOrder;
import hu.foodservice.persistence.entity.Menu;
import hu.foodservice.persistence.exception.FoodServiceException;
import hu.foodservice.persistence.trunk.OrderStatus;

@Stateless(mappedName="ejb/orderService")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class OrderServiceImpl implements OrderService {
	
	private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);
	
	private static final String CUSTORDER_GET_BY_ORDERCODE = "CustomerOrder.getByOrderCode";
	private static final String CUSTORDER_GET_ALL = "CustomerOrder.getAll";
	private static final String CUSTORDER_DELETE_BY_CODE = "CustomerOrder.deleteByCode";
	private static final String CUSTORDER_COUNT_BY_CODE = "CustomerOrder.countByCode";
	private static final String CUSTORDER_GET_ALL_BY_CUSTOMER = "CustomerOrder.getAllByCustomer";
	private static final String CUSTORDER_MAX_ID = "CustomerOrder.maxId";
	
	@PersistenceContext(unitName="fs-persistence-unit")
	private EntityManager em;

	@Override
	public CustomerOrder readCustOrder(String orderCode) throws FoodServiceException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Read customerorder with ordercode: " + orderCode);
		}
		
		CustomerOrder result = null;
		
		try {
			result = em.createNamedQuery(CUSTORDER_GET_BY_ORDERCODE, CustomerOrder.class).setParameter("orderCode", orderCode).getSingleResult();
		} catch (final Exception e) {
			LOGGER.error("Error occured during the execution of the named query");
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
		
		return result;
	}

	@Override
	public List<CustomerOrder> readAllCustOrder() throws FoodServiceException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Read all customerorder");
		}
		
		List<CustomerOrder> result = null;
		
		try {
			result = em.createNamedQuery(CUSTORDER_GET_ALL, CustomerOrder.class).getResultList();
		} catch (final Exception e) {
			LOGGER.error("Error occured during the execution of the named query");
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
		
		return result;
	}
	

	@Override
	public CustomerOrder updateOrder(String orderCode, Date deadLine, boolean nextPhase) throws FoodServiceException {
		
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Updating order details for order: " + orderCode);
		}
		
		try {
			CustomerOrder custOrder = readCustOrder(orderCode);
			custOrder.setDeadLine(deadLine);
			if (nextPhase) {
				custOrder.setStatus(OrderStatus.values()[(custOrder.getStatus().ordinal())+1]);
			}
			
			this.em.persist(custOrder);
			this.em.flush();
			
			return custOrder;
		} catch (Exception e) {
			LOGGER.error("Error while updating customer order");
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
	}

	protected void setEm(EntityManager em) {
		this.em = em;
	}

	protected EntityManager getEm() {
		return em;
	}

	@Override
	public CustomerOrder addNewOrder(Customer buyer, Date deadLine, Menu orderedMenu, String orderCode) throws FoodServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Adding new order");
		}
		
		CustomerOrder order = new CustomerOrder();
		order.setBuyer(buyer);
		order.setDeadLine(deadLine);
		order.setOrderedMenu(orderedMenu);
		order.setOrderCode(orderCode);
		order.setStatus(OrderStatus.WAITING);
		
		try {
			this.em.persist(order);
			this.em.flush();
		} catch (Exception e) {
			LOGGER.error("Error during adding new order: " + e.getMessage());
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
		
		return order;
	}

	@Override
	public Boolean existsOrder(String orderCode) throws FoodServiceException {
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Checking order with code: " + orderCode);
		}
		try {
			return (em.createNamedQuery(CUSTORDER_COUNT_BY_CODE, Long.class).setParameter("orderCode", orderCode).getSingleResult() == 1);
		} catch (Exception e) {
			LOGGER.error("Error during checking an order in the database");
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
	}

	@Override
	public void deleteOrder(String orderCode) throws FoodServiceException {
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Deleting order: " + orderCode);
		}
		
		try {
			em.createNamedQuery(CUSTORDER_DELETE_BY_CODE).setParameter("orderCode", orderCode).executeUpdate();
		} catch (Exception e) {
			LOGGER.error("Error while deleting an order");
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
		
	}

	@Override
	public List<CustomerOrder> readAllCustOrderByCustomer(String email) throws FoodServiceException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Read all customerorder by customer: " + email);
		}
		
		List<CustomerOrder> result = null;
		
		try {
			result = em.createNamedQuery(CUSTORDER_GET_ALL_BY_CUSTOMER, CustomerOrder.class).setParameter("email", email).getResultList();
		} catch (final Exception e) {
			LOGGER.error("Error occured during the execution of the named query");
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
		
		return result;
	}

	@Override
	public Long getMaxId() throws FoodServiceException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Reading max id from DB");
		}
		
		Long result = null;
		
		try {
			result = em.createNamedQuery(CUSTORDER_MAX_ID, Long.class).getSingleResult();
		} catch (Exception e) {
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
		
		return result;
	}
}
