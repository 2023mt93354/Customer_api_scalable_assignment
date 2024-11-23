package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.dao;

import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Orders_db_repo extends MongoRepository<Orders, String> {
    List<Orders> findByCustomerName(String customerName);
    List<Orders> findByRestaurantName(String restaurantName);
    List<Orders> findByOrderStatus(String orderStatus);
}