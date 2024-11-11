package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.service;

import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.dao.Customer_db_repo;
import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements CustomerServiceInterface{
    @Autowired
    Customer_db_repo repo;
    @Override
    public Customer addCust(Customer customer) {

        return repo.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(String id) {
        return repo.findById(id);
    }
}
