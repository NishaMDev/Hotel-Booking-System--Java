package com.sjsu.hotel.repo;

 import com.sjsu.hotel.domain.RoomEntity;
 import org.springframework.data.jpa.repository.Query;
 import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface RoomRepository extends CrudRepository<RoomEntity,Integer> {
    Optional<List<RoomEntity>> findByHotelId(int id);

    @Query(value = "select * from rooms where hotel_id = ?3 and room_id not in ( select room_id from room_bookings where check_out_date >=   ?1 and check_in_date <=  ?2) limit 50", nativeQuery = true)
    List<RoomEntity> findAvailableRooms(String from,String to, String hotelId);


}

