package com.example.RealEstateSite.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String author;
    private String authorFirstName;
    private String address;
    private String city;
    private float numOfRooms;
    private int floor;
    private EstateType type;
    private int parameter;
    private String description;
    private String photosUrl;

}
