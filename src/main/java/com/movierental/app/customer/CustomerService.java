package com.movierental.app.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    public CustomerService(CustomerRepository customerRepository, CustomerConverter customerConverter) {
        this.customerRepository = customerRepository;
        this.customerConverter = customerConverter;
    }

    public List<Customer> getCustomersList() {
        log.info("Getting customers list");
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long customerId) {
        log.info("Getting info about customer id " + customerId);
        return customerRepository.findById(customerId);
    }

    public Customer savePerson(CustomerDTO customerDTO) {
        log.info("Saving new customer in database");
        return customerRepository.save(customerConverter.dtoToEntity(customerDTO));
    }

    void deleteCustomer(String emailCustomer) throws Exception {
        checkIfCustomerExistByEmail(emailCustomer);
        customerRepository.findByEmail(emailCustomer);
    }

    void checkIfCustomerExistByEmail(String email) throws Exception {
        final boolean exists = customerRepository.findCustomerByEmail(email);
        if (!exists) {
            throw new Exception("Customer not found!");
        }
    }
}
