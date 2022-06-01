package com.sjsu.hotel.repo;

import com.sjsu.hotel.domain.AddressEntity;
import com.sjsu.hotel.domain.HotelEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface AddressRepository extends PagingAndSortingRepository<AddressEntity,Integer> , QueryByExampleExecutor<AddressEntity> {
}
