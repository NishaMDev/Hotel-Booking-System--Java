package com.sjsu.hotel.resource;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class RoomType {

    protected Integer guestCount;
    protected Integer roomCount;
    protected Double basePrice;
    protected String hotelName;

    public abstract String getPriceBreakdown();

    public abstract Double getCost();
}
