package com.florentina.pethotel.customer;

import com.florentina.pethotel.customer.enums.Gender;
import com.florentina.pethotel.components.Address;
import com.florentina.pethotel.hotel.PetHotel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    @Embedded
    private Address address;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int age;


    public Customer(String firstName, String lastName, String username, String email, String password, Address address, String phoneNumber, Gender gender, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.age = age;
    }
}
