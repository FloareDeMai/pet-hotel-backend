package com.florentina.pethotel.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUsername(String username);
    Customer findByUsernameIs(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
