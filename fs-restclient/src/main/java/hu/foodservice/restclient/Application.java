package hu.foodservice.restclient;

import java.util.List;

import hu.foodservice.restclient.domain.OrderStub;

public class Application {
	
	private static final String HOST = "localhost";
	private static final Integer PORT = 8080;
	
	public static void main(String[] args) {
		FoodRestClient client = new FoodRestClient(HOST, PORT);
		List<OrderStub> order = client.processAllOrder();
		for(OrderStub stub : order) {
			System.out.println(stub.getOrderCode());
		}
	}

}
