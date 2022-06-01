package com.sjsu.hotel.resource;

import com.sjsu.hotel.domain.RoomEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class RoomBooking {
    private Long id;

    @NotNull(message = "CheckIn Date cannot be null")
    @Size(max = 10, message = "")
    private String checkInDate;

    @NotNull(message = "CheckOut Date cannot be null")
    @Size(max = 10, message = "")
    private String checkOutDate;

    @NotNull(message = "no of guests Date cannot be null")
    private int numberOfGuests;

    private Long roomId;

    private int parentBookingId;
}