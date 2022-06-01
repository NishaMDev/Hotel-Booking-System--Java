package com.sjsu.hotel.resource;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@Data
@NoArgsConstructor
public class Address {
    private Long id;
     private String street;
     private String city;
     private String country;
     private String zipcode;
}
