package com.florentina.pethotel.customer;

import com.florentina.pethotel.customer.enums.Gender;
import com.florentina.pethotel.components.Address;
import com.florentina.pethotel.security.roles.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
    @AttributeOverrides({
            @AttributeOverride(name ="city", column = @Column(name = "city")),
            @AttributeOverride(name ="country", column = @Column(name = "country")),
            @AttributeOverride(name ="street", column = @Column(name = "street")),
            @AttributeOverride(name ="number", column = @Column(name = "number")),
            @AttributeOverride(name ="zip", column = @Column(name = "zip"))
    })
    private Address address;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int age;
    private LocalDate joinedDate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

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

    public Customer(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
