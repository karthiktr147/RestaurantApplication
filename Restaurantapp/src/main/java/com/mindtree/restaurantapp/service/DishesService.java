package com.mindtree.restaurantapp.service;

import java.util.List;
import com.mindtree.restaurantapp.entity.Dishes;
import com.mindtree.restaurantapp.entity.Shop;
import com.mindtree.restaurantapp.exception.service.RestaurantAppServiceexception;

public interface DishesService {

	public Shop addDishesToShop(int shopId,List<Dishes> dishes) throws RestaurantAppServiceexception;
	public List<Dishes> getAllDishesByShop(int shopId,String type) throws RestaurantAppServiceexception;
	
}
