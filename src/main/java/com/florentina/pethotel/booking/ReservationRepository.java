package com.florentina.pethotel.booking;

import com.florentina.pethotel.customer.Customer;
import com.florentina.pethotel.hotel.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    Reservation findReservationById(Long id);
    List<Reservation> findAllByCustomerId(Long id);
    @Query(
            value = "SELECT r FROM Reservation r WHERE r.customer = :customer AND r.id = :reservationId")
    Reservation findReservationByCustomerAndReservationId(Customer customer, Long reservationId);
List<Reservation> findReservationsByRoom(Room room);



}
