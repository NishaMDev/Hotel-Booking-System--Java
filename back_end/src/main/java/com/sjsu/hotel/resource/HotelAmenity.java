package com.sjsu.hotel.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
public class HotelAmenity {
    private int id;

    @Positive(message = "Must supply hotel")
    private int hotelId;
    private boolean chargeable;
    private BigDecimal price;
    private String description;
    private String name;

}
