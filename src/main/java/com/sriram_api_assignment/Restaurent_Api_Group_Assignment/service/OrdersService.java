package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.dao.Orders_db_repo;
import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models.Orders;
import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.serviceInterface.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService implements OrderServiceInterface {

    @Autowired
    Orders_db_repo ordersRepository;

    public Orders createOrder(Orders order) {
        return ordersRepository.save(order);
    }

    // Method to get all orders
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    // Method to get orders by customer name
    public List<Orders> getOrdersByCustomerName(String customerName) {
        return ordersRepository.findByCustomerName(customerName);
    }

    // Method to get orders by restaurant name
    public List<Orders> getOrdersByRestaurantName(String restaurantName) {
        return ordersRepository.findByRestaurantName(restaurantName);
    }

    // Method to get orders by status
    public List<Orders> getOrdersByStatus(String status) {
        return ordersRepository.findByOrderStatus(status);
    }

    // Method to get an order by its ID
    public Optional<Orders> getOrderById(String orderId) {
        return ordersRepository.findById(orderId);
    }

    @Override
    public String updateOrderStatus(String orderId, String statusPayload) throws JsonProcessingException {
      List<String> ALLOWED_STATUSES = Arrays.asList(
                "Preparation Done", "Pending", "Waiting for Delivery Partner",
                "Delivery Partner Arrived", "Delivery Partner on Transit", "Delivered");
//        String status = statusPayload.trim().replace("\"", ""); // Clean the raw payload (if JSON-like string is passed)
        String status = new ObjectMapper()
                .readTree(statusPayload)
                .get("status")
                .asText();

        if (!ALLOWED_STATUSES.contains(status)) {
            throw new IllegalArgumentException("Invalid status provided");
        }

        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderStatus(status);
        ordersRepository.save(order);

        return "Order status updated to: " + status;
    }

    @Override
    public String getOrderTrackingStatus(String orderId) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return "Order status: " + order.getOrderStatus();
    }

    @Override
    public String cancelOrder(String orderId) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if ("Pending".equalsIgnoreCase(order.getOrderStatus())) {
            ordersRepository.deleteById(orderId);
            return "Order canceled successfully";
        } else {
            return "Sorry, the dish is already being prepared and cannot be canceled.";
        }
    }



}