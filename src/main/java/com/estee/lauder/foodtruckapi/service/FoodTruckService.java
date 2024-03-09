package com.estee.lauder.foodtruckapi.service;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.estee.lauder.foodtruckapi.entity.FoodTruck;
import com.estee.lauder.foodtruckapi.entity.FoodTruck.FacilityType;
import com.estee.lauder.foodtruckapi.entity.FoodTruck.Status;
import com.estee.lauder.foodtruckapi.request.FoodTruckRequest;
import com.estee.lauder.foodtruckapi.util.CSVUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class FoodTruckService implements IFoodTruckService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FoodTruckService.class);
    private final List<FoodTruck> allFoodTrucks;
    private final String file;

    private final ResourceLoader resourceLoader;

    public FoodTruckService(
            @Value("${data.file.name}")
            String file,
            ResourceLoader resourceLoader) {
        this.file = file;
        this.resourceLoader = resourceLoader;
        this.allFoodTrucks = loadAllFoodTruck();
    }

    private List<FoodTruck> loadAllFoodTruck() {
        try {
            URI uri = resourceLoader.getResource("classpath:" + file).getURI();
            List<String[]> foodTruckRawData = CSVUtil.read(uri);
            return foodTruckRawData.stream()
                    .map(line -> {
                        FoodTruck foodTruck = new FoodTruck();
                        foodTruck.setLocationId(line[0]);
                        foodTruck.setApplicant(line[1]);
                        foodTruck.setFacilityType(FacilityType.fromString(line[2]));
                        // line[3] is cnn
                        foodTruck.setLocationDescription(line[4]);
                        foodTruck.setAddress(line[5]);
                        // line[4] is blocklot
                        foodTruck.setBlock(line[7]);
                        foodTruck.setLot(line[8]);
                        Status status = StringUtils.isBlank(line[10]) ? Status.EXPIRED : Status.valueOf(line[10]);
                        foodTruck.setStatus(status);
                        foodTruck.setFoodItems(extractFoodItems(line[11]));
                        foodTruck.setLatitude(line[14]);
                        foodTruck.setLongitude(line[15]);
                        return foodTruck;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("loading food truck error", e);
        }
        return Collections.emptyList();
    }

    private List<String> extractFoodItems(String foodStr) {
        if (StringUtils.isBlank(foodStr)) {
            return Collections.emptyList();
        }
        return Arrays.stream(foodStr.split(":"))
                .map(StringUtils::trimToEmpty)
                .toList();
    }

    @Override
    public List<FoodTruck> getAllFoodTrucks() {
        return allFoodTrucks;
    }

    @Override
    public List<FoodTruck> filterFoodTrucks(FoodTruckRequest request) {
        return allFoodTrucks.stream()
                .filter(item -> request.getStatus().equals(item.getStatus()))
                .collect(Collectors.toList());
    }

}
