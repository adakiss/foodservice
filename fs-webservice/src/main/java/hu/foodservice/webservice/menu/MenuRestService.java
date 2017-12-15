package hu.foodservice.webservice.menu;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import hu.foodservice.ejb.exception.FoodFacadeException;
import hu.foodservice.ejb.menufacade.MenuInput;
import hu.foodservice.ejb.menufacade.MenuStub;

@Path("/menu")
public interface MenuRestService {

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	List<MenuStub> getMenus() throws FoodFacadeException;
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	MenuStub createMenu(MenuInput input) throws FoodFacadeException;
}
