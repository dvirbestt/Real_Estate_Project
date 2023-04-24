package com.example.RealEstateSite.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SearchRequest {

    @NotNull(message = "Must Fill A City")
    @Pattern(regexp = "^((?=.*[a-z])(?=.*[A-Z])).{2,20}$", message = "Must Fill A City")
    String city;

    float numOfRooms;

    float price;
    PropertyType propertyType;

    public boolean hasAll(){
        return this.city != null && this.numOfRooms > 0 && price > 0 && this.propertyType != null;
    }

    public boolean hasCityRoomsAndPrice(){
        return this.city != null && this.numOfRooms > 0 && price > 0;
    }

    public boolean hasCityAndRooms(){
        return this.city != null && this.numOfRooms > 0;
    }

    public int getPropertyType() {
        if (this.propertyType == PropertyType.RENT){
            return 0;
        }else {
            return 1;
        }
    }

    public boolean hasCityRoomsAndType() {
        return this.city != null && this.numOfRooms > 0 && this.propertyType != null;
    }

    public boolean hasCityAndType(){
        return this.city != null && this.propertyType != null;
    }

    public boolean hasCityPriceAndType() {
        return this.city != null && this.propertyType!= null && this.price != 0 ;
    }

    public boolean hasCityAndPrice() {
        return this.city != null && this.price != 0;
    }
}

