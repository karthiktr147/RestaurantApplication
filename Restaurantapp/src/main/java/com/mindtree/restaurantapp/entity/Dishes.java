package com.mindtree.restaurantapp.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Table(name = "dishes")
@Entity
public class Dishes {

	@Id
	@Column(name = "dish_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dishId;
	@Column(name = "dish_name", nullable = false)
	private String dishName;
	@Column(name = "price")
	private double price;
	@Column(name = "type", nullable = false)
	private String type;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Shop shops;

	public int getDishId() {
		return dishId;
	}

	public void setDishId(int dishId) {
		this.dishId = dishId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Shop getShops() {
		return shops;
	}

	public void setShops(Shop shops) {
		this.shops = shops;
	}

	public Dishes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dishes(int dishId, String dishName, double price, String type, Shop shops) {
		super();
		this.dishId = dishId;
		this.dishName = dishName;
		this.price = price;
		this.type = type;
		this.shops = shops;
	}

}
