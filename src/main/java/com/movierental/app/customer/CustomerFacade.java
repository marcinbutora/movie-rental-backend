package com.movierental.app.customer;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerFacade {
    private final CustomerService customerService;

    public CustomerFacade(CustomerService customerService) {
        this.customerService = customerService;
    }

    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        return customerService.savePerson(customerDTO);
    }

    public List<CustomerDTO> getCustomers() {
        return customerService.getCustomersList();
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerService.getCustomerByMail(email);
    }

    public CustomerDTO updateCustomer(String email, CustomerDTO customerDTO) {
        return customerService.updateCustomer(email, customerDTO);
    }

    public void deleteCustomer(CustomerDTO customerDTO) {
        customerService.deleteCustomer(customerDTO);
    }

}
