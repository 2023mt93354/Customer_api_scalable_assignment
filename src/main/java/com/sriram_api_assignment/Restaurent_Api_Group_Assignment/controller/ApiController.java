package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.controller;


import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.exception.ResourceNotFoundException;
import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models.*;
import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.service.CustomerServiceInterface;
//import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.service.MyUserDetaiService;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
//import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.Jwt.JwtUtil;


import java.util.List;

@RestController
@RequestMapping("/customer")
public class ApiController {

    @Autowired
    CustomerServiceInterface customerServiceInterface;
//    @Autowired
//     RestTemplate restTemplate;
//    @Autowired
//    JwtUtil jwtUtil;
//    String TOKEN="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IkJoYXJhdGgxNTkiLCJpYXQiOjE3MzE1Nzk2Mzl9.1WZRshV-j2eMAEmdRUTlxnGbT0ZwdEaAd1Z34tCTKfI";
//
//    private static final String EXTERNAL_API_URL ="https://fooddeliveryapigroup37.azurewebsites.net/restaurant/get-menu";




    @GetMapping("/")
    public ResponseEntity<List<Customer>>  getAllCustomers(){
        List<Customer> customers=customerServiceInterface.getAllCustomers();
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id){
        return customerServiceInterface.getCustomerById(id)
                .map(customer->new ResponseEntity<>(customer,HttpStatus.OK))
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"customer not found"));
    }
    @GetMapping("/orders")
    public ResponseEntity<List<Restaurant_Order>>  getAllRestaurantOrder(){
        List<Restaurant_Order> restaurantOrder=customerServiceInterface.getAllRestaurantOrder();
        return new ResponseEntity<>(restaurantOrder,HttpStatus.OK);
    }


    @GetMapping("/status/{orderId}")
    public String getOrderStatus(@PathVariable Integer orderId) {
        return customerServiceInterface.getOrderStatusById(orderId);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody Customer customerDetails) {
        try {
            Customer updatedCustomer = customerServiceInterface.updateCustomer(id, customerDetails);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable Integer id) {
        try {
            customerServiceInterface.deleteCustomerById(id);
            return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


//    @GetMapping("/view-menu")
//    public ResponseEntity<String> callExternalApi() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer " + TOKEN);
//        System.out.println(EXTERNAL_API_URL);
//        ResponseEntity<String> response = restTemplate.exchange(
//                EXTERNAL_API_URL, HttpMethod.GET,
//                new org.springframework.http.HttpEntity<>(headers), String.class);
//        return response;
//    }

    @PostMapping("/")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        Customer savedCustomer= customerServiceInterface.addCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/create-restaurant-order")
    public ResponseEntity<Restaurant_Order> createNewOrder(@RequestBody Restaurant_Order restaurantOrder){
        System.out.println(restaurantOrder);
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer " + TOKEN);
//        System.out.println(EXTERNAL_API_URL);
//        HttpEntity<Order> entity = new HttpEntity<>(order, headers);
//        System.out.println(order);
//        ResponseEntity<String> response = restTemplate.exchange(
//                EXTERNAL_API_URL+"update-orders-by-order-id", HttpMethod.PATCH,
//               entity, String.class);
//        return response;
        Restaurant_Order newOrder=customerServiceInterface.createNewRestaurantOrder(restaurantOrder);
        return new ResponseEntity<>(newOrder,HttpStatus.CREATED);
    }
    @DeleteMapping("/order/{orderId}")
    public String cancelOrder(@PathVariable Integer orderId) {
        return customerServiceInterface.deleteOrderIfPending(orderId);
    }

//    @PostMapping("/login")
//    public String login(@RequestBody CustomerLoginRequest loginRequest) {
//        Customer customer = customerServiceInterface.authenticateCustomer(loginRequest.getEmail(), loginRequest.getPassword());
//
//        if (customer != null) {
//            // Generate JWT token after successful authentication
//            String token = jwtUtil.generateToken(customer.getEmail());
//            return "Login successful! Your access token: " + token;
//        } else {
//            return "Invalid credentials!";
//        }
//    }
//    @GetMapping("/profile")
//    public String getProfile(@RequestHeader("Authorization") String token) {
//        if (token != null && token.startsWith("Bearer ")) {
//            token = token.substring(7);
//            if (jwtUtil.validateToken(token)) {
//                String email = jwtUtil.extractEmail(token);
//                Customer customer = customerServiceInterface.getCustomerByEmail(email);
//                if (customer != null) {
//                    return "Customer Profile: " + customer.toString();
//                } else {
//                    return "Customer not found.";
//                }
//            }
//        }
//        return "Invalid or missing token!";
//    }
//jwt
//@Autowired
//private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtUtil jwtTokenUtil;
//
//    @Autowired
//    private MyUserDetaiService userDetailsService;
//    @PostMapping("/authenticate")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
//
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//            );
//        }
//        catch (BadCredentialsException e) {
//            throw new Exception("Incorrect username or password", e);
//        }


//        final UserDetails userDetails = userDetailsService
//                .loadUserByUsername(authenticationRequest.getUsername());
//
////        final String jwt = jwtTokenUtil.generateToken(userDetails);
//
////        return ResponseEntity.ok(new AuthenticationResponse(jwt));
////    }
//
//    @PostMapping("/login")
//    public String cust_login(@RequestBody Customer cust){
//        return customerServiceInterface.verify(cust);
//    }

}




