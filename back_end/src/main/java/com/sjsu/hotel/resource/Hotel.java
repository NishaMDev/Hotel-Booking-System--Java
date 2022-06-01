package com.sjsu.hotel.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Hotel {
    private int id;
    @NotNull(message = "Hotel name cannot be null")
    @Size(max = 50, message = "Hotel name cannot be longer than 50 characters")
    private String name;
    @NotNull(message = "Hotel description cannot be null")
    @Size(max = 500, message = "Hotel description cannot be longer than 500 characters")
    private String description;
    @NotNull(message = "Address cannot be null")
    private Address address;
    @NotNull(message = "Hotel email cannot be null")
    @Size(max = 500, message = "Hotel email cannot be longer than 500 characters")
    private String email;

    @NotNull(message = "Hotel phone cannot be null")
    @Size(max = 500, message = "Hotel phone cannot be longer than 500 characters")
    private String phone;
    public Hotel() {
    }

    public Hotel(int id, @NotNull(message = "Hotel name cannot be null") @Size(max = 50, message = "Hotel name cannot be longer than 50 characters") String name, @NotNull(message = "Hotel description cannot be null") @Size(max = 500, message = "Hotel description cannot be longer than 500 characters") String description, @NotNull(message = "Address cannot be null")   Address address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
    }


}
