package com.sjsu.hotel.controller;

import com.sjsu.hotel.domain.HotelAmenityEntity;
import com.sjsu.hotel.resource.*;
import com.sjsu.hotel.service.HotelService;
import com.sjsu.hotel.controller.error.ApiError;
import com.sjsu.hotel.controller.error.ValidationError;
import com.sjsu.hotel.domain.HotelEntity;
import com.sjsu.hotel.domain.RoomEntity;
import com.sjsu.hotel.service.RoomService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/hotels", produces = {"application/json"})

@Api(value="/api/hotels",description="Hotels Resource",produces ="application/json")
public class HotelRestController {

    private HotelService hotelService;
    private RoomService roomService;

    @Autowired
    public HotelRestController(HotelService hotelService, RoomService roomService) {
        this.hotelService = hotelService;
        this.roomService = roomService;
    }

    /**
     * Creates a Hotel
     *
     * @param hotel the hotel data
     * @return the created hotel and a status of CREATED
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    @ApiOperation(value="Create a new Hotel",response=HotelEntity.class)
    @ApiResponses(value={
            @ApiResponse(code=201,message="Hotel Created",response=HotelEntity.class),
            @ApiResponse(code=400,message="Validation Error",response= ValidationError.class),
            @ApiResponse(code=404,message="Not Found", response = ApiError.class),
            @ApiResponse(code=500,message="Internal Server Error", response = ApiError.class)
    })
    public ResponseEntity<HotelEntity> newHotel(@Valid @RequestBody @ApiParam(name = "Hotel",value = "Hotel Resource",required = true) Hotel hotel) {
        return new ResponseEntity<>(hotelService.createHotel(hotel), HttpStatus.CREATED);

    }




    /**
     * Search for hotels by name, cityCode and description
     * the search parameters is case insensitive and also will match on contains
     *
     * @param param    the search parameter
     * @param pageable the paging data
     * @return the list of Hotels that match the parameter
     */
    @PostMapping(path = "search")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<HotelEntity>> SearchHotel(@RequestBody HotelSearchParam param, Pageable pageable) {
        return new ResponseEntity<>(hotelService.search(param, pageable), HttpStatus.OK);

    }

    /**
     * Get all hotels
     *
     * @param pageable the paging data
     * @return the list of hotels
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<HotelEntity>> GetAll(Pageable pageable) {
        return new ResponseEntity<>(hotelService.getAll(pageable), HttpStatus.OK);
    }

    /**
     * Delete an hotel by id
     *
     * @param id the id of the hotel to delete
     * @return returns NO_CONTENT if delete is successfull otherwise it will throw EmptyResultDataAccessException is the hotel does not exist
     */

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteHotel(@PathVariable("id") int id) {
        hotelService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    /**
     * Creates a Room for a given hotel
     * will throw DataNotFound if the hotel for the room is not found
     *
     * @param hotelId the id of the hotel
     * @param roomResource the room data
     * @return the created room and a status of CREATED
     */
    @PostMapping(path = "{hotelId}/rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RoomEntity> createRoom(@PathVariable("hotelId") int hotelId, @Valid @RequestBody Room roomResource) {
        return new ResponseEntity<>(roomService.createRoom(hotelId,roomResource), HttpStatus.CREATED);
    }


    /**
     * Get all the rooms for a hotel with  id
     *
     * @param id the id of the hotel
     * @return list of rooms
     */
    @GetMapping(path = "{hotelId}/rooms")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RoomEntity>> getRoomsForHotel(@PathVariable("hotelId") int id) {
        return new ResponseEntity<>(roomService.getRoomsForHotel(id), HttpStatus.OK);
    }
    /**
     * Add amenities to a hotel
     *
     * @param hotelAmenity
     * @return the list am amenities added
     */
    @PostMapping(path = "amenities")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HotelAmenityEntity> addAmenity( @Valid @RequestBody HotelAmenity hotelAmenity) {

        return new ResponseEntity<>(hotelService.addAmenity( hotelAmenity), HttpStatus.CREATED);
    }

    /**
     * Get the list of amenities for a hotel, this will throw a EmptyResultDataAccessException if the room does not exist
     *
     * @param hotelId the id of the room
     * @return the list of the added room amenities
     */
    @GetMapping(path = "{roomId}/amenities")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HotelAmenityEntity>> getAmenities(@PathVariable("hotelId") int hotelId) {
        return new ResponseEntity<>(hotelService.getAmenities(hotelId), HttpStatus.OK);

    }

    /**
     * Delete a hotel amenity this will throw a EmptyResultDataAccessException if the room amenity does not exist
     *
     * @param id the id of the room amenity
     * @return NO_CONTENT status code
     */
    @DeleteMapping(path = "amenities/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteRoomAmenity( @PathVariable("id") int id) {
        hotelService.deleteHotelAmenity(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);

    }

}
