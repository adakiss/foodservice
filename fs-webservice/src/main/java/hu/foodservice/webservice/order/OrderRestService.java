package hu.foodservice.webservice.order;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import hu.foodservice.ejb.exception.FoodFacadeException;
import hu.foodservice.ejb.orderfacade.CustomerOrderStub;
import hu.foodservice.ejb.orderfacade.OrderInput;

@Path("/order")
public interface OrderRestService {
	
	@GET
	@Path("/list/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	List<CustomerOrderStub> getOrdersOfCustomer(@PathParam("email") String email) throws FoodFacadeException;
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	List<CustomerOrderStub> getAllOrders() throws FoodFacadeException;
	
	@POST
	@Path("/{email}/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	List<CustomerOrderStub> addOrder(List<OrderInput> order, @PathParam("email") String email) throws FoodFacadeException;
	
	@DELETE
	@Path("/cancel/{ordercode}")
	void cancelOrder(@PathParam("ordercode") String orderCode) throws FoodFacadeException;
}
