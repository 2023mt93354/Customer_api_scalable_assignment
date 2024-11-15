//package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.dao;
//
//import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models.Delivery_Order;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface Delivery_Order_Repo extends  JpaRepository<Delivery_Order,Integer> {
//
//    @Query("SELECT d FROM Delivery_Order d WHERE d.OrderID = :orderId")
//    Optional<Delivery_Order> getDeliveryOrderById(@Param("orderId") Integer orderId);
//}

