package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.service;

import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerServiceInterface {
    Customer addCust(Customer customer);

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerById(String id);
}
