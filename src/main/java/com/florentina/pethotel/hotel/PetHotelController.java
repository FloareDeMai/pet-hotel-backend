package com.florentina.pethotel.hotel;


import com.florentina.pethotel.booking.ReservationService;
import com.florentina.pethotel.payload.request.SearchRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/pet-hotel")
@CrossOrigin
@Slf4j
public class PetHotelController {

    private final PetHotelService petHotelService;
    private final ReservationService reservationService;


    @GetMapping(path = "/all-hotels")
    public List<PetHotel> getAllHotels(){
        return petHotelService.getAllHotels();
    }

    @GetMapping(path = "/all-hotels/{cityName}")
    public List<PetHotel> getHotelsByCityName(@PathVariable String cityName){
        return petHotelService.getAllPetHotelsByCity(cityName);
    }

    @GetMapping(path = "/available")
    public List<PetHotel> getAvailable(@RequestBody SearchRequest searchRequest){
        return reservationService.findPetHotelsByCityAndIntervalSearched(searchRequest.getCityName(), searchRequest.getStartDate(), searchRequest.getEndDate());
    }
}

