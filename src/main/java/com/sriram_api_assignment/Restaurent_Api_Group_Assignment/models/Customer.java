package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Customer")
public class Customer {

    @Id
    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String pincode;
    private String cardNumber;
    private String password;
    private String username;
}
