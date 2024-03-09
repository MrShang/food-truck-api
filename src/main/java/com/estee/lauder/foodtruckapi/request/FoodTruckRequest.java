package com.estee.lauder.foodtruckapi.request;

import com.estee.lauder.foodtruckapi.entity.FoodTruck.Status;

public class FoodTruckRequest {

    private Status status;

    private FoodTruckRequest(Builder builder) {
        this.status = builder.status;
    }

    public Status getStatus() {
        return status;
    }

    public static class Builder {
        private Status status;

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public FoodTruckRequest build() {
            return new FoodTruckRequest(this);
        }
    }

}
