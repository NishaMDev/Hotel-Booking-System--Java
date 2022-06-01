package com.sjsu.hotel.service;
import com.sjsu.hotel.resource.RoomType;

import java.util.HashMap;


public class RoomTypeDeluxe extends RoomType {
    HashMap<String, Double> gcCostMultiplier = new HashMap<>();

    public RoomTypeDeluxe(Integer gc , Integer rc) {
        guestCount = gc;
        roomCount = rc;
        //TODO: Read from DB
        basePrice = 130.00;

        gcCostMultiplier.put("1", 1.00);
        gcCostMultiplier.put("2", 1.10);
        gcCostMultiplier.put("3", 1.20);
        gcCostMultiplier.put("4", 1.30);
    }

    @Override
    public String getPriceBreakdown() {
        return "<br /> Room: Deluxe <br /> Guests: " + this.guestCount;
    }

    @Override
    public Double getCost() {
        //consider the number of guests.
        //get cost of room type, and guest limitation for room type
        //auto-select number of rooms to accommodate guests
        Integer factor = guestCount/roomCount;
        Double multiplier = gcCostMultiplier.get(factor.toString());
        if (multiplier == null) {
            multiplier =2.0;
        }
        return basePrice * multiplier;
    }
}
