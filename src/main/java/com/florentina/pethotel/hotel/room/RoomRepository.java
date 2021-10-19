package com.florentina.pethotel.hotel.room;

import com.florentina.pethotel.hotel.PetHotel;
import com.florentina.pethotel.hotel.enums.RoomType;
import com.florentina.pethotel.hotel.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> getAllByPetHotel(PetHotel petHotel);

    Room getRoomByPetHotelAndRoomType(PetHotel petHotel, RoomType roomType);

}
