package com.movierental.app.customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService, CustomerConverter customerConverter) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDTO> getCustomersList() {
        return customerService.getCustomersList();
    }

    @PostMapping
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.savePerson(customerDTO);
    }

    @DeleteMapping
    public void deleteCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.deleteCustomer(customerDTO);
    }
}
