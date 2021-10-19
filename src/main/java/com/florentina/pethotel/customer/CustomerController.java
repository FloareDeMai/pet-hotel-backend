package com.florentina.pethotel.customer;


import com.florentina.pethotel.booking.Reservation;
import com.florentina.pethotel.components.Address;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/customers")
public class CustomerController {


    private final CustomerRepository customerRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody Customer newCustomer) {
        Customer customer = new Customer();
        Address address = new Address();

        customer.setFirstName(newCustomer.getFirstName());
        customer.setLastName(newCustomer.getLastName());
        customer.setUsername(newCustomer.getUsername());
        customer.setEmail(newCustomer.getEmail());
        customer.setPassword(newCustomer.getPassword());
        //to set addresss
        customer.setAddress(new Address("Iazu", "Romania", "Principala", 23, "042041"));
        customer.setPhoneNumber(newCustomer.getPhoneNumber());
        customer.setGender(newCustomer.getGender());
        customer.setAge(newCustomer.getAge());
        customerRepository.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
