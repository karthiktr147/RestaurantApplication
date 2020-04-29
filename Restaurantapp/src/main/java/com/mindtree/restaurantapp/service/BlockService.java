package com.mindtree.restaurantapp.service;



import java.io.IOException;

import com.mindtree.restaurantapp.dto.BlockDto;
import com.mindtree.restaurantapp.entity.Block;
import com.mindtree.restaurantapp.exception.service.RestaurantAppServiceexception;

public interface BlockService {

	public Block addblock(BlockDto block) throws RestaurantAppServiceexception;
	public String writeExcelBlock(Block block,String path) throws RestaurantAppServiceexception, IOException;
	public Block readExcelBlock(String path) throws RestaurantAppServiceexception, IOException;

}
