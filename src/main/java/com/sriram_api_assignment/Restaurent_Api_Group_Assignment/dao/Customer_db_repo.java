package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.dao;

import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Customer_db_repo extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByEmailAndPassword(String email, String password);
    Optional<Customer> findByEmail(String email);
}
