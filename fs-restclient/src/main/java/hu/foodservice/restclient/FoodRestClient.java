package hu.foodservice.restclient;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.ClientRequestFactory;
import org.jboss.resteasy.client.ClientResponse;

import hu.foodservice.restclient.domain.OrderStub;

public class FoodRestClient {
	
	private static final Logger LOGGER = Logger.getLogger(FoodRestClient.class.getName());
	
	private static final String SERVICE_CONTEXT_PATH = "/foodservice/api";
	
	private final String host;
	private final Integer port;
	
	public FoodRestClient(String host, Integer port) {
		this.host = host;
		this.port = port;
	}
	
	public List<OrderStub> processAllOrder() {
		
		final URI serviceUri = UriBuilder.fromUri(this.getServicePath()).build();
		final ClientRequestFactory crf = new ClientRequestFactory(serviceUri);
		
		final FoodRemoteRestService api = crf.createProxy(FoodRemoteRestService.class);
		final ClientResponse<List<OrderStub>> response = api.getAllOrders();
		
		LOGGER.info("Response status: " + response.getStatus());
		final MultivaluedMap<String, Object> header = response.getMetadata();
		for (final String key : header.keySet()) {
			LOGGER.info("HEADER - key: " + key + ", value: " + header.get(key));
		}
		
		final List<OrderStub> orders = response.getEntity();
		return orders;
	}
	
	
	private String getServicePath() {
		return "http://" + this.host + ":" + this.port + SERVICE_CONTEXT_PATH;
	}
	
}
