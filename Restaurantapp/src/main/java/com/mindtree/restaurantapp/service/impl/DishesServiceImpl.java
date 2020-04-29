package com.mindtree.restaurantapp.service.impl;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mindtree.restaurantapp.entity.Dishes;
import com.mindtree.restaurantapp.entity.Shop;
import com.mindtree.restaurantapp.exception.service.NoDishesInTheshopException;
import com.mindtree.restaurantapp.exception.service.RestaurantAppServiceexception;
import com.mindtree.restaurantapp.exception.service.ShopNotFoundException;
import com.mindtree.restaurantapp.repository.DishesRepository;
import com.mindtree.restaurantapp.repository.ShopRepository;
import com.mindtree.restaurantapp.service.DishesService;

@Service
public class DishesServiceImpl implements DishesService {

	@Autowired
	private ShopRepository shopRepository;
	@Autowired
	private DishesRepository dishesRepository;

	@Override
	public Shop addDishesToShop(int shopId, List<Dishes> dishes) throws RestaurantAppServiceexception {

		Shop shop = shopRepository.findById(shopId)
				.orElseThrow(() -> new ShopNotFoundException("No Such Shop Exception"));
		if (shop.getRating() > 4.5) {

			dishes.forEach(d -> {
				d.setPrice(d.getPrice() - (d.getPrice() * 0.1));
				d.setShops(shop);
			});

			shop.setDish(dishes);
			shopRepository.save(shop);
		} else if (shop.getRating() > 3.5 && shop.getRating() <= 4.5) {
			dishes.forEach(d -> {
				d.setPrice(d.getPrice() - (d.getPrice() * 0.05));
				d.setShops(shop);
			});

			shop.setDish(dishes);
			shopRepository.save(shop);
		} else if (shop.getRating() > 2.5 && shop.getRating() <= 3.5) {
			dishes.forEach(d -> {
				d.setPrice(d.getPrice() - (d.getPrice() * 0.02));
				d.setShops(shop);
			});

			shop.setDish(dishes);
			shopRepository.save(shop);
		}

		return shop;
	}

	@Override
	public List<Dishes> getAllDishesByShop(int shopId, String type) throws RestaurantAppServiceexception {
		shopRepository.findById(shopId).orElseThrow(() -> new ShopNotFoundException("No Such Shop Exception"));
		List<Dishes> dishes = dishesRepository.findAll();
		List<Dishes> dishes1 = dishes.stream().filter(d -> d.getShops().getShopId() == shopId)
				.filter(d -> d.getType().compareTo(type) == 0).collect(Collectors.toList());
		dishes1.stream().findAny().orElseThrow(() -> new NoDishesInTheshopException(
				"No Dishes in the shop or No Dishes in the given type in the shop"));
		return dishes1;
	}

}
