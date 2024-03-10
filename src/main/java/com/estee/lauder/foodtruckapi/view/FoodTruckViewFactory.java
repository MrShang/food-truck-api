package com.estee.lauder.foodtruckapi.view;

import com.estee.lauder.foodtruckapi.entity.FoodTruck;

import org.springframework.stereotype.Component;

@Component
public class FoodTruckViewFactory {
    public FoodTruckView buildView(FoodTruck foodTruck) {
        FoodTruckView view = new FoodTruckView();
        view.setAddress(foodTruck.getAddress());
        view.setApplicant(foodTruck.getApplicant());
        view.setFoodItems(foodTruck.getFoodItems());
        view.setLatitude(foodTruck.getLatitude());
        view.setFacilityType(foodTruck.getFacilityType().name());
        view.setLocationDescription(foodTruck.getLocationDescription());
        view.setLocationId(foodTruck.getLocationId());
        view.setStatus(foodTruck.getStatus().name());
        view.setLongitude(foodTruck.getLongitude());
        view.setBlock(foodTruck.getBlock());
        view.setLot(foodTruck.getLot());
        return view;
    }
}
