package com.mindtree.restaurantapp.service;

import java.util.List;

import com.mindtree.restaurantapp.entity.Dishes;
import com.mindtree.restaurantapp.exception.service.NoDishesInTheshopException;
@FunctionalInterface
public interface FuncInterface {
public List<Dishes> getDishesByShop(List<Dishes> dishesList) throws NoDishesInTheshopException;
}
