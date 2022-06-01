package com.sjsu.hotel.controller;

import com.sjsu.hotel.domain.RoomEntity;
import com.sjsu.hotel.resource.Room;
import com.sjsu.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/rooms")
public class RoomRestController {

    final private RoomService roomService;

    @Autowired
    public RoomRestController(RoomService roomService) {
        this.roomService = roomService;
    }


    /**
     * Update ROom
     *
     * @return the list am amenities added
     */
    @PostMapping(path = "/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity updateRoom( @Valid @RequestBody Room room) {

        roomService.updateRoom(room);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }


    /**
     * Get all room
     *
     * @return the list am amenities added
     */
    @GetMapping( )
    @ResponseStatus(HttpStatus.CREATED)
    public List<RoomEntity> getAllRooms( ) {
        return roomService.getAll();
    }




    /**
     * Get all available rooms
     *
     * @param
     * @return return a room or throws DataNotFoundException
     */
    @GetMapping("/avaiable")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RoomEntity>> getAvailableRooms(@RequestParam(name = "from") String from, @RequestParam(name = "to") String to, @RequestParam(name = "hotelId") String hotelId) {
        return new ResponseEntity(roomService.getAvailableRooms(from,to,hotelId), HttpStatus.OK);
    }

    /**
     * Get a room with id
     *
     * @param id the id of the room
     * @return return a room or throws DataNotFoundException
     */
    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RoomEntity> getRoom(@PathVariable("id") int id) {
        return new ResponseEntity<>(roomService.getRoom(id), HttpStatus.OK);
    }



    /**
     * Deletes a room wit id
     *
     * @param id the id of the room to delete
     * @return a status of NO_CONTENT if successful or NOT_FOUND if the room does not exist
     */
    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteRoom(@PathVariable("id") int id) {
        roomService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
