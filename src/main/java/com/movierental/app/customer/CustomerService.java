package com.movierental.app.customer;

import com.movierental.app.exception.CustomerAlreadyExistsException;
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
            throw new CustomerAlreadyExistsException("Customer with this mail: " + customerDTO.getEmail() + " already exists");
        }
        customerRepository.save(customerConverter.dtoToEntity(customerDTO));
        return customerDTO;
    }

    void deleteCustomer(CustomerDTO customerDTO) {
        log.info("Searching customer by: " + customerDTO.getEmail());
        Optional<Customer> foundedCustomerByEmail = getCustomerByMail(customerDTO.getEmail());
        log.info("Customer with mail: " + customerDTO.getEmail() + " has been removed");
        deleteCustomer(customerDTO, foundedCustomerByEmail);
    }

    private void deleteCustomer(CustomerDTO customerDTO, Optional<Customer> foundedCustomerByEmail) {
        if (foundedCustomerByEmail.isPresent()) {
            customerRepository.delete(customerConverter.dtoToEntity(customerDTO));
        }
    }

    CustomerDTO updateCustomer(String email, CustomerDTO customer) {
        log.info("Searching for customer by mail: " + email);
        Optional<Customer> byEmail = getCustomerByMail(email);
        byEmail.ifPresent(customerRepository::save);
        log.info("Updated customer saved as a: " + customer.getFirstName() + " " + customer.getLastName());
        return customer;
    }

    Optional<Customer> getCustomerByMail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }
}