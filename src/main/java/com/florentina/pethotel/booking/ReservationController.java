package com.florentina.pethotel.booking;


import com.florentina.pethotel.hotel.enums.RoomType;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

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
    public ResponseEntity<?> addNewReservation(@RequestBody Reservation newReservation, @PathVariable(value = "petHotelId") Long petHotelId, @PathVariable(value = "customerId") Long customerId, @PathVariable(value = "roomType")RoomType roomType){
        Reservation reservationToAdd = reservationService.makeAReservation(customerId,petHotelId,newReservation, roomType);
        return new ResponseEntity<>(reservationToAdd, HttpStatus.OK);
    }
}
