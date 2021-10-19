package com.florentina.pethotel.booking;

import com.florentina.pethotel.customer.Customer;
import com.florentina.pethotel.hotel.PetHotel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private PetHotel petHotel;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean done = false;
    private boolean cancelled = false;
    private boolean confirmed = false;




}
