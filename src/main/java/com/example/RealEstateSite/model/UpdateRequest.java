package com.example.RealEstateSite.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateRequest {

    private String jwt;
    private Post post;

}
