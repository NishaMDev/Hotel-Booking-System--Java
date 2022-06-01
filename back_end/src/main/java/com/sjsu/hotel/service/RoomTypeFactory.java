package com.sjsu.hotel.service;

import com.sjsu.hotel.resource.RoomType;

public class RoomTypeFactory {

    public static RoomType GetRoomType(String roomType, int gc , int rc) {
        RoomType roomtype;

        switch (roomType) {
            case "DBL":
                 roomtype = new RoomTypeDouble(gc , rc);
                 break;
            case "DLX":
                 roomtype = new RoomTypeDeluxe(gc, rc);
                 break;
            default:
                 roomtype = new RoomTypeSingle(gc, rc);
                 break;
            };
        return roomtype;
        }
    }

