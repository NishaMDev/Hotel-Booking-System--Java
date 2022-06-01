package com.sjsu.hotel.service;

import com.sjsu.hotel.domain.AddressEntity;
import com.sjsu.hotel.domain.PersonEntity;
import com.sjsu.hotel.repo.AddressRepository;
import com.sjsu.hotel.repo.PersonRepository;
import com.sjsu.hotel.resource.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;


@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, AddressRepository addressRepository) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
    }

    public PersonEntity createPerson(@Valid Person person) {
        PersonEntity personEntity = new PersonEntity();
        AddressEntity addressEntity = new AddressEntity();
        long addressId=0;

        if(person.getAddress().getId()!=null){
        addressId=person.getAddress().getId();
        }
        addressEntity.setId(addressId );
        addressEntity.setCity(person.getAddress().getCity());
        addressEntity.setCountry(person.getAddress().getCountry());
        addressEntity.setStreet(person.getAddress().getStreet());
        addressEntity.setZipcode(person.getAddress().getZipcode());
        AddressEntity updatedAddress = addressRepository.save(addressEntity);
        personEntity.setId(person.getId());
        addressEntity.setId(updatedAddress.getId());
        personEntity.setAddress(addressEntity);
        personEntity.setEmail(person.getEmail());
        personEntity.setPhoneNumber(person.getPhoneNumber());
        personEntity.setType(person.getType());
        personEntity.setPersonName(person.getPersonName());

        return personRepository.save(personEntity);

    }

    public Page<PersonEntity> getAll(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    public Optional<PersonEntity> getPerson(String id) {
        Optional<PersonEntity> personEntity=personRepository.findById(id);
        return personEntity;
    }
}
