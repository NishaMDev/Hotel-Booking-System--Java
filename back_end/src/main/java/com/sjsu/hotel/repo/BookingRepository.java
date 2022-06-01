package com.sjsu.hotel.repo;

import com.sjsu.hotel.domain.BookingEntity;
import com.sjsu.hotel.domain.RoomBookingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface BookingRepository extends CrudRepository<BookingEntity,Integer> {

}
