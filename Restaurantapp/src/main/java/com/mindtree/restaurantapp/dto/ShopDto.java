package com.mindtree.restaurantapp.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
public class ShopDto {
	
	@NotNull(message = "Shop name cannot be null")
	@Size(min =3 , message = "shopname should contain minimum three characters")
	private String shopName;
	@NotNull(message = "type cannot be null")
	@Size(min =3 , message = "shop type name should contain minimum three characters")
	private String type;
	@Min(value = 1,message = "rating should be gretaer than 1")
	@Max(value=5,message = "rating cannot be greater than 5")
	private float rating;

	public ShopDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShopDto(String shopName, String type, float rating) {
		super();
		this.shopName = shopName;
		this.type = type;
		this.rating = rating;
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
	
}
