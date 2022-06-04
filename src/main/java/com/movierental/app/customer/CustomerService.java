package com.movierental.app.customer;

import com.movierental.app.exception.CustomerAlreadyExistsException;
import com.movierental.app.exception.CustomerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    CustomerService(CustomerRepository customerRepository, CustomerConverter customerConverter) {
        this.customerRepository = customerRepository;
        this.customerConverter = customerConverter;
    }

    List<CustomerDTO> getCustomersList() {
        log.info("Getting customers list, all customers count: " + customerRepository.findAll().size());
        return customerConverter.entityToDtoList(customerRepository.findAll());
    }

    CustomerDTO savePerson(CustomerDTO customerDTO) {
        log.info("Saving new customer with e-mail: " + customerDTO.getEmail() + " in database");
        if (customerRepository.findCustomerByEmail(customerDTO.getEmail()).isPresent()) {
            log.warn("Customer with this mail: " + customerDTO.getEmail() + " is already in database!");
            throw new CustomerAlreadyExistsException("Customer with this mail: " + customerDTO.getEmail() + " already exists");
        }
        customerRepository.save(customerConverter.dtoToEntity(customerDTO));
        return customerDTO;
    }

    void deleteCustomer(String email) {
        Optional<Customer> foundedCustomer = customerRepository.findCustomerByEmail(email);
        if (foundedCustomer.isEmpty()) {
            log.warn("Customer with email " + email + " not found!");
            throw new CustomerNotFoundException("Customer with this email: " + email + " is not exist!");
        }
        log.info("Customer " + foundedCustomer.get().getFirstName() + " " + foundedCustomer.get().getLastName() + " was removed");
        customerRepository.deleteCustomerByEmail(email);
    }

    CustomerDTO updateCustomer(String email, CustomerDTO customer) {
        log.info("Searching for customer by mail: " + email);
        Optional<Customer> byEmail = getCustomerByMail(email);
        if (byEmail.isEmpty()) {
            log.warn("Customer with this mail: " + email + " is not exist!");
            throw new CustomerNotFoundException("Customer with this mail: " + email + " is not exist!");
        }
        byEmail.get().update(customerConverter.dtoToEntity(customer));
        customerRepository.save(byEmail.get());
        log.info("Updated customer saved as a: " + customer.getFirstName() + " " + customer.getLastName());
        return customerConverter.entityToDto(byEmail.get());
    }

    Optional<Customer> getCustomerByMail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }
}