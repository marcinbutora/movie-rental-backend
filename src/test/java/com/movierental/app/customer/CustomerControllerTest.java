package com.movierental.app.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CustomerFacade customerFacade;

    private CustomerDTO createCustomer(Long id, String firstName, String lastName, String email, LocalDateTime createdDate) {
        CustomerDTO customer = new CustomerDTO(id, firstName, lastName, email, createdDate);
        customerFacade.saveCustomer(customer);
        return customer;
    }

    @Test
    void shouldReturnAllCustomers() throws Exception {
        // given
        CustomerDTO customerDTO = createCustomer(1L,"Marcin", "Butora", "mbutora@gmail.com", LocalDateTime.of(2022,6,5,15,10,23));
        CustomerDTO customerDTO1 = createCustomer(2L,"Jan", "Kowalski", "jankowalski@gmail.com", LocalDateTime.of(2022,6,7,11,33,22));
        // expect
        mockMvc.perform(get("/api/customers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value(customerDTO.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(customerDTO.getLastName()))
                .andExpect(jsonPath("$[0].email").value(customerDTO.getEmail()))
                .andExpect(jsonPath("$[1].firstName").value(customerDTO1.getFirstName()))
                .andExpect(jsonPath("$[1].lastName").value(customerDTO1.getLastName()))
                .andExpect(jsonPath("$[1].email").value(customerDTO1.getEmail()));
    }

}