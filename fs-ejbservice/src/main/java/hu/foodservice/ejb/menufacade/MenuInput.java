package hu.foodservice.ejb.menufacade;

import java.util.List;
import java.util.Set;

public class MenuInput {
	String menuName;
	List<String> meals;
	
	public MenuInput(String menuName, List<String> meals) {
		this.menuName = menuName;
		this.meals = meals;
	}
	
	public MenuInput() {}

	public String getMenuName() {
		return menuName;
	}

	public List<String> getMeals() {
		return meals;
	}
}