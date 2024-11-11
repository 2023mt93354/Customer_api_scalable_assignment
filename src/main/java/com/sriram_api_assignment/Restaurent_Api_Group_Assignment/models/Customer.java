package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="customer_db")
public class Customer {
    @Id
    private String id;
    private String cust_name;
    private String cust_address;
    private String cust_phone;
    private String cust_email;
}
