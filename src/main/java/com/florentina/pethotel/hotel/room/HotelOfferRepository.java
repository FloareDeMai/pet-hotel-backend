package com.florentina.pethotel.hotel.room;

import com.florentina.pethotel.hotel.PetHotel;
import com.florentina.pethotel.hotel.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelOfferRepository extends JpaRepository<HotelOffer, Long> {
    List<HotelOffer> getAllByPetHotel(PetHotel petHotel);

    HotelOffer getRoomByPetHotelAndRoomType(PetHotel petHotel, RoomType roomType);

}
