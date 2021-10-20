package com.florentina.pethotel.hotel;

import com.florentina.pethotel.components.Address;
import com.florentina.pethotel.hotel.room.HotelOffer;
import com.florentina.pethotel.review.Review;
import com.florentina.pethotel.security.roles.Role;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PetHotel {
    @Id
    @SequenceGenerator(name = "hotel_sequence", sequenceName = "hotel_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_sequence")
    private Long id;
    private String hotelName;
    @Column(length = 6000)
    private String pictureLink;
    private String password;
    private String email;
    private boolean isActive;
    @Column(length = 5000)
    private String description;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name ="city", column = @Column(name = "city")),
            @AttributeOverride(name ="country", column = @Column(name = "country")),
            @AttributeOverride(name ="street", column = @Column(name = "street")),
            @AttributeOverride(name ="number", column = @Column(name = "number")),
            @AttributeOverride(name ="zip", column = @Column(name = "zip"))
    })
    private Address address;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "petHotel")
    private List<HotelOffer> hotelOffers;
    private boolean hasVeterinary;
    private double rating;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "petHotel")
    private List<Review> reviews;
    private LocalDate joinedDate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "hotel_roles",
            joinColumns = @JoinColumn(name = "pet_hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public PetHotel(String hotelName, String password, String email, String description, Address address, List<HotelOffer> hotelOffers, boolean hasVeterinary, double rating, List<Review> reviews) {
        this.hotelName = hotelName;
        this.password = password;
        this.email = email;
        this.description = description;
        this.address = address;
        this.hotelOffers = hotelOffers;
        this.hasVeterinary = hasVeterinary;
        this.rating = rating;
        this.reviews = reviews;
    }

    public PetHotel(String hotelName, String password, String email) {
        this.hotelName = hotelName;
        this.password = password;
        this.email = email;
        this.isActive = false;
    }
}
