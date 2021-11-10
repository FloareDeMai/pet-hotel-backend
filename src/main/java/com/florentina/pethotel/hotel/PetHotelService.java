package com.florentina.pethotel.hotel;


import java.util.List;


public interface PetHotelService {

     List<PetHotel> getAllHotels();

     List<PetHotel> getAllPetHotelsByCity(String cityName);

     List<PetHotel> getAllPetHotelsByAnimalTypeDog();

     List<PetHotel> getAllHotelsByAnimalTypeCat();

     List<PetHotel> getAllHotelsWithGarden();

     List<PetHotel> filter(String filterType, List<PetHotel> listToFilter);

     List<PetHotel> filter(List<String> filterTypes);
}
