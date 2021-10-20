package com.florentina.pethotel.hotel.room;

import com.florentina.pethotel.hotel.PetHotel;
import com.florentina.pethotel.hotel.PetHotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/rooms")
public class HotelOfferController {


    private final PetHotelRepository petHotelRepository;
    private final HotelOfferRepository hotelOfferRepository;
    private final HotelOfferService hotelOfferService;

    @GetMapping(path = "all-rooms/{petHotelId}")
    public List<HotelOffer> getAllRoomsByHotelId(@PathVariable Long petHotelId){
        PetHotel petHotel = petHotelRepository.getById(petHotelId);
        List<HotelOffer> hotelOffers = hotelOfferRepository.getAllByPetHotel(petHotel);
        return hotelOffers;
    }

    @PostMapping(path = "add-room/{petHotelId}")
    public ResponseEntity<?> addRoom(@RequestBody HotelOffer hotelOfferToAdd, @PathVariable Long petHotelId){
        HotelOffer newHotelOffer = hotelOfferService.saveNewRoom(hotelOfferToAdd, petHotelId);
        return new ResponseEntity<>(newHotelOffer, HttpStatus.OK);

    }
}
