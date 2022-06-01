package com.sjsu.hotel.resource;


import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Person {
    private String id;

    @NotNull(message = "user name cannot be null")
    @Size(max = 50, message = "user name cannot be longer than 50 characters")
    private String personName;

    @NotNull(message = "user email cannot be null")
    @Size(max = 500, message = "user email cannot be longer than 50 characters")
    private String email;

    @NotNull(message = "User Phone number cannot be null")
    @Size(max = 10, message = "User Phone number should be numeric")
    private String phoneNumber;
    @NotNull(message = "Address cannot be null")
    private Address address;
    @NotNull(message = "type cannot be null")
    @Size(max = 20, message = "types should be valid")
    private String type;

     private int points;

     private String grade;
}