package hu.foodservice.ejb.menufacade;

import java.util.List;

public class MenuStub {
	
	private String menuName;
	
	private List<String> includedMeals;
	
	private Integer price;
	
	private Boolean isGeneric;

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public List<String> getIncludedMeals() {
		return includedMeals;
	}

	public void setIncludedMeals(List<String> includedMeals) {
		this.includedMeals = includedMeals;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Boolean getIsGeneric() {
		return isGeneric;
	}

	public void setIsGeneric(Boolean isGeneric) {
		this.isGeneric = isGeneric;
	}

	@Override
	public String toString() {
		String str = "MenuData: " + this.menuName + ", ";
		for(String meal : this.includedMeals) {
			str+=meal + ", ";
		}
		str += this.isGeneric + ", " + this.price;
		
		return str;
	}
}
