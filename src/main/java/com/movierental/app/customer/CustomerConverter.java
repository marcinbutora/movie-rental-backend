package com.movierental.app.customer;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerConverter {
    public CustomerDTO entityToDto(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setCreatedDate(customer.getCreatedDate());
        return customerDTO;
    }

    public List<CustomerDTO> entityToDtoList(List<Customer> customerList) {
        return customerList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Customer dtoToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setCreatedDate(customerDTO.getCreatedDate());
        return customer;
    }

    public List<Customer> dtoToEntityList(List<CustomerDTO> customerDTOS) {
        return customerDTOS.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

}
