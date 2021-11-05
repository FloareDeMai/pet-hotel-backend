package com.florentina.pethotel.hotel;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetHotelServiceImplementation implements PetHotelService {

    private final PetHotelRepository petHotelRepository;

    public List<PetHotel> getAllHotels() {
        return petHotelRepository.findAll();
    }


    public List<PetHotel> getAllPetHotelsByCity(String cityName) {
        return petHotelRepository.findPetHotelsByAddress_City(cityName);
    }
}
