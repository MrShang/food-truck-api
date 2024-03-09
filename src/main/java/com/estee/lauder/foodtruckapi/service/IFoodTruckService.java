package com.estee.lauder.foodtruckapi.service;

import java.util.List;

import com.estee.lauder.foodtruckapi.entity.FoodTruck;
import com.estee.lauder.foodtruckapi.request.FoodTruckRequest;

public interface IFoodTruckService {

    List<FoodTruck> getAllFoodTrucks();

    List<FoodTruck> filterFoodTrucks(FoodTruckRequest request);
}
