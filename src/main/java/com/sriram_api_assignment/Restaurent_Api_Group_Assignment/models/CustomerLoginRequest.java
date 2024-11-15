package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerLoginRequest {
    private String email;
    private String password;
}