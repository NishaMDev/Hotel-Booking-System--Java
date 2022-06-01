package com.sjsu.hotel.controller;//package com.sjsu.hotel.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import com.sjsu.hotel.domain.AddressEntity;
//import com.sjsu.hotel.domain.HotelEntity;
//
//import com.sjsu.hotel.resource.HotelSearchParam;
//import com.sjsu.hotel.resource.Room;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.core.Is.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest()
//@AutoConfigureMockMvc
//public class HotelRestControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//    private static boolean setUpIsDone = false;
//
//
//
//    @Before
//    public void resetState(){
//        if (!setUpIsDone) {
//            jdbcTemplate.update("CREATE domain IF NOT EXISTS jsonb AS other;");
//            jdbcTemplate.update("create schema hotelapi; create table hotelapi.address ( id      int auto_increment primary key, street  varchar(255) null, city    varchar(255) null, county  varchar(255) null, zipcode varchar(255) null ); create table hotelapi.amenity ( amenity_id  int auto_increment primary key, hotel_id    int          null, short_desc  varchar(10)  null, description varchar(200) null, price       int          null, chargeable  int          null ); create table hotelapi.hotels ( hotel_id    int auto_increment primary key, hotel_name  varchar(255) null, description varchar(255) null, address_id  varchar(200) null, contact     json         null ); create table hotelapi.room_amenities ( room_amenity_id int auto_increment primary key, room_id         int null, amenity_id      int null ); create table hotelapi.rooms ( room_id     int auto_increment primary key, hotel_id    int          null, description varchar(255) null, type        varchar(200) null, status      varchar(200) null, price       int          null, details     json         null ); create table hotelapi.person ( person_id    int auto_increment primary key, person_name  varchar(255) null, email        varchar(200) null, phone_number varchar(200) null, account_type varchar(200) null, address_id   varchar(200) null ); create table hotelapi.account ( account_id     int auto_increment primary key, password       varchar(200) null, account_status varchar(200) null, loyalty_status varchar(200) null ); create table hotelapi.bookings ( booking_id     int auto_increment primary key, start_date     varchar(200) null, duration       varchar(200) null, status         varchar(200) null, check_in_date  varchar(200) null, check_out_date varchar(200) null, no_of_guests   int          null );");
//            setUpIsDone = true;
//        }
//
//        jdbcTemplate.update("use hotelapi"); }
//
//
//
//    private String toJSon(Object object) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.writeValueAsString(object);
//    }
//
//
////    private AmenityList createAmenities(int count) {
////        List<Amenity> amenities = new ArrayList<>();
////        for(int i=0;i<count;i++){
////            Amenity amenity = new Amenity();
////            amenity.setShortDesc("cod"+i);
////            amenity.setDescription("desc"+i);
////            amenity.setChargeable(true);
////            amenity.setAmount(BigDecimal.valueOf(100));
////            amenities.add(amenity);
////        }
////        return new AmenityList(amenities);
////    }
////
////    private void createAmenitiesForRoom(int roomId, int count) throws Exception {
////        AmenityList amenityList = createAmenities(count);
////        mvc.perform(post("/api/rooms/"+roomId+"/amenities")
////                        .contentType(MediaType.APPLICATION_JSON).content(toJSon(amenityList)))
////                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
////                .andExpect(jsonPath("$", hasSize(count)))
////                .andExpect(status().isCreated());
////    }
//
//    void createHotel() throws Exception {
//        HotelEntity hotelEntity = new HotelEntity();
//        hotelEntity.setName("Hotel 1");
//        hotelEntity.setDescription("Hotel Description 1");
//        AddressEntity address= new AddressEntity();
//        address.setZipcode("test");
//        address.setCountry("test");
//        address.setZipcode("test");
//        address.setCity("test");
//
//        hotelEntity.setAddress( address);
//
//        String json = toJSon(hotelEntity);
//        mvc.perform(post("/api/hotels")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                    .andExpect(status().isCreated());
//    }
//
//    @Test
//    public void testCreateHotel() throws Exception {
//        HotelEntity hotelEntity = new HotelEntity();
//        hotelEntity.setName("Hotel 1");
//        hotelEntity.setDescription("Hotel Description 1");
//        AddressEntity address= new AddressEntity();
//        address.setZipcode("test");
//        address.setCountry("test");
//        address.setZipcode("test");
//        address.setCity("test");
//
//        hotelEntity.setAddress( address);
//
//        String json = toJSon(hotelEntity);
//        mvc.perform(post("/api/hotels")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(jsonPath("$.name", is("Hotel 1")))
//                .andExpect(jsonPath("$.description", is("Hotel Description 1")))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    public void testAddRoomToHotel() throws Exception {
//        createHotel();
//        Room room = new Room();
//        room.setDescription("Description");
//
//         mvc.perform(post("/api/hotels/1/rooms")
//                        .contentType(MediaType.APPLICATION_JSON).content(toJSon(room)))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.description", is("Description")))
//                .andExpect(jsonPath("$.hotelId", is(1)))
//                .andExpect(status().isCreated());
//    }
//
//
//    @Test
//    public void testCreateHotel_With_Invalid_Data() throws Exception {
//        HotelEntity hotelEntity = new HotelEntity();
//
//        String json = toJSon(hotelEntity);
//        mvc.perform(post("/api/hotels")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(jsonPath("$.message", is("Validation failed for hotel")))
//                .andExpect(jsonPath("$.details.name", is("Hotel name cannot be null")))
//                .andExpect(jsonPath("$.details.description", is("Hotel description cannot be null")))
//                .andExpect(jsonPath("$.path", is("/api/hotels")))
//                .andExpect(status().isBadRequest());
//    }
//
//
//    @Test
//    public void testGetHotels() throws Exception {
//        createHotel();
//
//        mvc.perform(get("/api/hotels")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testDeleteHotel() throws Exception {
//        createHotel();
//        mvc.perform(delete("/api/hotels/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }
////
////    @Test
////    public void testAddAmenitiesToRoom() throws Exception {
////        createHotel();
////        AmenityList amenityList = createAmenities(3);
////        mvc.perform(post("/api/rooms/2/amenities")
////                        .contentType(MediaType.APPLICATION_JSON).content(toJSon(amenityList)))
////                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
////                .andExpect(jsonPath("$", hasSize(3)))
////             .andExpect(status().isCreated());
////    }
//
////
////    @Test
////    public void testAddAmenitiesToRoom_With_Invalid_Data() throws Exception {
////        createHotel();
////          AmenityList amenityList = createAmenities(3);
////        amenityList.getItems().get(0).setShortDesc(null);
////        mvc.perform(post("/api/rooms/1/amenities")
////                        .contentType(MediaType.APPLICATION_JSON).content(toJSon(amenityList)))
////                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
////                .andExpect(jsonPath("$.message", is("Validation failed for amenityList")))
////                .andExpect(jsonPath("$.path", is("/api/rooms/1/amenities")))
////                .andExpect(status().isBadRequest());
////    }
////
////
////    @Test
////    public void testGetAmenitiesForRoom() throws Exception {
////
////        createAmenitiesForRoom(1,2);
////        mvc.perform(get("/api/rooms/2/amenities")
////                        .contentType(MediaType.APPLICATION_JSON))
////                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
////    .andExpect(status().isOk());
////    }
//
//
//    @Test
//    public void testDeleteRoomAmenity() throws Exception {
//
//         mvc.perform(delete("/api/rooms/amenities/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }
//
//}