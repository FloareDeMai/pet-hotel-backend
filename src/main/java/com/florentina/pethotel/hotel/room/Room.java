package com.florentina.pethotel.hotel.room;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.florentina.pethotel.booking.Reservation;
import com.florentina.pethotel.hotel.PetHotel;
import com.florentina.pethotel.hotel.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    private int totalRooms;
    private int walkingPerDay;
    private int mealsPerDay;
    private int pricePerDay;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    private List<Reservation> reservations;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    @JsonIgnore
    private PetHotel petHotel;

    public Room(RoomType roomType, int totalRooms, int walkingPerDay, int mealsPerDay, int pricePerDay, List<Reservation> reservations, PetHotel petHotel) {
        this.roomType = roomType;
        this.totalRooms = totalRooms;
        this.walkingPerDay = walkingPerDay;
        this.mealsPerDay = mealsPerDay;
        this.pricePerDay = pricePerDay;
        this.reservations = reservations;
        this.petHotel = petHotel;
    }
}
