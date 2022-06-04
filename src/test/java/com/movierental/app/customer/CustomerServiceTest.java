package com.movierental.app.customer;

import com.movierental.app.exception.CustomerAlreadyExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CustomerServiceTest {
    @Autowired
    private CustomerFacade customerFacade;
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
        CustomerDTO customerSavedOne = customerFacade.saveCustomer(customer1);
        CustomerDTO customerSavedTwo = customerFacade.saveCustomer(customer2);
        customerFacade.getCustomers();
        // then
        assertThat(List.of(customerSavedOne,customerSavedTwo)).isNotEmpty();
    }

    @Test
    void shouldReturnCustomerByEmail() {
        // given
        CustomerDTO customer = createCustomerDTO("Marcin", "Butora", "kabutora@gmail.com", LocalDateTime.now());
        // when
        CustomerDTO savedCustomer = customerFacade.saveCustomer(customer);
        // then
        assertThat(customer.getEmail()).isEqualTo(savedCustomer.getEmail());
    }

    @Test
    void shouldSaveCustomer() {
        // given
        CustomerDTO customer = createCustomerDTO("Jan", "Kowalski", "jkowalski@gmail.com", LocalDateTime.now());
        // when
        CustomerDTO savedCustomer = customerFacade.saveCustomer(customer);
        // then
        assertThat(savedCustomer.getEmail()).isEqualTo(customer.getEmail());
    }

    @Test
    void shouldRemoveCustomerGivenByEmail() {
        // given
        CustomerDTO customer = createCustomerDTO( "John", "Smith", "jddsmith@gmail.com", LocalDateTime.now());
        // when
        customerFacade.saveCustomer(customer);
        customerFacade.deleteCustomer(customer.getEmail());
        // then
        final List<CustomerDTO> customerList = customerFacade.getCustomers();
        assertThat(customerList).doesNotContain(customer);
    }

    @Test
    void shouldUpdateCustomerByGivenMail() {
        // given
        CustomerDTO customerInDB = createCustomerDTO( "Marcin", "Butora", "kambutora@gmail.com", LocalDateTime.now());
        CustomerDTO customerToChange = createCustomerDTO("Anna", "Kowalska", "akowalska@gmail.com", LocalDateTime.now());
        // when

        CustomerDTO customerUpdated = customerFacade.updateCustomer(customerInDB.getEmail(), customerToChange);
        // then
        assertThat(customerUpdated).isEqualTo(customerUpdated);
    }

    @Test
    void shouldThrowExceptionWhenCustomerAlreadyExists() {
        // given
        CustomerDTO customerDTO = new CustomerDTO("Test", "Test", "test@test.com", LocalDateTime.now());
        CustomerDTO customerDTO1 = new CustomerDTO("Test 2", "Test 2", "test@test.com", LocalDateTime.now());
        // when
        customerFacade.saveCustomer(customerDTO);
        // then
        Assertions.assertThrows(CustomerAlreadyExistsException.class, () -> customerFacade.saveCustomer(customerDTO1));
    }

}