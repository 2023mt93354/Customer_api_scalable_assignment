package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.controller;

import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.exception.ResourceNotFoundException;
import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models.Customer;
import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models.Restaurant;
import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.serviceInterface.CustomerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class ApiController {

    @Autowired
    CustomerServiceInterface customerServiceInterface;

    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerServiceInterface.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        return customerServiceInterface.getCustomerById(id)
                .map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer customerDetails) {
        try {
            Customer updatedCustomer = customerServiceInterface.updateCustomer(id, customerDetails);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable String id) {
        try {
            customerServiceInterface.deleteCustomerById(id);
            return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerServiceInterface.addCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-restaurant")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurantList = customerServiceInterface.getAllRestaurants();
        return ResponseEntity.ok(restaurantList);
    }

    @GetMapping("get-restaurant/{identifier}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable String identifier) {
        return ResponseEntity.ok(customerServiceInterface.getRestaurantById(identifier));
    }

    @GetMapping("/menus/{identifier}")
    public ResponseEntity<List<Restaurant.Menu>> getAllMenus(@PathVariable String identifier) {
        return ResponseEntity.ok(customerServiceInterface.getAllMenus(identifier));
    }
}




