package com.example.RealEstateSite.model;

import lombok.Data;

@Data
public class AuthenticationResponse {

    public final String jwt;

    public final UserContact userContact;

}
