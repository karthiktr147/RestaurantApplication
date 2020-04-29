package com.mindtree.restaurantapp.service;

import java.io.IOException;
import java.util.List;


import com.mindtree.restaurantapp.entity.Block;
import com.mindtree.restaurantapp.entity.Shop;
import com.mindtree.restaurantapp.exception.service.RestaurantAppServiceexception;

public interface ShopService {

	public Block addShopToBlock(Shop shops, int blockId) throws  RestaurantAppServiceexception;
	
	public List<Shop> getAllShopsByType(String type)throws RestaurantAppServiceexception;
	
	public Shop seralizingTheShopData(Shop shop) throws IOException;
	public Shop deSerializingTheShopData() throws IOException,ClassNotFoundException;

}
