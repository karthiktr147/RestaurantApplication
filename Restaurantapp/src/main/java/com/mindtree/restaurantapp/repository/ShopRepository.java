package com.mindtree.restaurantapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.restaurantapp.entity.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {

	public Shop findByType(String type);
}
