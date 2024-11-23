package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.serviceInterface;

import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models.Customer;
import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models.Restaurant;
import java.util.List;
import java.util.Optional;

public interface CustomerServiceInterface {
    Customer addCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerById(String  id);

    Customer updateCustomer(String id, Customer customerDetails);

    void deleteCustomerById(String  id);

    List<Restaurant> getAllRestaurants();

    Restaurant getRestaurantById(String identifier);

    List<Restaurant.Menu> getAllMenus(String identifier);
}
