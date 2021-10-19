package com.florentina.pethotel.hotel.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.florentina.pethotel.hotel.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class RoomDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("room_type")
    private String roomType;

    @JsonProperty("total_rooms")
    private int totalRooms;

    @JsonProperty("booked_rooms")
    private int bookedRooms;

    @JsonProperty("walking_per_day")
    private int walkingPerDay;

    @JsonProperty("meals_per_day")
    private int mealsPerDay;

    @JsonProperty("price_per_day")
    private int pricePerDay;

    @JsonProperty("hotel_id")
    private Long hotelId;
}
