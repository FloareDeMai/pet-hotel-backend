package com.florentina.pethotel.booking;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.florentina.pethotel.customer.Customer;
import com.florentina.pethotel.hotel.room.HotelOffer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JsonIgnore
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "hotel_offer_id", nullable = false)
    @JsonIgnore
    private HotelOffer hotelOffer;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean done = false;
    private boolean cancelled = false;
    private boolean confirmed = false;


}
