package com.sjsu.hotel.domain;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="persons")
@Getter
@Setter
@TypeDef(name = "json", typeClass = JsonStringType.class)

public class PersonEntity   {


    @Id
    @Column(name="person_id")
    private String id;

    @NotNull(message = "User name cannot be null")
    @Size(max = 50, message = "provide first name and last name")
    @Column(name = "person_name",length = 50, nullable = false)
    private String personName;


    @NotNull(message = "User Email id cannot be null")
    @Size(max = 500, message = "email description cannot be longer than 500 characters")
    @Column(name = "email", length = 500, nullable = false)
    private String email;

    @NotNull(message = "User Phone Number cannot be null")
    @Size(max = 100, message = "phone_number description cannot be longer than 500 characters")
    @Column(name = "phone_number", length = 100, nullable = false)
    private String phoneNumber;

    @OneToOne
    @NotNull(message = "Address cannot be null")
    @JoinColumn(name = "address_id", nullable = false)
    private AddressEntity address;

    @NotNull(message = "type cannot be null")
    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "points", nullable = false)
    private int points;

    @Column(name = "loyalty_grade", nullable = false)
    private String grade;


    @OneToMany
    @JoinColumn(name = "user_id", nullable = true, insertable = false,unique = false)
    private List<BookingEntity> bookingEntity;

}
