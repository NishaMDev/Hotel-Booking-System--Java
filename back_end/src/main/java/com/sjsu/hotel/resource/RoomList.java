package com.sjsu.hotel.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class RoomList {
    @Valid
    @NotNull
    private List<Room> rooms;

    public RoomList() {
    }

    public RoomList(List<Room> Rooms) {
        this.rooms = Rooms;
    }


}
