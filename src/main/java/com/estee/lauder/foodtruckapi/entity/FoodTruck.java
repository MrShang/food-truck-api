package com.estee.lauder.foodtruckapi.entity;

import java.util.List;

public class FoodTruck {

    public enum FacilityType {
        PUSH_CART("Push Cart"), TRUCK("Truck");
        private final String value;

        FacilityType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static FacilityType fromString(String str) {
            for (FacilityType type : FacilityType.values()) {
                if (type.value.equalsIgnoreCase(str)) {
                    return type;
                }
            }
            return FacilityType.TRUCK;
        }
    }

    public enum Status {
        REQUESTED, EXPIRED, SUSPEND, APPROVED, ISSUED
    }
    private List<String> foodItems;
    private FacilityType facilityType;
    private String locationId;
    private String locationDescription;

    private String applicant;
    private String address;
    private Status status;
    private String latitude;
    private String longitude;

    private String block;
    private String lot;

    public List<String> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<String> foodItems) {
        this.foodItems = foodItems;
    }

    public FacilityType getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(FacilityType facilityType) {
        this.facilityType = facilityType;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }
}
