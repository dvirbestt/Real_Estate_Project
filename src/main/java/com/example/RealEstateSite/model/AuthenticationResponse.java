package com.example.RealEstateSite.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class AuthenticationResponse {

    public final String jwt;
    public final UserContact userContact;
    public final Role role;
}
