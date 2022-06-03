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

    public Customer updatePerson(String email, CustomerDTO customerDTO) throws Exception {
        Optional<Customer> foundedCustomerByEmail = getCustomerByMail(email);
        try {
            if (foundedCustomerByEmail.isEmpty()) {
                Customer customer = new Customer();
                customer.setEmail(customerDTO.getEmail());
                customer.setFirstName(customerDTO.getFirstName());
                customer.setLastName(customerDTO.getLastName());
                return customer;
            }
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return customerConverter.dtoToEntity(customerDTO);
    }

    private Optional<Customer> getCustomerByMail(String email) {
        return customerRepository.findByEmail(email);
    }
}
