package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.dao;

import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Customer_db_repo extends MongoRepository<Customer,String> {
    Customer findByUsername(String username);
}
