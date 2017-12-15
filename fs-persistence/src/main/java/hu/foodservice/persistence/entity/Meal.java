package hu.foodservice.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="meal")
@NamedQueries({
	@NamedQuery(name="Meal.getById", query="SELECT ml FROM Meal ml WHERE ml.id=:id"),
	@NamedQuery(name="Meal.getByName", query="SELECT ml FROM Meal ml WHERE ml.name=:name"),
	@NamedQuery(name="Meal.getAll", query="SELECT ml FROM Meal ml"),
	@NamedQuery(name="Meal.countByName", query="SELECT count(ml) FROM Meal ml WHERE ml.name=:name"),
	@NamedQuery(name = "Meal.deleteByName", query = "DELETE FROM Meal ml WHERE ml.name=:name")
})
public class Meal implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="generatorMeal", sequenceName="meal_meal_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generatorMeal")
	@Column(name="meal_id", nullable=false)
	private Long id;
	
	@Column(name="meal_name", nullable=false)
	private String name;
	
	@Column(name="meal_description")
	private String description;
	
	@Column(name="meal_isallergic")
	private Boolean isAllergic;
	
	@Column(name="meal_price", nullable=false)
	private Integer price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsAllergic() {
		return isAllergic;
	}

	public void setIsAllergic(Boolean isAllergic) {
		this.isAllergic = isAllergic;
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
	
	
}
