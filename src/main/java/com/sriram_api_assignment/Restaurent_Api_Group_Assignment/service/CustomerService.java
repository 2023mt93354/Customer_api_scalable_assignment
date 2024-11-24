package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.service;


import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.dao.Customer_db_repo;

import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.exception.ResourceNotFoundException;
import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.feign.CustomerFeignInterface;
import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models.Customer;
import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models.Restaurant;

import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.serviceInterface.CustomerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class CustomerService implements CustomerServiceInterface {
    @Autowired
    Customer_db_repo customerDbRepo;

    @Autowired
    CustomerFeignInterface customerFeignInterface;

    @Override
    public Customer addCustomer(Customer customer) {
        return customerDbRepo.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDbRepo.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(String id) {
        return customerDbRepo.findById(id);
    }

    @Override
    public Customer updateCustomer(String  id, Customer customerDetails) {
        Customer customer = customerDbRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        customer.setName(customerDetails.getName());
        customer.setAddress(customerDetails.getAddress());
        customer.setPhone(customerDetails.getPhone());
        customer.setEmail(customerDetails.getEmail());
        customer.setPincode(customerDetails.getPincode());
        customer.setCardNumber(customerDetails.getCardNumber());
        return customerDbRepo.save(customer);
    }

    @Override
    public void deleteCustomerById(String  id) {
        Customer customer = customerDbRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        customerDbRepo.delete(customer);
    }


    public List<Restaurant> getAllRestaurants() {
        // Fetch the list of restaurants from Feign client
        List<Restaurant> restaurants = customerFeignInterface.getAllRestaurants();
        return restaurants;

    }
    public Restaurant getRestaurantById(@PathVariable String identifier){
      return   customerFeignInterface.getRestaurantById(identifier).getBody();
    }

    public List<Restaurant.Menu> getAllMenus(@PathVariable String identifier){
        return customerFeignInterface.getAllMenus(identifier).getBody();
    }

}


