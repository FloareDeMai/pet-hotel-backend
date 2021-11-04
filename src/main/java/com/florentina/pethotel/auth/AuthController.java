package com.florentina.pethotel.auth;


import com.florentina.pethotel.customer.Customer;
import com.florentina.pethotel.customer.CustomerRepository;
import com.florentina.pethotel.hotel.PetHotel;
import com.florentina.pethotel.hotel.PetHotelRepository;
import com.florentina.pethotel.payload.request.LoginRequest;
import com.florentina.pethotel.payload.request.SignUpRequest;
import com.florentina.pethotel.payload.response.JwtResponse;
import com.florentina.pethotel.payload.response.MessageResponse;
import com.florentina.pethotel.security.jwt.JwtUtils;
import com.florentina.pethotel.security.roles.ERole;
import com.florentina.pethotel.security.roles.Role;
import com.florentina.pethotel.security.roles.RoleRepository;
import com.florentina.pethotel.security.services.UserDetailsImplementation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    PetHotelRepository petHotelRepository;
    CustomerRepository customerRepository;
    RoleRepository roleRepository;
    AuthenticationManager authenticationManager;
    JwtUtils jwtUtils;
    PasswordEncoder passwordEncoder;

    @PostMapping("/pet-hotel/signup")
    public ResponseEntity<?> registerPetHotel(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (petHotelRepository.existsByHotelName(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error, PetHotel name is already taken!"));
        }
        if (petHotelRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error, email is already taken"));
        }

        PetHotel petHotel = new PetHotel(signUpRequest.getUsername(), passwordEncoder.encode(signUpRequest.getPassword()), signUpRequest.getEmail());
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
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
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        if (customerRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: username is already taken"));
        }


        if (customerRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: email is already taken"));
        }
        log.info(signUpRequest.toString());
        Customer customer = new Customer(signUpRequest.getUsername(), signUpRequest.getEmail(), passwordEncoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role customerRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found!"));
            roles.add(customerRole);
        }

        customer.setRoles(roles);
        customer.setJoinedDate(LocalDate.now());
        customerRepository.save(customer);
        return ResponseEntity.ok(new MessageResponse("Customer registered successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImplementation userDetailsImplementation = (UserDetailsImplementation) authentication.getPrincipal();

        List<String> roles = userDetailsImplementation.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String jwt = jwtUtils.generateJwtToken(loginRequest.getUsername(), roles);
        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetailsImplementation.getId(),
                userDetailsImplementation.getUsername(),
                userDetailsImplementation.getEmail(),
                roles));

    }

}
