package com.movierental.app.customer;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerFacade {
    private final CustomerService service;

    public CustomerFacade(CustomerService customerService) {
        this.service = customerService;
    }

    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        return service.savePerson(customerDTO);
    }

    public List<CustomerDTO> getCustomers() {
        return service.getCustomersList();
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return service.getCustomerByMail(email);
    }

    public CustomerDTO updateCustomer(String email, CustomerDTO customerDTO) {
        return service.updateCustomer(email, customerDTO);
    }

    public CustomerDTO showCustomerInfo(String firstName, String lastName) {
        return service.showCustomerByName(firstName, lastName);
    }

    public void deleteCustomer(String email) {
        service.deleteCustomer(email);
    }

}
