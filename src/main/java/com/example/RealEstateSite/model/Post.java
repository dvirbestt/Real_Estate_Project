package com.example.RealEstateSite.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;
import lombok.Value;
import org.springframework.data.mapping.model.Property;

@Entity
@Data
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String author;
    @Pattern(regexp = "^((?=.*[a-zA-Z])).{2,20}$", message = "First Name Must Be Filled")
    private String authorFirstName;
    @Pattern(regexp = "^((?=.*[a-zA-Z])(?=.*[0-9])(?=.*[' '])).{3,20}$", message = "Address Must Be Filled")
    private String address;
    @Pattern(regexp = "^((?=.*[a-zA-Z'\''' '])).{2,30}$", message = "City Must Be Filled")
    private String city;
    @NotNull
    @Min(value = 1,message = "Number Of Rooms Must Be Filled")
    private float numOfRooms;
    @NotNull
    @Min(value = 1,message = "Floor Must Be Filled")
    private int floor;

    @NotNull
    @Min(value = 1,message = "Parameter Must Be Filled")
    private int parameter;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;
    private String photosUrl;
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone Must Be Filled")
    private String authorPhone;
    @Pattern(regexp = "^(.+)@(.+).com$", message = "Email Must Be Filled")
    private String authorEmail;
    @NotNull(message = "Type Must Be Filled")
    private PropertyType propertyType;
    @NotNull
    @Min(value = 1,message = "Price Must Be Filled")
    private Long price;

}
