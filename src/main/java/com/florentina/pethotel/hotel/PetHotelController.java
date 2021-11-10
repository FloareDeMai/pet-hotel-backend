package com.florentina.pethotel.hotel;


import com.florentina.pethotel.booking.ReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/pet-hotel")
@CrossOrigin
@Slf4j
public class PetHotelController {

    private final PetHotelService petHotelService;
    private final ReservationService reservationService;
    private final PetHotelRepository petHotelRepository;


    @GetMapping(path = "/all-hotels")
    public List<PetHotel> getAllHotels(){
        return petHotelService.getAllHotels();
    }

    @GetMapping(path = "/all-hotels/{cityName}")
    public List<PetHotel> getHotelsByCityName(@PathVariable String cityName){
        return petHotelService.getAllPetHotelsByCity(cityName);
    }

    @GetMapping(path = "/available")
    public List<PetHotel> getAvailable(@RequestParam(value = "cityName", required = false) String cityName, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate ){
        return reservationService.findPetHotelsByCityAndIntervalSearched(cityName, startDate, endDate);
    }

    @GetMapping(path = "/has-veterinary")
    public List<PetHotel> getAllHotelsWithVet(){
        return petHotelRepository.findPetHotelsByHasVeterinary(true);
    }

    @GetMapping(path = "/room-type-dog")
    public ResponseEntity<List<PetHotel>> getAllHotelsByRoomTypeDog(){
        List<PetHotel> selectedHotels = petHotelService.getAllPetHotelsByAnimalTypeDog();
        return new ResponseEntity<>(selectedHotels, HttpStatus.OK);
    }

    @GetMapping(path = "/room-type-cat")
    public ResponseEntity<List<PetHotel>> getAllHotelsByRoomTypeCat(){
        List<PetHotel> selectedHotels = petHotelService.getAllHotelsByAnimalTypeCat();
        return new ResponseEntity<>(selectedHotels, HttpStatus.OK);
    }

    @GetMapping(path = "/room-type-garden")
    public ResponseEntity<List<PetHotel>> getAllHotelsWithGarden(){
        List<PetHotel> selectedHotels = petHotelService.getAllHotelsWithGarden();
        return new ResponseEntity<>(selectedHotels, HttpStatus.OK);
    }


    @GetMapping(path="/filter")
    public List<PetHotel> getFilteredHotels(@RequestParam(value = "filterParam", required = false) List<String> filerParam){
        return petHotelService.filter(filerParam);
    }
}

