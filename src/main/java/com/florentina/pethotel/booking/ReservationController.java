package com.florentina.pethotel.booking;


import com.florentina.pethotel.exception.domain.NoRoomsAvailableException;
import com.florentina.pethotel.hotel.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "/reservations")
public class ReservationController {

    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;

    @GetMapping("/all-reservations")
    public List<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

    @PostMapping("/add-reservation/{petHotelId}/{customerId}/{roomType}")
    public ResponseEntity<?> addNewReservation(@RequestBody Reservation newReservation, @PathVariable(value = "petHotelId") Long petHotelId, @PathVariable(value = "customerId") Long customerId, @PathVariable(value = "roomType")RoomType roomType) throws NoRoomsAvailableException {
        log.info(LocalDate.now().toString());
        Reservation reservationToAdd = reservationService.makeAReservation(customerId,petHotelId,newReservation, roomType);
        return new ResponseEntity<>(reservationToAdd, HttpStatus.OK);
    }



}
