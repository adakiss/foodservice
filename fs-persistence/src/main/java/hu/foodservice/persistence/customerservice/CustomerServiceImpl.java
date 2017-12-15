package hu.foodservice.persistence.customerservice;

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
import hu.foodservice.persistence.exception.FoodServiceException;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CustomerServiceImpl implements CustomerService {
	
	private static final Logger LOGGER = Logger.getLogger(CustomerServiceImpl.class);
	
	private static final String CUSTOMER_GET_BY_NAME = "Customer.getByName";
	private static final String CUSTOMER_GET_ALL = "Customer.getAll";
	private static final String CUSTOMER_COUNT_BY_EMAIL = "Customer.countByEmail";
	private static final String CUSTOMER_GET_BY_EMAIL = "Customer.getByEmail";
	private static final String CUSTOMER_DELETE_BY_EMAIL = "Customer.deleteByEmail";
	
	@PersistenceContext(unitName="fs-persistence-unit")
	private EntityManager em;

	@Override
	public Customer readCustomer(String name) throws FoodServiceException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Read customer with ordercode: " + name);
		}
		
		Customer result = null;
		
		try {
			result = em.createNamedQuery(CUSTOMER_GET_BY_NAME, Customer.class).setParameter("name", name).getSingleResult();
		} catch (final Exception e) {
			LOGGER.error("Error occured during the execution of the named query");
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
		
		return result;
	}

	@Override
	public List<Customer> readAllCustomer() throws FoodServiceException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Read all customer");
		}
		
		List<Customer> result = null;
		
		try {
			result = em.createNamedQuery(CUSTOMER_GET_ALL, Customer.class).getResultList();
		} catch (final Exception e) {
			LOGGER.error("Error occured during the execution of the named query");
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
		
		return result;
	}
	
	public void setEm(EntityManager em) {
		this.em = em;
	}

	protected EntityManager getEm() {
		return em;
	}

	@Override
	public Boolean existsCustomer(String email) throws FoodServiceException {
		
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Counting customers with email: " + email);
		}
		
		try {
			return (em.createNamedQuery(CUSTOMER_COUNT_BY_EMAIL, Long.class).setParameter("email", email).getSingleResult() == 1);
		} catch (Exception e) {
			LOGGER.error("Error during checking a customer in the database");
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
	}

	@Override
	public Customer readCustomerByEmail(String email) throws FoodServiceException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Read customer with email: " + email);
		}
		
		Customer result = null;
		
		try {
			result = em.createNamedQuery(CUSTOMER_GET_BY_EMAIL, Customer.class).setParameter("email", email).getSingleResult();
		} catch (final Exception e) {
			LOGGER.error("Error occured during the execution of the named query");
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
		
		return result;
	}

	@Override
	public void deleteCustomer(String email) throws FoodServiceException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Deleting customer with email: " + email);
		}
		
		try {
			em.createNamedQuery(CUSTOMER_DELETE_BY_EMAIL).setParameter("email", email).executeUpdate();
		} catch (final Exception e) {
			LOGGER.error("Error occured during the execution of the named query");
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
	}

	@Override
	public Customer addNewCustomer(String name, Integer tel, String address, String email) throws FoodServiceException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Adding new customer: " + email);
		}
		
		Customer c = new Customer();
		
		try {
			c.setName(name);
			c.setEmail(email);
			c.setAddress(address);
			c.setPhone(tel);
			
			this.em.persist(c);
			this.em.flush();
		} catch (Exception e) {
			LOGGER.error("Error occured during adding new customer");
			throw new FoodServiceException(e.getMessage(), this.getClass().getName());
		}
		
		return c;
	}
}
