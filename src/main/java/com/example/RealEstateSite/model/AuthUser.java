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
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;


    String username;

    String password;
    Role role;


    public AuthUser(String username,String password){
        this.username = username;
        this.password = password;
    }

    public AuthUser(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
