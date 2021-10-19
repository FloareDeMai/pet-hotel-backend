package com.florentina.pethotel.hotel;

import com.florentina.pethotel.components.Address;
import com.florentina.pethotel.hotel.room.Room;
import com.florentina.pethotel.review.Review;
import lombok.*;

import javax.persistence.*;
import java.util.List;


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
    private Address address;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "petHotel")
    private List<Room> rooms;
    private boolean hasVeterinary;
    private double rating;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "petHotel")
    private List<Review> reviews;

    public PetHotel(String hotelName, String password, String email, String description, Address address, List<Room> rooms, boolean hasVeterinary, double rating, List<Review> reviews) {
        this.hotelName = hotelName;
        this.password = password;
        this.email = email;
        this.description = description;
        this.address = address;
        this.rooms = rooms;
        this.hasVeterinary = hasVeterinary;
        this.rating = rating;
        this.reviews = reviews;
    }

    public void addRoom(Room room){
        room.setPetHotel(this);
        rooms.add(room);
    }
}
