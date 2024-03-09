package com.estee.lauder.foodtruckapi.view;

import java.util.List;

public record FoodTruckView (
    List<String> foodItems,
    String address,
    String applicant,
    String latitude,
    String facilityType,
    String locationDescription,
    String locationId,
    String status,
    String longitude,
    String block,
    String lot
) { }
