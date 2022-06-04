package com.movierental.app.customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
class CustomerController {
    private final CustomerFacade customerFacade;

    CustomerController(CustomerFacade customerFacade, CustomerConverter customerConverter) {
        this.customerFacade = customerFacade;
    }

    @GetMapping
    List<CustomerDTO> getCustomersList() {
        return customerFacade.getCustomers();
    }

    @PostMapping
    CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerFacade.saveCustomer(customerDTO);
    }

    @DeleteMapping
    void deleteCustomer(@RequestBody CustomerDTO customerDTO) {
        customerFacade.deleteCustomer(customerDTO);
    }
}
