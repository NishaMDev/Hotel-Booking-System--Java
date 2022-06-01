package com.sjsu.hotel.controller;

import com.sjsu.hotel.controller.error.ApiError;
import com.sjsu.hotel.controller.error.ValidationError;
import com.sjsu.hotel.domain.BookingEntity;
import com.sjsu.hotel.domain.HotelEntity;
import com.sjsu.hotel.domain.RoomBookingEntity;
import com.sjsu.hotel.resource.Booking;
import com.sjsu.hotel.resource.RoomBooking;
import com.sjsu.hotel.service.BookingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/bookings")
public class BookingRestController {

    final private BookingService bookingService;

    @Autowired
    public BookingRestController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Creates a Booking
     *
     * @return the created booking
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)

    @ApiOperation(value="Create a new Booking",response= HotelEntity.class)
    @ApiResponses(value={
            @ApiResponse(code=201,message="Booking Created",response=HotelEntity.class),
            @ApiResponse(code=400,message="Validation Error",response= ValidationError.class),
            @ApiResponse(code=404,message="Not Found", response = ApiError.class),
            @ApiResponse(code=500,message="Internal Server Error", response = ApiError.class)
    })
    public ResponseEntity<BookingEntity> newBooking(  @RequestBody @ApiParam(name = "Booking",value = "Room Bookings Resource",required = true) Booking booking
                                                              ) {
        return new ResponseEntity<>(bookingService.createBooking(booking), HttpStatus.CREATED);

    }

    /**
     * Get  Bookings with Parent Booking id
     *
     * @param parentBookingId the id of the  parent booking
     * @return return a booking or throws DataNotFoundException
     */
    @GetMapping(path = "{parentBookingId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RoomBookingEntity>> getBookings(@PathVariable("parentBookingId") int parentBookingId) {
        return new ResponseEntity<>(bookingService.getBookings(parentBookingId), HttpStatus.OK);
    }

    /**
     * Deletes a booking wit booking id
     *
     * @param id the id of the booking to delete
     * @return a status of NO_CONTENT if successful or NOT_FOUND if the booking does not exist
     */
    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteBooking(@PathVariable("id") int id) {
        bookingService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /**
     * Deletes a room booking with id
     *
     * @param id the id of the booking to delete
     * @return a status of NO_CONTENT if successful or NOT_FOUND if the booking does not exist
     */
    @DeleteMapping(path = "/room/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteRoomBooking(@PathVariable("id") int id) {
        bookingService.deleteRoom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}