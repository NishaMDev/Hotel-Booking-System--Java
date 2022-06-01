package com.sjsu.hotel.controller;

import com.sjsu.hotel.controller.error.ApiError;
import com.sjsu.hotel.controller.error.ValidationError;
import com.sjsu.hotel.domain.HotelEntity;
import com.sjsu.hotel.domain.PersonEntity;
import com.sjsu.hotel.domain.RoomEntity;
import com.sjsu.hotel.resource.Person;
import com.sjsu.hotel.service.PersonService;
import com.sjsu.hotel.service.RoomService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/person")
public class PersonRestController {

    final private PersonService personService;

    @Autowired
    public PersonRestController(RoomService roomService, PersonService personService) {
        this.personService = personService;
    }

    /**
     * Creates a User
     *
     * @param person the User data
     * @return the created User and a status of CREATED
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    @ApiOperation(value="Create a new User",response= PersonEntity.class)
    @ApiResponses(value={
            @ApiResponse(code=201,message="User Created",response=PersonEntity.class),
            @ApiResponse(code=400,message="Validation Error",response= ValidationError.class),
            @ApiResponse(code=404,message="Not Found", response = ApiError.class),
            @ApiResponse(code=500,message="Internal Server Error", response = ApiError.class)
    })
    public ResponseEntity<PersonEntity> newPerson(@Valid @RequestBody @ApiParam(name = "Person",value = "Person Resource",required = true) Person person) {
        return new ResponseEntity<>(personService.createPerson(person), HttpStatus.CREATED);

    }


    /**
     * Get all
     *
     * @param pageable the paging data
     * @return the list of hotels
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<PersonEntity>> GetAll(Pageable pageable) {
        return new ResponseEntity<>(personService.getAll(pageable), HttpStatus.OK);
    }




    /**
     * Get with id
     *
     * @param id the id
     * @return return a person or throws DataNotFoundException
     */
    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PersonEntity> getPerson(@PathVariable("id") String id) {
        return new ResponseEntity(personService.getPerson(id), HttpStatus.OK);
    }

}
