package com.movierental.app.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerConverter customerConverter;

    private CustomerDTO createCustomerDTO(String firstName, String lastName, String email, LocalDateTime created) {
        CustomerDTO customerDTOS = new CustomerDTO();
        customerDTOS.setFirstName(firstName);
        customerDTOS.setLastName(lastName);
        customerDTOS.setEmail(email);
        customerDTOS.setCreatedDate(created);
        return customerDTOS;
    }

    private Customer createCustomer(Long id, String firstName, String lastName, String email, LocalDateTime created) {
        Customer newCustomer = new Customer();
        newCustomer.setId(id);
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);
        newCustomer.setEmail(email);
        newCustomer.setCreatedDate(created);
        return newCustomer;
    }

    @Test
    void getCustomersList() {
        // given
        CustomerDTO customer1 = createCustomerDTO("Marcin", "Kowalski", "mbutora@gmail.com", LocalDateTime.now());
        CustomerDTO customer2 = createCustomerDTO("Anna", "Kowalska", "abutora@gmail.com", LocalDateTime.now());
        // when
        Customer customerSavedOne = customerService.savePerson(customer1);
        Customer customerSavedTwo = customerService.savePerson(customer2);
        // then
        assertThat(List.of(customerSavedOne,customerSavedTwo)).isNotEmpty();
    }

    @Test
    void getCustomerById() {
        // given
        Customer customer = createCustomer(1L, "Marcin", "Butora", "mbutora@gmail.com", LocalDateTime.now());
        // when
        Customer customerSaved = customerService.savePerson(customerConverter.entityToDto(customer));
        Optional<Customer> customerById = customerService.getCustomerById(customerSaved.getId());
        // then
        assertThat(customerById.get().getId()).isEqualTo(customer.getId());
    }

    @Test
    void savePerson() {
        // given
        Customer customer = createCustomer(2L, "Jan", "Kowalski", "jkowalski@gmail.com", LocalDateTime.now());
        // when
        Customer savedCustomer = customerService.savePerson(customerConverter.entityToDto(customer));
        // then
        assertThat(savedCustomer).isEqualTo(customer);
    }

    @Test
    void deleteCustomer() {
        // given
        Customer customer = createCustomer(3L, "John", "Smith", "jsmith@gmail.com", LocalDateTime.now());
        // when
        customerService.deleteCustomer(customerConverter.entityToDto(customer));
        // then
        final List<Customer> customerList = customerService.getCustomersList();
        assertThat(customerList).isEmpty();
    }

    @Test
    void updatePerson() throws Exception {
        // given
        CustomerDTO customerDTO = createCustomerDTO("John", "Smith", "jsmith@gmail.com", LocalDateTime.now());
        CustomerDTO customerToChange = createCustomerDTO("Marry", "Smith", "msmith@o2.pl", LocalDateTime.now());
        // when
        Customer customerSaved = customerService.savePerson(customerDTO);
        Customer customerUpdated = customerService.updatePerson(customerDTO.getEmail(), customerToChange);
        //then
        assertThat(customerSaved).isEqualTo(customerUpdated);
    }

}