package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "Orders")  // Specify the collection name in MongoDB
public class Orders {
    @Id
    private String id;  // MongoDB _id

    private List<FoodItem> foodItems;  // List of food items in the order
    private Double totalAmount;
    private String restaurantName;
    private String customerName;
    private String customerPhoneNo;
    private String deliveryAddress;
    private String paymentMethod;
    private String orderStatus;

    // Nested class for FoodItem
    @Data
    public static class FoodItem {
        private String foodId;
        private String foodName;
        private Integer quantity;  // Quantity of the food item
    }
}