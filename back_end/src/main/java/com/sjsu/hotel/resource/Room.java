package com.sjsu.hotel.resource;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class Room {
    @NotNull(message = "Room description cannot be null")
    @Size(max = 256, message = "Room description cannot be longer that 50 characters")
    private String description;




    private int id;

    @NotNull(message = "Room Short Description cannot be null")

    private int price;
    private int hotelId;
    private String type;

}
