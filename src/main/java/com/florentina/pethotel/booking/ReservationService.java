package com.florentina.pethotel.booking;


import com.florentina.pethotel.customer.Customer;
import com.florentina.pethotel.exception.domain.NoRoomsAvailableException;
import com.florentina.pethotel.hotel.PetHotel;
import com.florentina.pethotel.hotel.enums.RoomType;
import java.time.LocalDate;
import java.util.List;


public interface ReservationService {

    Reservation makeAReservation(Long customerId, Long petHotelId, Reservation wantedReservation, RoomType roomType) throws NoRoomsAvailableException;

    List<Reservation> getAllReservations();

    public List<Reservation> getAllReservationsByCustomer(Customer customer);

    List<PetHotel> findPetHotelsByCityAndIntervalSearched(String cityName, LocalDate startDate, LocalDate endDate);

}
