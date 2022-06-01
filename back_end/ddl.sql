create table address
(
    id      int auto_increment
        primary key,
    street  varchar(255) null,
    city    varchar(255) null,
    country varchar(255) null,
    zipcode varchar(255) null
)
    auto_increment = 13;

create table bookings
(
    id           int auto_increment
        primary key,
    user_id      varchar(200) null,
    status       varchar(200) null,
    amenities    varchar(400) null,
    no_of_guests int          null,
    constraint bookings_id_uindex
        unique (id)
)
    auto_increment = 8;

create table hotel_amenities
(
    hotel_amenity_id int auto_increment
        primary key,
    hotel_id         int          null,
    chargeable       tinyint(1)   null,
    description      varchar(200) null,
    price            int          null,
    name             varchar(200) null
)
    auto_increment = 4;

create table hotels
(
    hotel_id    int auto_increment
        primary key,
    hotel_name  varchar(255) null,
    description varchar(255) null,
    email       varchar(255) null,
    address_id  varchar(200) null,
    phone       varchar(255) null
)
    auto_increment = 4;

create table persons
(
    person_id    varchar(200) not null
        primary key,
    person_name  varchar(255) null,
    email        varchar(200) null,
    phone_number varchar(200) null,
    address_id   varchar(200) null,
    type         varchar(200) null,
    constraint persons_person_id_uindex
        unique (person_id)
);

create table room_bookings
(
    id                int auto_increment
        primary key,
    check_in_date     date null,
    check_out_date    date null,
    room_id           int  null,
    parent_booking_id int  not null,
    constraint bookings_id_uindex
        unique (id)
)
    auto_increment = 6;

create table rooms
(
    room_id     int auto_increment
        primary key,
    hotel_id    int          null,
    description varchar(255) null,
    type        varchar(200) null,
    price       int          null
)
    auto_increment = 13;

