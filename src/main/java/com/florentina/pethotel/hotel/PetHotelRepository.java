package com.florentina.pethotel.hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetHotelRepository extends JpaRepository<PetHotel, Long> {
    boolean existsByHotelName(String hotelName);

    boolean existsByEmail(String email);

    Optional<PetHotel> findByHotelName(String hotelName);

    PetHotel findPetHotelById(Long id);

    List<PetHotel> findPetHotelsByAddress_City(String cityName);

    List<PetHotel> findPetHotelsByHasVeterinary(boolean hasVet);


}
