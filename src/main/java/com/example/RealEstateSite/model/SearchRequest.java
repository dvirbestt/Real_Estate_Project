package com.example.RealEstateSite.model;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SearchRequest {

    @Pattern(regexp = "^(?=.*[a-zA-Z]).{2,25}$", message = "Must Fill A City")
    String city;
    float numOfRooms;

}
