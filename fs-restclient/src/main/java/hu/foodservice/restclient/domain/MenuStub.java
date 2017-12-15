package hu.foodservice.restclient.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "orderedmenu")
public class MenuStub {
	String menuName;
	List<String> includedMeals;
	Integer price;
	Boolean isGeneric;
	
	@XmlElement(name = "menuname")
	public String getMenuName() {
		return menuName;
	}
	@XmlElement(name = "includedmeals")
	public List<String> getIncludedMeals() {
		return includedMeals;
	}
	@XmlElement(name = "price")
	public Integer getPrice() {
		return price;
	}
	@XmlElement(name = "isgeneric")
	public Boolean getIsGeneric() {
		return isGeneric;
	}
	
	public MenuStub(String menuName, List<String> includedMeals, Integer price, Boolean isGeneric) {
		super();
		this.menuName = menuName;
		this.includedMeals = includedMeals;
		this.price = price;
		this.isGeneric = isGeneric;
	}
	
	public MenuStub() {}
}
