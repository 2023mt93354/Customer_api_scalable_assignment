package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.serviceInterface;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models.Orders;
import java.util.List;
import java.util.Optional;

public interface OrderServiceInterface {
    Orders createOrder(Orders order);

    List<Orders> getAllOrders();

    List<Orders> getOrdersByCustomerName(String customerName);

    List<Orders> getOrdersByRestaurantName(String restaurantName);

    List<Orders> getOrdersByStatus(String status);

    Optional<Orders> getOrderById(String orderId);

    String updateOrderStatus(String orderId, String statusPayload) throws JsonProcessingException;

    String getOrderTrackingStatus(String orderId);

    String  cancelOrder(String orderId);

}
