package com.movierental.app.customer;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime createdDate;

    public CustomerDTO(Long id, String firstName, String lastName, String email, LocalDateTime createdDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.createdDate = createdDate;
    }

    public CustomerDTO() {
    }
}
