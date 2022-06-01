package com.sjsu.hotel.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sjsu.hotel.resource.Address;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="hotels")
@Getter
@Setter
@TypeDef(name = "json", typeClass = JsonStringType.class)

public class HotelEntity   {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hotel_id")
    private int id;

    @NotNull(message = "Hotel name cannot be null")
    @Size(max = 50, message = "Hotel name cannot be longer than 50 characters")
    @Column(name = "hotel_name",length = 50, nullable = false)
    private String name;


    @NotNull(message = "Hotel description cannot be null")
    @Size(max = 500, message = "Hotel description cannot be longer than 500 characters")
    @Column(name = "description", length = 500, nullable = false)
    private String description;

    @OneToOne
    @NotNull(message = "Address cannot be null")
    @JoinColumn(name = "address_id", nullable = false)
    private AddressEntity address;

    @OneToMany(mappedBy = "hotelId")
    private List<RoomEntity> rooms;

    @NotNull(message = "Hotel email cannot be null")
    @Size(max = 500, message = "Hotel email cannot be longer than 500 characters")
    @Column(name = "email", length = 500, nullable = false)
    private String email;

    @NotNull(message = "Hotel phone cannot be null")
    @Size(max = 500, message = "Hotel phone cannot be longer than 500 characters")
    @Column(name = "phone", length = 500, nullable = false)
    private String phone;

    @OneToMany(mappedBy = "hotelId")
    private List<HotelAmenityEntity> amenities;


}

