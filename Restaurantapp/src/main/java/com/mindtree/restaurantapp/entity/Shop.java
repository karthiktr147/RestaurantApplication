package com.mindtree.restaurantapp.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "shop")
@Entity
public class Shop implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shop_id")
	private int shopId;
	@Column(name = "shop_name", nullable = false)
	private String shopName;
	@Column(name = "type", nullable = false)
	private String type;
	@Column(name = "rating")
	transient private float rating;
	@JsonProperty
	@ManyToOne(fetch = FetchType.LAZY)
	 private Block blocks;
	@JsonProperty
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "shops")
	 private List<Dishes> dish;

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public Block getBlocks() {
		return blocks;
	}

	public void setBlocks(Block blocks) {
		this.blocks = blocks;
	}

	public List<Dishes> getDish() {
		return dish;
	}

	public void setDish(List<Dishes> dish) {
		this.dish = dish;
	}

	public Shop(int shopId, String shopName, String type, float rating, Block blocks, List<Dishes> dish) {
		super();
		this.shopId = shopId;
		this.shopName = shopName;
		this.type = type;
		this.rating = rating;
		this.blocks = blocks;
		this.dish = dish;
	}

	public Shop() {
		super();
		// TODO Auto-generated constructor stub
	}

}
