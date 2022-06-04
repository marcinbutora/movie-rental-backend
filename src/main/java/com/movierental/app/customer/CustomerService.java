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

    public List<CustomerDTO> getCustomersList() {
        log.info("Getting customers list, all customers count: " + customerRepository.findAll().size());
        return customerConverter.entityToDtoList(customerRepository.findAll());
    }

    public Optional<Customer> getCustomerById(Long customerId) {
        log.info("Getting info about customer id " + customerId);
        return customerRepository.findById(customerId);
    }

    public Customer savePerson(CustomerDTO customerDTO) {
        log.info("Saving new customer with e-mail: " + customerDTO.getEmail() + " in database");
        log.info("Customer created at: " + customerDTO.getCreatedDate());
        return customerRepository.save(customerConverter.dtoToEntity(customerDTO));
    }

    public void deleteCustomer(CustomerDTO customerDTO) {
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

    public Customer updateCustomer(String email, Customer customer) {
        log.info("Searching for customer by mail: " + email);
        Optional<Customer> byEmail = getCustomerByMail(email);
        byEmail.ifPresent(customerRepository::save);
        log.info("Updated customer saved as a: " + customer.getFirstName() + " " + customer.getLastName());
        return customer;
    }

    private Optional<Customer> getCustomerByMail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }
}