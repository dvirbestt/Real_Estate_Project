package com.example.RealEstateSite.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegistrationRequest {

    @NotNull(message = "User Name Must Be Filled")
    @Pattern(regexp = "^([a-zA-Z0-9]{4,12})$", message = "User Name must Be Between 4-12 Characters With No Special Symbols ")
    String username;

    @NotNull(message = "Password Must Be Filled")
    @Pattern(regexp = "^((?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])).{8,20}$",
            message = "Password Must Have At Least 8 Character And Contain One Lower Case , One Uppercase, One Digit, One Symbol")
    String password;

    @Pattern(regexp = "^((?=.*[A-Za-z])).{1,20}$", message = "First Name Must Be Filled And Contain Only Letters")
    String firstName;
    @Pattern(regexp = "^((?=.*[A-Za-z])).{1,20}$", message = "Last Name Must Be Filled And Contain Only Letters")
    String lastName;
    @Pattern(regexp = "^.{2,40}$", message = "Email Must Be Filled")
    String email;

    @NotNull(message = "Phone Number Must Be Filled")
    @Pattern(regexp = "^[0-9]{10}$",message = "Phone Must Be 10 digits")
    String phoneNumber;


}

