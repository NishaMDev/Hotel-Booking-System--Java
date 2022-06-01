package com.sjsu.hotel.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="bookings")
@Getter
@Setter
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotNull(message = "Guests cannot be null")
    @Column(name = "no_of_guests",  nullable = false)
    private int noOfGuests;

    @Column(name="user_id")
    private String userId;

    @Column(name="status")
    private String status;

    @Column(name="amenities")
    private String amenities;

    @OneToMany(mappedBy = "parentBookingId")
    private List<RoomBookingEntity> roomBookingEntityList;

}
