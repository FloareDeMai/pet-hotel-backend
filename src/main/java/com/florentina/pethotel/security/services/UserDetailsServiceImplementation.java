package com.florentina.pethotel.security.services;

import com.florentina.pethotel.customer.Customer;
import com.florentina.pethotel.customer.CustomerRepository;
import com.florentina.pethotel.hotel.PetHotel;
import com.florentina.pethotel.hotel.PetHotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImplementation implements UserDetailsService {

    CustomerRepository customerRepository;
    PetHotelRepository petHotelRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer customer = customerRepository.findByUsernameIs(username);
        PetHotel petHotel = petHotelRepository.findByHotelName(username);

        if (customer != null) {
            return UserDetailsImplementation.build(customer);
        }
        return UserDetailsImplementation.buildHotel(petHotel);
    }
}
