package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.serviceInterface.login;

import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models.Customer;

public interface LoginServiceInterface {
     Customer register(Customer customer);

     String verify(Customer customer);
}
