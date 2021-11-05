package com.florentina.pethotel.hotel;


import java.util.List;


public interface PetHotelService {

    public List<PetHotel> getAllHotels();

    public List<PetHotel> getAllPetHotelsByCity(String cityName);
}
