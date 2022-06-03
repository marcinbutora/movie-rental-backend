package com.movierental.app.customer;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerDTO {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime createdDate;

    public CustomerDTO(String firstName, String lastName, String email, LocalDateTime createdDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.createdDate = createdDate;
    }

    public CustomerDTO() {
    }
}
