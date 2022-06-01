package com.sjsu.hotel.repo;

import com.sjsu.hotel.domain.HotelAmenityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface HotelAmenityRepository extends CrudRepository<HotelAmenityEntity,Integer> {
    List<HotelAmenityEntity> findByHotelId(int hotelId);
}
