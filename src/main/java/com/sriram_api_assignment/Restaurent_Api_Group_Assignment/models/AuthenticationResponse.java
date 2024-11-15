package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor

public class AuthenticationResponse implements Serializable {
    private final String jwt;
}