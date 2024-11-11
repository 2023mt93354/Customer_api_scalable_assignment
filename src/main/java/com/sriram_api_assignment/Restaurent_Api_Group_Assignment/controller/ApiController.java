package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.controller;

import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.dao.Customer_db_repo;
import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models.Customer;
import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.service.CustomerServiceInterface;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.List;

@RestController
public class ApiController {

    @Autowired
    CustomerServiceInterface customerServiceInterface;



    @PostMapping("/customer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        Customer savedCustomer= customerServiceInterface.addCust(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>>  getAllCustomers(){
        List<Customer> customers=customerServiceInterface.getAllCustomers();
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }

    @GetMapping("customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id){
        return customerServiceInterface.getCustomerById(id)
                .map(customer->new ResponseEntity<>(customer,HttpStatus.OK))
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"customer not found"));
    }



}
