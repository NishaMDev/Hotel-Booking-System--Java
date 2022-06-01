package com.sjsu.hotel.service;

import com.sjsu.hotel.domain.AddressEntity;
 import com.sjsu.hotel.domain.HotelAmenityEntity;
import com.sjsu.hotel.domain.HotelEntity;
import com.sjsu.hotel.repo.AddressRepository;
 import com.sjsu.hotel.repo.HotelAmenityRepository;
import com.sjsu.hotel.repo.HotelRepository;
import com.sjsu.hotel.resource.Hotel;
import com.sjsu.hotel.resource.HotelAmenity;
import com.sjsu.hotel.resource.HotelSearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
     private final AddressRepository addressRepository;
    private final HotelAmenityRepository hotelAmenityRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository,  HotelAmenityRepository hotelAmenityRepository, AddressRepository addressRepository) {
        this.hotelRepository = hotelRepository;
         this.addressRepository=addressRepository;
        this.hotelAmenityRepository=hotelAmenityRepository;
    }

    public HotelEntity createHotel(@Valid Hotel hotel) {
        HotelEntity hotelEntity = new HotelEntity();
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(hotel.getAddress().getCity());
        addressEntity.setCountry(hotel.getAddress().getCountry());
        addressEntity.setId(hotel.getAddress().getId());
        addressEntity.setStreet(hotel.getAddress().getStreet());
        addressEntity.setZipcode(hotel.getAddress().getZipcode());


        try {
            hotelEntity.setAddress(addressEntity);
        } catch (Exception e) {
            System.out.println("error parsing" + e);
        }
        hotelEntity.setName(hotel.getName());
        hotelEntity.setDescription(hotel.getDescription());
        hotelEntity.setEmail(hotel.getEmail());
        hotelEntity.setPhone(hotel.getPhone());

        addressRepository.save(addressEntity);
        return hotelRepository.save(hotelEntity);

    }


    public Page<HotelEntity> search(HotelSearchParam param, Pageable pageable) {
        HotelEntity hotelEntity = new HotelEntity();
        hotelEntity.setName(param.getName());
         AddressEntity addressEntity=new AddressEntity();
        addressEntity.setCity(param.getAddress().getCity());
        addressEntity.setCountry(param.getAddress().getCountry());
        addressEntity.setId(param.getAddress().getId());
        addressEntity.setStreet(param.getAddress().getStreet());

        hotelEntity.setAddress(addressEntity);
        hotelEntity.setDescription(param.getDescription());
        Example<HotelEntity> example = Example.of(hotelEntity, ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withIgnorePaths("id")
                .withIgnoreNullValues()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING))
                .withMatcher("description", ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING)));
        return hotelRepository.findAll(example, pageable);

    }

    public Page<HotelEntity> getAll(Pageable pageable) {
        return hotelRepository.findAll(pageable);
    }

    public void delete(int id) {
        hotelRepository.deleteById(id);
    }
 

    public List<HotelAmenityEntity> getAmenities(int roomId) {
        return hotelAmenityRepository.findByHotelId(roomId);
    }

    public void deleteHotelAmenity(int id) {
        hotelAmenityRepository.deleteById(id);
    }


    public HotelAmenityEntity addAmenity(@Valid HotelAmenity hotelAmenity) {

        HotelAmenityEntity hotelAmenityEntity = new HotelAmenityEntity();
        hotelAmenityEntity.setHotelId(hotelAmenity.getHotelId());
        hotelAmenityEntity.setPrice(hotelAmenity.getPrice());
        hotelAmenityEntity.setDescription(hotelAmenity.getDescription());
        hotelAmenityEntity.setChargeable(hotelAmenity.isChargeable());
        hotelAmenityEntity.setName(hotelAmenity.getName());

        hotelAmenityRepository.save(hotelAmenityEntity);
        return hotelAmenityEntity;
     }
}
