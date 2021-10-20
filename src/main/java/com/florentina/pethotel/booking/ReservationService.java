package com.florentina.pethotel.booking;


import com.florentina.pethotel.customer.Customer;
import com.florentina.pethotel.customer.CustomerRepository;
import com.florentina.pethotel.exception.domain.NoRoomsAvailableException;
import com.florentina.pethotel.hotel.PetHotel;
import com.florentina.pethotel.hotel.PetHotelRepository;
import com.florentina.pethotel.hotel.enums.RoomType;
import com.florentina.pethotel.hotel.room.HotelOffer;
import com.florentina.pethotel.hotel.room.HotelOfferRepository;
import com.florentina.pethotel.hotel.room.HotelOfferService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final PetHotelRepository petHotelRepository;
    private final HotelOfferService hotelOfferService;
    private final HotelOfferRepository hotelOfferRepository;

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
        HotelOffer bookedHotelOffer = hotelOfferRepository.getRoomByPetHotelAndRoomType(currentPetHotel, roomType);

        List<Reservation> allReservationsByRoom = reservationRepository.findReservationsByHotelOffer(bookedHotelOffer);
        log.info("suuus");
        if (countOverlappedRooms(allReservationsByRoom, wantedReservation.getStartDate(), wantedReservation.getEndDate()) < bookedHotelOffer.getTotalRooms()) {
            log.info("aiciiii");
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
    // dintre camerele libere vine al doilea filtru
    // numar rezervarile care se overlap si daca numarul e mai mic decat total room inseamna ca am liber

//    private boolean hasFreeRooms(Long petHotelId){
//        PetHotel petHotel = petHotelRepository.findById(petHotelId).get();
//        List<Room> hotelRooms = roomRepository.getAllByPetHotel(petHotel);
//        return hotelRooms.stream().anyMatch(r-> r.getBookedRooms() < r.getTotalRooms());
//    }

    private boolean reservationOverlaps(Reservation reservation, LocalDate startDate, LocalDate endDate) {
        return startDate.isBefore(reservation.getEndDate()) && endDate.isAfter(reservation.getStartDate());
    }

    private int countOverlappedRooms(List<Reservation> allReservationsByRoom, LocalDate startDate, LocalDate endDate) {
        return (int) allReservationsByRoom.stream().filter(r -> reservationOverlaps(r, startDate, endDate)).count();
    }
}
