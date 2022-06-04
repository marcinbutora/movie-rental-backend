package com.movierental.app.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
interface CustomerRepository  extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByEmail(String email);
    boolean findByEmail(String email);

    @Transactional
    void deleteCustomerByEmail(String email);
}
