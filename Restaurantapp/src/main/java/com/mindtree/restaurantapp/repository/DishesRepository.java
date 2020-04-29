package com.mindtree.restaurantapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.restaurantapp.entity.Dishes;

@Repository
public interface DishesRepository extends JpaRepository<Dishes, Integer> {

}
