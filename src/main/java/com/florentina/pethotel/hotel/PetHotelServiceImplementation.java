package com.florentina.pethotel.hotel;


import com.florentina.pethotel.hotel.room.HotelOffer;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PetHotelServiceImplementation implements PetHotelService {

    private final PetHotelRepository petHotelRepository;

    public List<PetHotel> getAllHotels() {
        return petHotelRepository.findAll();
    }


    public List<PetHotel> getAllPetHotelsByCity(String cityName) {
        return petHotelRepository.findPetHotelsByAddress_City(cityName);
    }

    public List<PetHotel> getAllPetHotelsByAnimalTypeDog() {
        List<PetHotel> allHotels = petHotelRepository.findAll();
        List<PetHotel> selectedHotels = new ArrayList<>();
        for (PetHotel petHotel : allHotels) {
            for (HotelOffer hotelOffer : petHotel.getHotelOffers()) {
                if (hotelOffer.getRoomType().toString().contains("DOGS")) {
                    selectedHotels.add(hotelOffer.getPetHotel());
                }
            }
        }
        return selectedHotels;
    }

    public List<PetHotel> filter(String filterType, List<PetHotel> listToFilter) {
        List<PetHotel> selectedHotels = new ArrayList<>();
        for (PetHotel petHotel : listToFilter) {
            for (HotelOffer hotelOffer : petHotel.getHotelOffers()) {
                if (hotelOffer.getRoomType().toString().contains(filterType)) {
                    selectedHotels.add(hotelOffer.getPetHotel());

                }
            }
        }
        return selectedHotels;
    }


    public List<PetHotel> filter(List<String> filterTypes) {
        if (filterTypes == null) {
            return petHotelRepository.findAll();
        }

        List<PetHotel> selectedHotels = petHotelRepository.findAll();
        List<PetHotel> toReturn = new ArrayList<>();
        for (String filterType : filterTypes) {
            toReturn.addAll(filter(filterType, selectedHotels));
        }

        if (filterTypes.contains("VET") && toReturn.isEmpty()) {
          toReturn=   petHotelRepository.findAll().stream().filter(PetHotel::isHasVeterinary).collect(Collectors.toList());
        }else if(filterTypes.contains("VET") ){
            toReturn = toReturn.stream().filter(PetHotel::isHasVeterinary).collect(Collectors.toList());
        }

        Set<PetHotel> uniqueHotels = new HashSet<>(toReturn);
        return new ArrayList<>(uniqueHotels);
    }

    public List<PetHotel> getAllHotelsByAnimalTypeCat() {
        List<PetHotel> allHotels = petHotelRepository.findAll();
        List<PetHotel> selectedHotels = new ArrayList<>();
        for (PetHotel petHotel : allHotels) {
            for (HotelOffer hotelOffer : petHotel.getHotelOffers()) {
                if (hotelOffer.getRoomType().toString().contains("CATS")) {
                    selectedHotels.add(hotelOffer.getPetHotel());
                }
            }
        }
        return selectedHotels;
    }

    public List<PetHotel> getAllHotelsWithGarden() {
        List<PetHotel> allHotels = petHotelRepository.findAll();
        List<PetHotel> selectedHotels = new ArrayList<>();
        for (PetHotel petHotel : allHotels) {
            for (HotelOffer hotelOffer : petHotel.getHotelOffers()) {
                if (hotelOffer.getRoomType().toString().contains("GARDEN")) {
                    selectedHotels.add(hotelOffer.getPetHotel());
                }
            }
        }
        return selectedHotels;
    }
}
