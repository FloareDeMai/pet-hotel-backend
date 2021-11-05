package com.florentina.pethotel.hotel.room;

import com.florentina.pethotel.hotel.PetHotel;
import com.florentina.pethotel.hotel.PetHotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class HotelOfferServiceImplementation implements HotelOfferService{

    private final HotelOfferRepository hotelOfferRepository;
    private final PetHotelRepository petHotelRepository;

    public HotelOffer saveNewRoom(HotelOffer hotelOffer, Long petHotelId){
        PetHotel petHotel = petHotelRepository.findById(petHotelId).get();
        HotelOffer newHotelOffer = new HotelOffer();
        newHotelOffer.setRoomType(hotelOffer.getRoomType());
        newHotelOffer.setPetHotel(petHotel);
        newHotelOffer.setTotalRooms(hotelOffer.getTotalRooms());
        newHotelOffer.setWalkingPerDay(hotelOffer.getWalkingPerDay());
        newHotelOffer.setMealsPerDay(hotelOffer.getMealsPerDay());
        newHotelOffer.setPricePerDay(hotelOffer.getPricePerDay());
        hotelOfferRepository.save(newHotelOffer);
        return newHotelOffer;
    }
}
