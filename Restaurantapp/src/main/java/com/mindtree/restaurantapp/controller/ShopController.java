package com.mindtree.restaurantapp.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mindtree.restaurantapp.dto.ResponseBody;
import com.mindtree.restaurantapp.dto.ShopDto;
import com.mindtree.restaurantapp.entity.Block;
import com.mindtree.restaurantapp.entity.Shop;
import com.mindtree.restaurantapp.exception.RestaurantAppException;
import com.mindtree.restaurantapp.service.impl.ShopServiceImpl;

@RestController
@RequestMapping("/shopsController")
public class ShopController {

	@Autowired
	private ShopServiceImpl service;
	@Autowired
	private ModelMapper modelMapper;
	@RequestMapping(name = "/addshop/",method = RequestMethod.POST,path ="{blockId}" )
	public ResponseEntity<?> addShop(@Valid @RequestBody ShopDto shops, @PathVariable int blockId)
			throws RestaurantAppException, IOException {
		service.seralizingTheShopData(modelMapper.map(shops,Shop.class));
		return new ResponseEntity<ResponseBody<Block>>(new ResponseBody<Block>(service.addShopToBlock(modelMapper.map(shops,Shop.class), blockId),null,"The shop object is serialized",true), HttpStatus.ACCEPTED);
	}

	@RequestMapping(name = "/getshopsbytype/",method = RequestMethod.GET )
	public ResponseEntity<?> getAllShopsByType(@RequestParam String type)throws RestaurantAppException{
		return new ResponseEntity<ResponseBody<List<Shop>>>(new ResponseBody<List<Shop>>(service.getAllShopsByType(type),null,"succeed",true),HttpStatus.OK);
	} 
	@RequestMapping(name="/deserializeShopData/",method = RequestMethod.GET,path = "/deserializeShopData/")
	public ResponseEntity<?> getDeserializedShopData() throws ClassNotFoundException, IOException{
		return new ResponseEntity<ResponseBody<Shop>>(new ResponseBody<Shop>(service.deSerializingTheShopData(),null,"succeed",true),HttpStatus.OK);
		
	}
	
}
