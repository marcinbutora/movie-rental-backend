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
    void shouldReturnListOfCustomers() {
        // given
        CustomerDTO customer1 = createCustomerDTO("Marcin", "Kowalski", "mbutora@gmail.com", LocalDateTime.now());
        CustomerDTO customer2 = createCustomerDTO("Anna", "Kowalska", "abutora@gmail.com", LocalDateTime.now());
        // when
        Customer customerSavedOne = customerService.savePerson(customer1);
        Customer customerSavedTwo = customerService.savePerson(customer2);
        customerService.getCustomersList();
        // then
        assertThat(List.of(customerSavedOne,customerSavedTwo)).isNotEmpty();
    }

    @Test
    void shouldReturnCustomerById() {
        // given
        Customer customer = createCustomer(2L, "Marcin", "Butora", "mbutora@gmail.com", LocalDateTime.now());
        // when
        Customer customerSaved = customerService.savePerson(customerConverter.entityToDto(customer));
        Optional<Customer> customerById = customerService.getCustomerById(customerSaved.getId());
        // then
        assertThat(customerById.get().getId()).isEqualTo(customer.getId());
    }

    @Test
    void shouldSaveCustomer() {
        // given
        CustomerDTO customer = createCustomerDTO("Jan", "Kowalski", "jkowalski@gmail.com", LocalDateTime.now());
        // when
        Customer savedCustomer = customerService.savePerson(customer);
        // then
        assertThat(savedCustomer.getEmail()).isEqualTo(customer.getEmail());
    }

    @Test
    void shouldRemoveCustomerGivenByEmail() {
        // given
        Customer customer = createCustomer(3L, "John", "Smith", "jsmith@gmail.com", LocalDateTime.now());
        // when
        customerService.deleteCustomer(customerConverter.entityToDto(customer));
        // then
        final List<Customer> customerList = customerService.getCustomersList();
        assertThat(customerList).isEmpty();
    }

    @Test
    void shouldUpdateCustomerByGivenMail() {
        // given
        Customer customerInDB = createCustomer(1L, "Marcin", "Butora", "mbutora@gmail.com", LocalDateTime.now());
        Customer customerToChange = createCustomer(2L,"Anna", "Kowalska", "akowalska@gmail.com", LocalDateTime.now());
        // when

        Customer customerUpdated = customerService.updateCustomer(customerInDB.getEmail(), customerToChange);
        // then
        assertThat(customerUpdated).isEqualTo(customerUpdated);
    }

}