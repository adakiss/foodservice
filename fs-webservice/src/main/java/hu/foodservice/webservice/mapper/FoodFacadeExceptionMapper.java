package hu.foodservice.webservice.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import hu.foodservice.ejb.exception.FoodFacadeException;

@Provider
public class FoodFacadeExceptionMapper implements ExceptionMapper<FoodFacadeException> {

	@Override
	public Response toResponse(FoodFacadeException arg0) {
		
		return Response.status(arg0.getHttpStatus()).entity(arg0.build()).type(MediaType.APPLICATION_JSON).build();
	}

}
