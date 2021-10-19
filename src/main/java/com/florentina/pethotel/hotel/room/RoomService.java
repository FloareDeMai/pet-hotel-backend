package com.florentina.pethotel.hotel.room;

import com.florentina.pethotel.hotel.PetHotel;
import com.florentina.pethotel.hotel.PetHotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final PetHotelRepository petHotelRepository;

    public Room saveNewRoom(Room room, Long petHotelId){
        PetHotel petHotel = petHotelRepository.findById(petHotelId).get();
        Room newRoom = new Room();
        newRoom.setRoomType(room.getRoomType());
        newRoom.setPetHotel(petHotel);
        newRoom.setTotalRooms(room.getTotalRooms());
        newRoom.setBookedRooms(room.getBookedRooms());
        newRoom.setWalkingPerDay(room.getWalkingPerDay());
        newRoom.setMealsPerDay(room.getMealsPerDay());
        newRoom.setPricePerDay(room.getPricePerDay());
        roomRepository.save(newRoom);
        return newRoom;
    }
}
