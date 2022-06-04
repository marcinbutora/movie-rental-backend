package com.movierental.app.customer;

import com.movierental.app.exception.CustomerAlreadyExistsException;
import org.junit.jupiter.api.Assertions;
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
    void shouldReturnCustomerByEmail() {
        // given
        CustomerDTO customer = createCustomerDTO("Marcin", "Butora", "kabutora@gmail.com", LocalDateTime.now());
        // when
        Customer savedCustomer = customerService.savePerson(customer);
        // then
        assertThat(customer.getEmail()).isEqualTo(savedCustomer.getEmail());
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
        CustomerDTO customer = createCustomerDTO( "John", "Smith", "jddsmith@gmail.com", LocalDateTime.now());
        // when
        customerService.deleteCustomer(customer);
        // then
        final List<CustomerDTO> customerList = customerService.getCustomersList();
        assertThat(customerList).doesNotContain(customer);
    }

    @Test
    void shouldUpdateCustomerByGivenMail() {
        // given
        CustomerDTO customerInDB = createCustomerDTO( "Marcin", "Butora", "kambutora@gmail.com", LocalDateTime.now());
        CustomerDTO customerToChange = createCustomerDTO("Anna", "Kowalska", "akowalska@gmail.com", LocalDateTime.now());
        // when

        CustomerDTO customerUpdated = customerService.updateCustomer(customerInDB.getEmail(), customerToChange);
        // then
        assertThat(customerUpdated).isEqualTo(customerUpdated);
    }

    @Test
    void shouldThrowExceptionWhenCustomerAlreadyExists() {
        // given
        CustomerDTO customerDTO = new CustomerDTO("Test", "Test", "test@test.com", LocalDateTime.now());
        CustomerDTO customerDTO1 = new CustomerDTO("Test 2", "Test 2", "test@test.com", LocalDateTime.now());
        // when
        customerService.savePerson(customerDTO);
        // then
        Assertions.assertThrows(CustomerAlreadyExistsException.class, () -> customerService.savePerson(customerDTO1));
    }

}