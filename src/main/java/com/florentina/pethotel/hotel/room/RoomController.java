package com.florentina.pethotel.hotel.room;

import com.florentina.pethotel.hotel.PetHotel;
import com.florentina.pethotel.hotel.PetHotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/rooms")
public class RoomController {


    private final PetHotelRepository petHotelRepository;
    private final RoomRepository roomRepository;
    private final RoomService roomService;

    @GetMapping(path = "all-rooms/{petHotelId}")
    public List<Room> getAllRoomsByHotelId(@PathVariable Long petHotelId){
        PetHotel petHotel = petHotelRepository.getById(petHotelId);
        List<Room> rooms = roomRepository.getAllByPetHotel(petHotel);
        return rooms;
    }

    @PostMapping(path = "add-room/{petHotelId}")
    public ResponseEntity<?> addRoom(@RequestBody Room roomToAdd, @PathVariable Long petHotelId){
        Room newRoom = roomService.saveNewRoom(roomToAdd, petHotelId);
        return new ResponseEntity<>(newRoom, HttpStatus.OK);

    }
}
