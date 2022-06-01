package com.sjsu.hotel.repo;

import com.sjsu.hotel.domain.PersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface PersonRepository extends PagingAndSortingRepository<PersonEntity,String>, QueryByExampleExecutor<PersonEntity> {

    List<PersonEntity> getAllByType(String type);

}
