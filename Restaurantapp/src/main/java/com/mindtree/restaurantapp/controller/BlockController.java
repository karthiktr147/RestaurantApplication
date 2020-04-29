package com.mindtree.restaurantapp.controller;

import java.io.IOException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mindtree.restaurantapp.dto.BlockDto;
import com.mindtree.restaurantapp.dto.ResponseBody;
import com.mindtree.restaurantapp.entity.Block;
import com.mindtree.restaurantapp.exception.RestaurantAppException;
import com.mindtree.restaurantapp.service.impl.BlockServiceImpl;

@RestController
@RequestMapping("/block")
public class BlockController {

	@Autowired
	private BlockServiceImpl service;

	@RequestMapping(method = RequestMethod.POST,path = "/addblock")
	public ResponseEntity<?> addblock(@Valid @RequestBody BlockDto block) throws RestaurantAppException, IOException {
		Block block2 = service.addblock(block);
		String data = service.writeExcelBlock(block2,"D:\\Restaurant.xlsx");
		return new ResponseEntity<ResponseBody<Block>>(
				new ResponseBody<Block>(block2, null, "Block Added Successfully, " + data, true), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET,path = "/getBlock")
	public ResponseEntity<?> getBlock() throws RestaurantAppException, IOException {
		Block block = service.readExcelBlock("D:\\Restaurant.xlsx");
		return new ResponseEntity<ResponseBody<Block>>(
				new ResponseBody<Block>(block, null, "Block data successfully read from excel file", true),
				HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.GET,path = "/readandwritetoexcel")
	public ResponseEntity<?> readFromOneFileAndWriteToAnotherFile(@RequestParam String source,@RequestParam String destination) throws RestaurantAppException, IOException {
		Block block = service.readExcelBlock(source);
        service.writeExcelBlock(block,destination);
		return new ResponseEntity<ResponseBody<Block>>(
				new ResponseBody<Block>(block, null, "Block data successfully read from excel file and written to another excel file", true),
				HttpStatus.OK);
	}

}
