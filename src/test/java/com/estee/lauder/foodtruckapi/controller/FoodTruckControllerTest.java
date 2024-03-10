package com.estee.lauder.foodtruckapi.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.estee.lauder.foodtruckapi.entity.FoodTruck;
import com.estee.lauder.foodtruckapi.service.IFoodTruckService;
import com.estee.lauder.foodtruckapi.view.FoodTruckView;
import com.estee.lauder.foodtruckapi.view.FoodTruckViewFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FoodTruckControllerTest {

    private static final IFoodTruckService foodTruckService = mock(IFoodTruckService.class);
    private static final FoodTruckViewFactory foodTruckViewFactory = mock(FoodTruckViewFactory.class);

    @BeforeAll
    public static void init() {
        List<FoodTruck> foodTruckList = buildFoodTruck();
        when(foodTruckService.getAllFoodTrucks()).thenReturn(foodTruckList);
        foodTruckList.forEach(foodTruck -> {
            FoodTruckView view = new FoodTruckView();
            view.setApplicant(foodTruck.getApplicant());
            view.setStatus(foodTruck.getStatus().name());
            when(foodTruckViewFactory.buildView(foodTruck)).thenReturn(view);
        });
    }

    @Test
    public void testGetAllFoodTruckList() {
        FoodTruckController controller = new FoodTruckController(foodTruckService, foodTruckViewFactory);
        // Given we have a full food truck list
        // When we try to get the full truck list
        List<FoodTruckView> viewList = controller.getFoodTruck(null);
        // Then we will get a full food truck list
        assertEquals(5, viewList.size());
        for (int i = 0; i < 5; i++) {
            assertEquals("applicant" + i, viewList.get(i).getApplicant());
            if (i % 2 == 0) {
                assertEquals(FoodTruck.Status.APPROVED, FoodTruck.Status.valueOf(viewList.get(i).getStatus()));
            } else {
                assertEquals(FoodTruck.Status.EXPIRED, FoodTruck.Status.valueOf(viewList.get(i).getStatus()));
            }
        }
    }

    private static List<FoodTruck> buildFoodTruck() {
        return IntStream.range(0, 5).mapToObj(index -> {
           FoodTruck foodTruck = new FoodTruck();
           foodTruck.setApplicant("applicant" + index);
           if (index % 2 == 0) {
               foodTruck.setStatus(FoodTruck.Status.APPROVED);
           } else {
               foodTruck.setStatus(FoodTruck.Status.EXPIRED);
           }
           return foodTruck;
        }).collect(Collectors.toList());
    }
}
