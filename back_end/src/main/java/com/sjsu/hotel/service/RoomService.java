package com.sjsu.hotel.service;

  import com.sjsu.hotel.domain.RoomEntity;
  import com.sjsu.hotel.repo.RoomRepository;
 import com.sjsu.hotel.resource.Room;
import com.sjsu.hotel.controller.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
  import org.springframework.http.ResponseEntity;
  import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository    ) {
        this.roomRepository = roomRepository;
     }

    public RoomEntity createRoom(int hotelId, Room roomResource) {
        try {
            RoomEntity room = new RoomEntity();
            room.setHotelId(hotelId);
            room.setDescription(roomResource.getDescription());
             room.setType(roomResource.getType());
            room.setPrice(roomResource.getPrice());

            return roomRepository.save(room);
        }catch (DataIntegrityViolationException e){
            throw new DataNotFoundException("Hotel not found",hotelId);
        }

    }

    public RoomEntity getRoom(int id) {
        return roomRepository.findById(id).orElseThrow(()-> new DataNotFoundException("Room not found",id));
    }

    public List<RoomEntity> getRoomsForHotel(int id) {
        return roomRepository.findByHotelId(id).orElseThrow(()-> new DataNotFoundException("Rooms not found for hotel",id));
    }

    public void delete(int id) {
        roomRepository.deleteById(id);
    }




    public void updateRoom( Room room) {
        RoomEntity roomEntity= entityToResource(room);
         roomRepository.save(roomEntity);
    }


    public RoomEntity entityToResource(Room room){

        RoomEntity roomEntity= new RoomEntity();
        roomEntity.setId(room.getId());
        roomEntity.setType(room.getType());
         roomEntity.setDescription(room.getDescription());
        roomEntity.setHotelId(room.getHotelId());
        roomEntity.setPrice(room.getPrice());
    return roomEntity;
    }

    public List<RoomEntity> getAvailableRooms(String from, String to, String hotelId) {
        return roomRepository.findAvailableRooms(from,to, hotelId);
    }

    public List<RoomEntity> getAll() {
        return (List<RoomEntity>) roomRepository.findAll();
    }
}
