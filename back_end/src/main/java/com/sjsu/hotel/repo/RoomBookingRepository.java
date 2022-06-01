package com.sjsu.hotel.repo;

import com.sjsu.hotel.domain.RoomBookingEntity;
import com.sjsu.hotel.domain.RoomEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource(exported = false)
public interface RoomBookingRepository extends CrudRepository<RoomBookingEntity,Integer> {

    RoomBookingEntity findById(int id);
    List<RoomBookingEntity> findByParentBookingId(int parentBookingId);

    @Transactional
    @Modifying
    @Query(value = "delete from room_bookings where parent_booking_id= ?1", nativeQuery = true)
    void deleteByParentBookingId(int parentBookingId);

    @Transactional
    @Modifying
    @Query(value = "delete from room_bookings where id= ?1", nativeQuery = true)
    void deleteByIdCustom(int id);


}
