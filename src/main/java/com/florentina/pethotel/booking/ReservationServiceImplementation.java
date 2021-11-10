package com.florentina.pethotel.booking;

import com.florentina.pethotel.customer.Customer;
import com.florentina.pethotel.customer.CustomerRepository;
import com.florentina.pethotel.exception.domain.NoRoomsAvailableException;
import com.florentina.pethotel.hotel.PetHotel;
import com.florentina.pethotel.hotel.PetHotelRepository;
import com.florentina.pethotel.hotel.PetHotelService;
import com.florentina.pethotel.hotel.enums.RoomType;
import com.florentina.pethotel.hotel.room.HotelOffer;
import com.florentina.pethotel.hotel.room.HotelOfferRepository;
import com.florentina.pethotel.hotel.room.HotelOfferService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ReservationServiceImplementation implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final PetHotelRepository petHotelRepository;
    private final HotelOfferService hotelOfferService;
    private final HotelOfferRepository hotelOfferRepository;
    private final PetHotelService petHotelService;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getAllReservationsByCustomer(Customer customer) {
        return reservationRepository.findAllByCustomerId(customer.getId());
    }


    public Reservation makeAReservation(Long customerId, Long petHotelId, Reservation wantedReservation, RoomType roomType) throws NoRoomsAvailableException {
        Customer currentCustomer = customerRepository.findById(customerId).get();
        PetHotel currentPetHotel = petHotelRepository.findById(petHotelId).get();
        // TODO not booking rooms in the past
        HotelOffer bookedHotelOffer = hotelOfferRepository.getHotelOfferByPetHotelAndRoomType(currentPetHotel, roomType);

        List<Reservation> allReservationsByRoom = reservationRepository.findReservationsByHotelOffer(bookedHotelOffer);

        if (countOverlappedRooms(allReservationsByRoom, wantedReservation.getStartDate(), wantedReservation.getEndDate()) < bookedHotelOffer.getTotalRooms()) {
            Reservation newReservation = new Reservation();
            newReservation.setCustomer(currentCustomer);
            newReservation.setHotelOffer(bookedHotelOffer);
            newReservation.setStartDate(wantedReservation.getStartDate());
            newReservation.setEndDate(wantedReservation.getEndDate());
            reservationRepository.save(newReservation);
            hotelOfferRepository.save(bookedHotelOffer);

            return newReservation;

        } else {
            throw new NoRoomsAvailableException("No rooms available");
        }


    }

    //TODO by hotel name -> search prin favorite
    //s3 ptr poze

    //TODO find all hotels that have available rooms in a specific interval
    // city
    // startDate, endDate
    // return List<PetHotel>

    public List<PetHotel> findPetHotelsByCityAndIntervalSearched(String cityName, String startDate, String endDate) {

        if (startDate.isEmpty() && endDate.isEmpty() && cityName == null) {
            return petHotelRepository.findAll();
        }

        if (startDate.isEmpty() && endDate.isEmpty()) {
            return petHotelService.getAllPetHotelsByCity(cityName);
        }

        if(cityName == null){
            return getAllPetHotelsBySearchedInterval(startDate, endDate);
        }
        List<PetHotel> petHotelsByCityName = petHotelRepository.findPetHotelsByAddress_City(cityName);
        List<List<HotelOffer>> roomsByPetHotel = new ArrayList<>();
        List<PetHotel> availableHotelsInPeriod = new ArrayList<>();
        return getPetHotels(startDate, endDate, petHotelsByCityName, availableHotelsInPeriod, roomsByPetHotel);

    }

    private boolean reservationOverlaps(Reservation reservation, LocalDate startDate, LocalDate endDate) {
        return startDate.isBefore(reservation.getEndDate()) && endDate.isAfter(reservation.getStartDate());
    }

    private int countOverlappedRooms(List<Reservation> allReservationsByRoom, LocalDate startDate, LocalDate endDate) {
        return (int) allReservationsByRoom.stream().filter(r -> reservationOverlaps(r, startDate, endDate)).count();
    }

    private List<PetHotel> getAllPetHotelsBySearchedInterval(String startDate, String endDate) {
        List<PetHotel> allPetHotels = petHotelRepository.findAll();
        List<PetHotel> availableHotelsInPeriod = new ArrayList<>();
        List<List<HotelOffer>> roomsByPetHotel = new ArrayList<>();

        return getPetHotels(startDate, endDate, allPetHotels, availableHotelsInPeriod, roomsByPetHotel);
    }

    private List<PetHotel> getPetHotels(String startDate, String endDate, List<PetHotel> allPetHotels, List<PetHotel> availableHotelsInPeriod, List<List<HotelOffer>> roomsByPetHotel) {
        for (PetHotel petHotel : allPetHotels) {
            roomsByPetHotel.add(hotelOfferRepository.getHotelOfferByPetHotel(petHotel));
        }

        for (List<HotelOffer> hotelOfferList : roomsByPetHotel) {
            for (HotelOffer hotelOffer : hotelOfferList) {
                if (countOverlappedRooms(hotelOffer.getReservations(), LocalDate.parse(startDate), LocalDate.parse(endDate)) < hotelOffer.getTotalRooms()) {
                    availableHotelsInPeriod.add(hotelOffer.getPetHotel());
                }
            }
        }
        return availableHotelsInPeriod;
    }


}
