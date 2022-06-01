package com.sjsu.hotel.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="room_bookings")
@Getter
@Setter
public class RoomBookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotNull(message = "CheckIn Date cannot be null")
    @Column(name = "check_in_date",length = 50, nullable = false)
    private Date checkInDate;

    @NotNull(message = "CheckOut Date cannot be null")
    @Column(name = "check_out_date",length = 50, nullable = false)
    private Date check_out_date;

    @NotNull(message = "Room id cannot be null")
    @Column(name = "room_id",  nullable = false)
    private Long roomId;

    @NotNull(message = "parent_booking_id id cannot be null")
    @Column(name = "parent_booking_id",  nullable = false)
    private int parentBookingId;
//
//    @ManyToMany (mappedBy = "roomId")
//    private List<RoomEntity> rooms;

}
