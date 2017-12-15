package hu.foodservice.ejb.converter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;

import hu.foodservice.ejb.menufacade.MenuStub;
import hu.foodservice.persistence.entity.Meal;
import hu.foodservice.persistence.entity.Menu;

@Stateless
public class MenuConverterImpl implements MenuConverter {

	@Override
	public MenuStub convert(Menu menu) {
		
		MenuStub ms = new MenuStub();
		
		List<String> incMeals = new ArrayList<String>();
		if(!(menu.getOrderedMeals() == null)) {
			Iterator<Meal> it = menu.getOrderedMeals().iterator();
			while(it.hasNext()) {
				incMeals.add(it.next().getName());
			}
		}	
		
		ms.setIncludedMeals(incMeals);
		ms.setIsGeneric(menu.getIsGeneric());
		ms.setMenuName(menu.getName());
		ms.setPrice(menu.getPrice());
		
		return ms;
	}

}
