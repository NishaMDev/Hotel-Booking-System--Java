package com.sjsu.hotel.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name="rooms")
@Getter
@Setter
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="room_id")
    private int id;

    @Column(name = "hotel_id",nullable = false)
    private int hotelId;

    @Column(name = "description", nullable = false, length = 256)
    private String description;


    @Column(name = "type", nullable = false, length = 200)
    private String type;

    @Column(name = "price", nullable = false)
    private int price;

}

