package com.sjsu.hotel.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelSearchParam {
    private String name;
    private String description;
    private Address address;
    public HotelSearchParam() {
    }


}
