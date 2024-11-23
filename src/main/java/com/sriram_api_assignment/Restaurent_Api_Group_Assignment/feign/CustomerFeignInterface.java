package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.feign;

import com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models.Restaurant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("restaurant")
public interface CustomerFeignInterface {
    @GetMapping("restaurant/get-all-restaurants")
    List<Restaurant> getAllRestaurants();


    @GetMapping("restaurant/{identifier}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable String identifier);

    @GetMapping("restaurant/menus/{identifier}")
    public ResponseEntity<List<Restaurant.Menu>> getAllMenus(@PathVariable String identifier);

}
