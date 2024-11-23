package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models.Orders;
import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.serviceInterface.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer/orders")
public class OrdersController {

    @Autowired
    private OrderServiceInterface ordersServiceInt;

    // Endpoint to create a new order
    @PostMapping("/")
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
        Orders savedOrder = ordersServiceInt.createOrder(order);
        return ResponseEntity.ok(savedOrder);
    }

    // Endpoint to get all orders
    @GetMapping("/")
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> orders = ordersServiceInt.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // Endpoint to get orders by customer name
    @GetMapping("/customer/{customerName}")
    public ResponseEntity<List<Orders>> getOrdersByCustomerName(@PathVariable String customerName) {
        List<Orders> orders = ordersServiceInt.getOrdersByCustomerName(customerName);
        return ResponseEntity.ok(orders);
    }

    // Endpoint to get orders by restaurant name
    @GetMapping("/restaurant/{restaurantName}")
    public ResponseEntity<List<Orders>> getOrdersByRestaurantName(@PathVariable String restaurantName) {
        List<Orders> orders = ordersServiceInt.getOrdersByRestaurantName(restaurantName);
        return ResponseEntity.ok(orders);
    }

    // Endpoint to get orders by order status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Orders>> getOrdersByStatus(@PathVariable String status) {
        List<Orders> orders = ordersServiceInt.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }

    // Endpoint to get an order by its ID
    @GetMapping("/{orderId}")
    public ResponseEntity<Orders> getOrderById(@PathVariable String orderId) {
        Optional<Orders> order = ordersServiceInt.getOrderById(orderId);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint to update the order status
    @PatchMapping("/status-update/{orderId}")
    public ResponseEntity<String> updateOrderStatus(
            @PathVariable String orderId,
            @RequestBody String statusPayload) throws JsonProcessingException {

        return ResponseEntity.ok(ordersServiceInt.updateOrderStatus(orderId, statusPayload));
    }


    // Endpoint to track the order status
    @GetMapping("/tracking/{orderId}")
    public ResponseEntity<String> getOrderTrackingStatus(@PathVariable String orderId) {
        return ResponseEntity.ok(ordersServiceInt.getOrderTrackingStatus(orderId));
    }

    // Endpoint to cancel an order
    @PatchMapping("/cancel/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable String orderId) {
        return ResponseEntity.ok(ordersServiceInt.cancelOrder(orderId));
    }


}
