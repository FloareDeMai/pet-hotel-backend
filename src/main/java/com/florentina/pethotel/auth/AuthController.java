package com.florentina.pethotel.auth;


import com.florentina.pethotel.customer.Customer;
import com.florentina.pethotel.customer.CustomerRepository;
import com.florentina.pethotel.hotel.PetHotel;
import com.florentina.pethotel.hotel.PetHotelRepository;
import com.florentina.pethotel.payload.request.SignUpRequest;
import com.florentina.pethotel.payload.response.MessageResponse;
import com.florentina.pethotel.security.roles.ERole;
import com.florentina.pethotel.security.roles.Role;
import com.florentina.pethotel.security.roles.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    PetHotelRepository petHotelRepository;
    CustomerRepository customerRepository;
    RoleRepository roleRepository;

    @PostMapping("/pet-hotel/signup")
    public ResponseEntity<?> registerPetHotel(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(petHotelRepository.existsByHotelName(signUpRequest.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error, PetHotel name is already taken!"));
        }
        if(petHotelRepository.existsByEmail(signUpRequest.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("error, email is already taken"));
        }

        PetHotel petHotel = new PetHotel(signUpRequest.getUsername(), signUpRequest.getPassword(), signUpRequest.getEmail());
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if(strRoles == null){
            Role hotelRole = roleRepository.findByName(ERole.ROLE_HOTEL)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(hotelRole);
        }

        petHotel.setRoles(roles);
        petHotel.setJoinedDate(LocalDate.now());
        petHotelRepository.save(petHotel);
        return ResponseEntity.ok(new MessageResponse("PetHotel registered successfully!"));
    }


    @PostMapping("/signupCustomer")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){

        if(customerRepository.existsByUsername(signUpRequest.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: username is already taken"));
        }


        if(customerRepository.existsByEmail(signUpRequest.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: email is already taken"));
        }

        Customer customer = new Customer(signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword());
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if(strRoles == null){
            Role customerRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found!"));
            roles.add(customerRole);
        }

        customer.setRoles(roles);
        customer.setJoinedDate(LocalDate.now());
        customerRepository.save(customer);
        return ResponseEntity.ok(new MessageResponse("Customer registered successfully!"));
    }
}
