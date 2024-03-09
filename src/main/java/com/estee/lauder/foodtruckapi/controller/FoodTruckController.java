package com.estee.lauder.foodtruckapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.estee.lauder.foodtruckapi.entity.FoodTruck;
import com.estee.lauder.foodtruckapi.request.FoodTruckRequest;
import com.estee.lauder.foodtruckapi.service.IFoodTruckService;
import com.estee.lauder.foodtruckapi.view.FoodTruckView;
import com.estee.lauder.foodtruckapi.view.FoodTruckViewFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodTruckController {

    private final IFoodTruckService foodTruckService;
    private final FoodTruckViewFactory foodTruckViewFactory;

    @Autowired
    public FoodTruckController(
            IFoodTruckService foodTruckService,
            FoodTruckViewFactory foodTruckViewFactory) {
        this.foodTruckService = foodTruckService;
        this.foodTruckViewFactory = foodTruckViewFactory;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/foodTruck/list")
    public List<FoodTruckView> getFoodTruck(
            @RequestParam(required = false)
            FoodTruck.Status status) {
        List<FoodTruck> foodTrucks = status == null ?
                foodTruckService.getAllFoodTrucks() :
                foodTruckService.filterFoodTrucks(
                        new FoodTruckRequest.Builder()
                                .withStatus(status)
                                .build());

        return foodTrucks.stream()
                .map(foodTruckViewFactory::buildView)
                .collect(Collectors.toList());
    }
}
