package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.service;

import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.dao.Customer_db_repo;

import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.dao.Restaurant_Order_repo;
import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.exception.ResourceNotFoundException;
import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models.Customer;

import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models.Restaurant_Order;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class CustomerService implements CustomerServiceInterface{
    @Autowired
    Customer_db_repo customerDbRepo;
    @Autowired
    Restaurant_Order_repo restaurantOrderRepo;
    @Autowired
    JdbcTemplate jdbcTemplate;

//    @Autowired
//    Delivery_Order_Repo deliveryOrderRepo;

//    @Autowired
//    private JwtUtil jwtUtil;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//    @Autowired
//    private PasswordEncoder passwordEncoder;



    @Override
    public Customer addCustomer(Customer customer) {
        return customerDbRepo.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDbRepo.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        return customerDbRepo.findById(id);
    }

    @Override
    public Restaurant_Order createNewRestaurantOrder(Restaurant_Order restaurantOrder) {
        return restaurantOrderRepo.save(restaurantOrder);
    }

    @Override
    public Customer updateCustomer(Integer id, Customer customerDetails) {
        Customer customer = customerDbRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        customer.setName(customerDetails.getName());
        customer.setAddress(customerDetails.getAddress());
        customer.setPhone(customerDetails.getPhone());
        customer.setEmail(customerDetails.getEmail());
        customer.setPincode(customerDetails.getPincode());
        customer.setCardNumber(customerDetails.getCardNumber());
        return customerDbRepo.save(customer);
    }

    @Override
    public void deleteCustomerById(Integer id) {
        Customer customer = customerDbRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        customerDbRepo.delete(customer);
    }

    @Override
    public List<Restaurant_Order> getAllRestaurantOrder() {
        return restaurantOrderRepo.findAll();
    }

    @Override
    public String deleteOrderIfPending(Integer orderId) {
        Optional<Restaurant_Order> orderOpt = restaurantOrderRepo.findById(orderId);

        // Check if the order exists
        if (orderOpt.isPresent()) {
            Restaurant_Order order = orderOpt.get();

            // Check if the order status is 'pending'
            if ("Pending".equalsIgnoreCase(order.getOrderStatus())|| order.getOrderStatus()==null || order.getOrderStatus().equals("")) {
                // Proceed with deletion
                restaurantOrderRepo.delete(order);
                return "Order with ID " + orderId + " has been cancelled successfully.";
            } else {
                return "Cannot cancel the order, as the restaurant accepted the order.";
            }
        } else {
            return "Order not found.";
        }
    }

    @Override
    public String getOrderStatusById(Integer orderId) {
        Optional<Restaurant_Order> order = restaurantOrderRepo.findById(orderId);

        if (order.isPresent()) {
            String restaurant_payload = "Restaurant Status : " + order.get().getOrderStatus();
            String sql = "SELECT OrderStatus FROM DELIVERYORDERS WHERE orderid = ?";
            Map<String, Object> result = jdbcTemplate.queryForMap(sql, orderId);
            if (result != null && !result.isEmpty()) {
                String orderStatus = (String) result.get("OrderStatus");
                return restaurant_payload+" \n deliveryStatus : "+ orderStatus ;
            } else {
                return restaurant_payload+  "\nDelivery Not yet assigned for Order ID: " + orderId + "\" }";
            }
        } else {

            return "No Order exists with ID: " + orderId;
        }
    }


//
//    public String login(CustomerLoginRequest loginRequest) {
//        // Find customer by email
//        Optional<Customer> optionalCustomer = customerDbRepo.findByEmail(loginRequest.getEmail());
//
//        // Check if the customer exists
//        if (optionalCustomer.isEmpty()) {
//            throw new RuntimeException("Invalid email or password");
//        }
//
//        // Get the customer object
//        Customer customer = optionalCustomer.get();
//
//        // Validate the password
//        if (!passwordEncoder.matches(loginRequest.getPassword(), customer.getPassword())) {
//            throw new RuntimeException("Invalid email or password");
//        }
//
//        // Generate JWT token
//        return jwtUtil.generateToken(customer.getEmail());
//    }
//

//    @Override
//    public Customer authenticateCustomer(String email, String password) {
//        return customerDbRepo.findByEmailAndPassword(email, password)
//                .orElse(null);
//    }
//    @Override
//    public Customer getCustomerByEmail(String email) {
//        return customerDbRepo.findByEmail(email)
//                .orElse(null);
//    }
//@Autowired
//    AuthenticationManager authenticationManager;
//    @Override
//    public String verify(Customer cust) {
//Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(cust.getName(),cust.getPassword()));
//        if(authentication.isAuthenticated()) return JwtUtil.generateTokens();
//        return "failure";
//    }
}
