package com.restaurant.api;

import com.restaurant.model.HartaRestaurant;

public class InterfataMeseApi {

    // Date locale până când este implementată încărcarea hărții din backend.
    public HartaRestaurant loadRestaurant() {
        return new HartaRestaurant(8, 12);
    }
}
