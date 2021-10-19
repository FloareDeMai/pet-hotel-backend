package com.florentina.pethotel.hotel;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/pet-hotel")
@CrossOrigin
@Slf4j
public class PetHotelController {

    private final PetHotelService petHotelService;


    @GetMapping(path = "/all-hotels")
    public List<PetHotel> getAllHotels(){
        return petHotelService.getAllHotels();
    }
}
