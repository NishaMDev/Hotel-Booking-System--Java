package com.sjsu.hotel.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class Booking {
    private int id;
    private String userId;
    private String amenities;
    private String status;
    private List<RoomBooking> roomBookings;
    private int noOfGuests;

}