package com.springmicroservice.loginservice.dto;

import java.io.Serializable;

public class AdminRequestDTO implements Serializable {
    private String username;
    private String emailAddress;

    // builder initialisation
    public static AdminRequestDTOBuilder builder() {
        return new AdminRequestDTOBuilder();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
