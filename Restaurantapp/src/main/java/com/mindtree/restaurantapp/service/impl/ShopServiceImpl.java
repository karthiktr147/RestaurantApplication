package com.mindtree.restaurantapp.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mindtree.restaurantapp.entity.Block;
import com.mindtree.restaurantapp.entity.Shop;
import com.mindtree.restaurantapp.exception.service.NoSuchTypeException;
import com.mindtree.restaurantapp.exception.service.RestaurantAppServiceexception;
import com.mindtree.restaurantapp.exception.service.ShopNotFoundException;
import com.mindtree.restaurantapp.repository.BlockRepository;
import com.mindtree.restaurantapp.repository.ShopRepository;
import com.mindtree.restaurantapp.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	public BlockRepository blockRepository;
	@Autowired
	private ShopRepository shopRepository;

	@Override
	public Block addShopToBlock(Shop shop, int blockId) throws RestaurantAppServiceexception {
		Block block = blockRepository.findById(blockId)
				.orElseThrow(() -> new ShopNotFoundException("There is no such shop"));
		List<Shop> shops = new ArrayList<Shop>();
		shop.setBlocks(block);
		shops.add(shop);
		block.setShops(shops);
		blockRepository.save(block);
		return block;
	}

	@Override
	public List<Shop> getAllShopsByType(String type) throws RestaurantAppServiceexception {
		List<Shop> shops = shopRepository.findAll();
		shops.stream().findAny().orElseThrow(() -> new ShopNotFoundException("no shops found"));
		List<Shop> shops1 = new ArrayList<Shop>();
		shops1 = shops.stream().filter(s -> s.getType().contains(type)).collect(Collectors.toList());
		shops1.stream().findAny().orElseThrow(() -> new NoSuchTypeException("There is no such type exception"));
		return shops1;
	}
	@Override
	public Shop seralizingTheShopData(Shop shop) throws IOException {
		FileOutputStream fos = new FileOutputStream("D://ShopFileIO.txt");
		BufferedOutputStream bos= new BufferedOutputStream(fos);
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(shop);
		oos.close();
		return shop;
	}

	@Override
	public Shop deSerializingTheShopData() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("D://ShopFileIO.txt");
		BufferedInputStream bis = new BufferedInputStream(fis);
		ObjectInputStream ois = new ObjectInputStream(bis);
		Shop shop=(Shop) ois.readObject();
		ois.close();
		return shop;
	}
}
