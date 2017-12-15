package hu.foodservice.restclient;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.jboss.resteasy.client.ClientResponse;

import hu.foodservice.restclient.domain.OrderStub;

@Path("/order")
public interface FoodRemoteRestService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list")
	@Wrapped(element = "orders")
	ClientResponse<List<OrderStub>> getAllOrders();
}
