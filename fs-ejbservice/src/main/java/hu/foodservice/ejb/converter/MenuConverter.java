package hu.foodservice.ejb.converter;

import javax.ejb.Local;

import hu.foodservice.ejb.menufacade.MenuStub;
import hu.foodservice.persistence.entity.Menu;

@Local
public interface MenuConverter {
	
	MenuStub convert(Menu menu);
	
}
