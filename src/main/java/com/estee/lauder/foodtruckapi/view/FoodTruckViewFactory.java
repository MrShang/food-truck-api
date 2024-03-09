package com.estee.lauder.foodtruckapi.view;

import com.estee.lauder.foodtruckapi.entity.FoodTruck;

import org.springframework.stereotype.Component;

@Component
public class FoodTruckViewFactory {
    public FoodTruckView buildView(FoodTruck foodTruck) {
        return new FoodTruckView(foodTruck.getFoodItems(), foodTruck.getAddress(), foodTruck.getApplicant(),
                foodTruck.getLatitude(), foodTruck.getFacilityType().getValue(),
                foodTruck.getLocationDescription(), foodTruck.getLocationId(), foodTruck.getStatus().name(),
                foodTruck.getLongitude(), foodTruck.getBlock(), foodTruck.getLot());
    }
}
