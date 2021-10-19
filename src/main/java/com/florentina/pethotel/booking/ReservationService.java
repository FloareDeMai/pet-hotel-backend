package com.florentina.pethotel.booking;


import com.florentina.pethotel.customer.Customer;
import com.florentina.pethotel.customer.CustomerRepository;
import com.florentina.pethotel.hotel.PetHotel;
import com.florentina.pethotel.hotel.PetHotelRepository;
import com.florentina.pethotel.hotel.enums.RoomType;
import com.florentina.pethotel.hotel.room.Room;
import com.florentina.pethotel.hotel.room.RoomRepository;
import com.florentina.pethotel.hotel.room.RoomService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final PetHotelRepository petHotelRepository;
    private final RoomService roomService;
    private final RoomRepository roomRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getAllReservationsByCustomer(Customer customer) {
        return reservationRepository.findAllByCustomerId(customer.getId());
    }

    @Transactional
    public Reservation makeAReservation(Long customerId, Long petHotelId, Reservation reservation, RoomType roomType) {
        Customer currentCustomer = customerRepository.findById(customerId).get();
        PetHotel currentPetHotel = petHotelRepository.findById(petHotelId).get();

        Room bookedRoom = roomRepository.getRoomByPetHotelAndRoomType(currentPetHotel, roomType);
        bookedRoom.setBookedRooms(bookedRoom.getBookedRooms() + 1);

        List<Reservation> allReservationsByPetHotelId = reservationRepository.findAllByPetHotelId(petHotelId);
        Reservation newReservation = new Reservation();
        newReservation.setCustomer(currentCustomer);
        newReservation.setPetHotel(currentPetHotel);
        newReservation.setStartDate(reservation.getStartDate());
        newReservation.setEndDate(reservation.getEndDate());
        reservationRepository.save(newReservation);
        roomRepository.save(bookedRoom);

        return newReservation;

    }
    // dintre camerele libere vine al doilea filtru

    private boolean hasFreeRooms(Long petHotelId){
        PetHotel petHotel = petHotelRepository.findById(petHotelId).get();
        List<Room> hotelRooms = roomRepository.getAllByPetHotel(petHotel);
        return hotelRooms.stream().anyMatch(r-> r.getBookedRooms() < r.getTotalRooms());
    }

    private boolean reservationOverlaps(Reservation reservation, LocalDate startDate, LocalDate endDate) {
        return startDate.isBefore(reservation.getEndDate()) && endDate.isAfter(reservation.getStartDate());
    }
}
