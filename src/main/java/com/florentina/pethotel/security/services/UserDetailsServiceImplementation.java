package com.florentina.pethotel.security.services;

import com.florentina.pethotel.customer.Customer;
import com.florentina.pethotel.customer.CustomerRepository;
import com.florentina.pethotel.hotel.PetHotel;
import com.florentina.pethotel.hotel.PetHotelRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserDetailsServiceImplementation implements UserDetailsService {

    CustomerRepository customerRepository;
    PetHotelRepository petHotelRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Customer> customer = customerRepository.findByUsername(username);
        Optional<PetHotel> petHotel = petHotelRepository.findByHotelName(username);

        if (customer.isPresent()) {
            return UserDetailsImplementation.build(customer.get());
        }else if(petHotel.isPresent()){
            return UserDetailsImplementation.buildHotel(petHotel.get());
        }else {
            throw  new UsernameNotFoundException("User not found with username: " + username);
        }

    }
}
