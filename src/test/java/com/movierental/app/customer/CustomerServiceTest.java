package com.movierental.app.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerConverter customerConverter;

    @Test
    void getCustomersList() {
        // given
        CustomerDTO customer1 = new CustomerDTO("Marcin", "Butora", "mbutora@gmail.com", LocalDateTime.now());
        CustomerDTO customer2 = new CustomerDTO("Anna", "Butora", "abutora@gmail.com", LocalDateTime.now());
        // when
        Customer customerSavedOne = customerService.savePerson(customer1);
        Customer customerSavedTwo = customerService.savePerson(customer2);
        // then
        assertThat(List.of(customerSavedOne,customerSavedTwo)).isNotEmpty();
    }

    @Test
    void getCustomerById() {
        // given
        Customer customer = new Customer(1L, "Marcin", "Butora", "mbutora@gmail.com", LocalDateTime.now());
        // when
        Customer customerSaved = customerService.savePerson(customerConverter.entityToDto(customer));
        Optional<Customer> customerById = customerService.getCustomerById(customerSaved.getId());
        // then
        assertThat(customerById.get().getId()).isEqualTo(customer.getId());
    }

    @Test
    void savePerson() {
        // given
        Customer customer = new Customer(1L, "Marcin", "Butora", "mbutora@gmail.com", LocalDateTime.now());
        // when
        Customer savedCustomer = customerService.savePerson(customerConverter.entityToDto(customer));
        // then
        assertThat(savedCustomer).isEqualTo(customer);
    }

    @Test
    void deleteCustomer() throws Exception {
        // given
        Customer customer = new Customer(1L, "Marcin", "Butora", "mbutora@gmail.com", LocalDateTime.now());
        // when
        customerService.deleteCustomer(customer.getEmail());
        // then
        final List<Customer> customerList = customerService.getCustomersList();
        assertThat(customerList).isEmpty();
    }

}