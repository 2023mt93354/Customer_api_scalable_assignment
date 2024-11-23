package com.sriram_api_assignment.Restaurent_Api_Group_Assignment.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class Restaurant {
    private String id;
    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;
    private List<Menu> menus;
    private String username;
    private String password;

    // Menu class embedded inside Restaurant class
    @Data
    @NoArgsConstructor
    public static class Menu {
        private String id;
        private List<MenuItem> menuItems;

        @Data
        @NoArgsConstructor
        public static class MenuItem {
            private String name;
            private double price;
            private String category;
            private List<String> ingredients;
        }
    }
}