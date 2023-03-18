package com.example.RealEstateSite.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserContact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String username;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;

    public UserContact(String username,String firstName, String lastName,String email, String phoneNumber){
            this.username = username;
            this.firstName= firstName;
            this.lastName= lastName;
            this.email = email;
            this.phoneNumber = phoneNumber;
    }

}
