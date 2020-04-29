package com.mindtree.restaurantapp.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mindtree.restaurantapp.dto.ResponseBody;
import com.mindtree.restaurantapp.entity.Dishes;
import com.mindtree.restaurantapp.entity.Shop;
import com.mindtree.restaurantapp.exception.RestaurantAppException;
import com.mindtree.restaurantapp.service.DishesService;

@RestController
@RequestMapping("/dishes")
public class DishesController {

	@Autowired
	private DishesService dishesService;

	//@PostMapping("/adddishes/{shopId}")
	@RequestMapping(method = RequestMethod.POST,path ="/adddishestoshop/{shopId}" )
	public ResponseEntity<?> addDishesToShop(@PathVariable int shopId, @RequestBody List<Dishes> dishes)
			throws RestaurantAppException {
		return new ResponseEntity<ResponseBody<Shop>>(new ResponseBody<Shop>(dishesService.addDishesToShop(shopId, dishes),null,"Added Dishes to shop",true), HttpStatus.ACCEPTED);
	}

	@GetMapping(path = "/getdishesbyshop/{shopId}")
	//@RequestMapping(method = RequestMethod.GET,path="/getalldishesbypriceinascending/{shopId}" ,name = "Dishes sorted by price in ascending")
	public ResponseEntity<?> getAllDishesByshopAndTypeasc(@PathVariable int shopId, @RequestParam String type)
			throws RestaurantAppException {
		List<Dishes> dishesList = new ArrayList<Dishes>();
		dishesList = dishesService.getAllDishesByShop(shopId, type);
		dishesList.sort((Dishes d1, Dishes d2) -> (int) (d1.getPrice() - d2.getPrice()));
		return new ResponseEntity<ResponseBody<List<Dishes>>>(
				new ResponseBody<List<Dishes>>(dishesList, null, "the dishes in ascending order", true), HttpStatus.OK);
	}

	//@GetMapping("/getdishesbyshopdesc/{shopId}")
	@RequestMapping(method = RequestMethod.GET ,path ="/getalldishesbypriceindescending/{shopId}")
	public ResponseEntity<?> getAllDishesByshopAndTypedesc(@PathVariable int shopId, @RequestParam String type)
			throws RestaurantAppException {
		List<Dishes> dishesList = new ArrayList<Dishes>();
		dishesList = dishesService.getAllDishesByShop(shopId, type);
		dishesList.sort((Dishes d1, Dishes d2) -> (int) (d2.getPrice() - d1.getPrice()));
		return new ResponseEntity<ResponseBody<List<Dishes>>>(
				new ResponseBody<List<Dishes>>(dishesList, null, "the dishes in descending order", true),
				HttpStatus.OK);
	}

}
