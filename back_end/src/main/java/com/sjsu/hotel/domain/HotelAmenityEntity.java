package com.sjsu.hotel.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "hotel_amenities")
@Getter
@Setter
public class HotelAmenityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hotel_amenity_id")
    private int id;

    @Column(name = "hotel_id", nullable = false)
    private int hotelId;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "chargeable", nullable = false)
    private Boolean chargeable;

}
