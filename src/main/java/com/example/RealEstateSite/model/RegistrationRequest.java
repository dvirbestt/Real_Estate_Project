package com.example.RealEstateSite.model;

import lombok.Data;

@Data
public class RegistrationRequest {

    String username;
    String password;

    String firstName;
    String lastName;
    String email;
    String phoneNumber;


}

