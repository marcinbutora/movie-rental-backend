package com.movierental.app.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
class CustomerController {
    private final CustomerFacade customerFacade;

    CustomerController(CustomerFacade customerFacade, CustomerConverter customerConverter) {
        this.customerFacade = customerFacade;
    }

    @GetMapping
    ResponseEntity<List<CustomerDTO>> getCustomersList() {
        List<CustomerDTO> customerDTOS = customerFacade.getCustomers();
        return new ResponseEntity<>(customerDTOS, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO customer = customerFacade.saveCustomer(customerDTO);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping("/{email}")
    ResponseEntity<CustomerDTO> updateCustomer(@PathVariable String email, @RequestBody CustomerDTO customerDTO) {
        CustomerDTO customer = customerFacade.updateCustomer(email, customerDTO);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @DeleteMapping("/{email}")
    ResponseEntity<?> deleteCustomer(@PathVariable String email) {
        customerFacade.deleteCustomer(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
