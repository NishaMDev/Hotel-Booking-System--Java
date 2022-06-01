package com.sjsu.hotel.service;


import com.sjsu.hotel.domain.*;
import com.sjsu.hotel.repo.BookingRepository;
import com.sjsu.hotel.repo.PersonRepository;
import com.sjsu.hotel.repo.RoomBookingRepository;
import com.sjsu.hotel.repo.RoomRepository;
import com.sjsu.hotel.resource.Booking;
import com.sjsu.hotel.resource.RoomBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final RoomBookingRepository roomBookingRepository;
    private final BookingRepository bookingRepository;

    private final RoomRepository roomRepository;

    private final PersonRepository personRepository;

    @Autowired
    public BookingService(RoomBookingRepository roomBookingRepository, PersonRepository personRepository, RoomRepository roomRepository, BookingRepository bookingRepository) {
        this.roomBookingRepository = roomBookingRepository;
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.personRepository = personRepository;
    }


    public BookingEntity createBooking(Booking booking) {


        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setUserId(booking.getUserId());
        bookingEntity.setAmenities(booking.getAmenities());
        bookingEntity.setStatus(booking.getStatus());
        bookingEntity.setNoOfGuests(booking.getNoOfGuests());

        BookingEntity updated = bookingRepository.save(bookingEntity);


        for (RoomBooking roomBooking : booking.getRoomBookings()) {
            RoomBookingEntity roomBookingEntity = new RoomBookingEntity();
            roomBookingEntity.setParentBookingId(updated.getId());
            roomBookingEntity.setCheckInDate(Date.valueOf(roomBooking.getCheckInDate()));
            roomBookingEntity.setCheck_out_date(Date.valueOf(roomBooking.getCheckOutDate()));
            roomBookingEntity.setId(roomBooking.getId());
            roomBookingEntity.setRoomId(roomBooking.getRoomId());
            roomBookingRepository.save(roomBookingEntity);
        }

        PersonEntity personEntity = personRepository.findById(booking.getUserId()).orElse(new PersonEntity());
        ;
        int points = personEntity.getPoints();

        String grade;

        if (personEntity.getPoints() > 10000) {
            grade = "gold";
        } else if (personEntity.getPoints() > 100000) {
            grade = "platinum";
        } else {
            grade = "silver";
        }

        if (grade == "gold") {
            points += 1000;
        } else if (grade == "platinum") {
            points += 5000;
        } else {
            points += 100;
        }
        personEntity.setPoints(points);
        if (personEntity.getPoints() > 10000) {
            grade = "gold";
        } else if (personEntity.getPoints() > 100000) {
            grade = "platinum";
        } else {
            grade = "silver";
        }
        personEntity.setGrade(grade);
        personRepository.save(personEntity);
        return bookingEntity;
    }

    public List<RoomBookingEntity> getBookings(int bookingId) {
        return roomBookingRepository.findByParentBookingId(bookingId);
    }


    public void delete(int id) {
        roomBookingRepository.deleteByParentBookingId(id);
        bookingRepository.deleteById(id);

    }

    public void deleteRoom(int id) {
        roomBookingRepository.deleteByIdCustom(id);
    }
}
