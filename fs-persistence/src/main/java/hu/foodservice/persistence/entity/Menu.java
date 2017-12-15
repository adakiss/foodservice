package hu.foodservice.persistence.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="menu")
@NamedQueries({
	@NamedQuery(name="Menu.getById", query="SELECT mn FROM Menu mn WHERE mn.id=:id"),
	@NamedQuery(name="Menu.getByName", query="SELECT mn FROM Menu mn LEFT JOIN FETCH mn.orderedMeals WHERE mn.name=:name"),
	@NamedQuery(name="Menu.getAll", query="SELECT DISTINCT mn FROM Menu mn LEFT JOIN FETCH mn.orderedMeals"),
	@NamedQuery(name="Menu.countByName", query="SELECT COUNT(mn) FROM Menu mn WHERE mn.name=:name"),
	@NamedQuery(name="Menu.deleteByName", query="DELETE FROM Menu mn WHERE mn.name=:name"),
	@NamedQuery(name="Menu.getAllGeneric", query="SELECT DISTINCT mn FROM Menu mn LEFT JOIN FETCH mn.orderedMeals WHERE mn.isGeneric=TRUE")
})
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="generatorMenu", sequenceName="menu_menu_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generatorMenu")
	@Column(name="menu_id", nullable=false)
	private Long id;
	
	@Column(name="menu_name", nullable=false)
	private String name;
	
	@Column(name="menu_isgeneric")
	private Boolean isGeneric;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade = {
			CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH,
			CascadeType.REMOVE
	})
	@JoinTable(
			name="menu_meal",
			joinColumns=@JoinColumn(name="menu_id", referencedColumnName="menu_id"),
			inverseJoinColumns=@JoinColumn(name="meal_id", referencedColumnName="meal_id"))
	private Set<Meal> orderedMeals;
	
	@Column(name="menu_price", nullable=false)
	private Integer price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsGeneric() {
		return isGeneric;
	}

	public void setIsGeneric(Boolean isGeneric) {
		this.isGeneric = isGeneric;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public Set<Meal> getOrderedMeals() {
		return orderedMeals;
	}

	public void setOrderedMeals(Set<Meal> orderedMeals) {
		this.orderedMeals = orderedMeals;
	}
}
