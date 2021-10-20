package com.florentina.pethotel.review;

import com.florentina.pethotel.customer.Customer;
import com.florentina.pethotel.hotel.PetHotel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @SequenceGenerator(name = "review_sequence", sequenceName = "review_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_sequence")
    private Long id;
    private String title;
    private String description;
    private double rating;
    private LocalDateTime postedAt;
    private LocalDateTime editedAt;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private PetHotel petHotel;

}


//rezervarea o leg de camera si camera e legata de pet hotel
//manyTo one rez-> room
//onetomany hotel-> rooms