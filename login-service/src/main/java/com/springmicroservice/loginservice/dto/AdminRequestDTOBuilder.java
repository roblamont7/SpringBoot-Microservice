package com.springmicroservice.loginservice.dto;

public class AdminRequestDTOBuilder {
    private String username;
    private String emailAddress;

    public AdminRequestDTOBuilder() {}

    public AdminRequestDTOBuilder(AdminRequestDTO adminRequestDTO) {
        this.username = adminRequestDTO.getUsername();
        this.emailAddress = adminRequestDTO.getEmailAddress();
    }

    public AdminRequestDTOBuilder username(String username) {
        this.username = username;
        return this;
    }

    public AdminRequestDTOBuilder emailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public AdminRequestDTO build() {
        AdminRequestDTO adminRequestDTO = new AdminRequestDTO();
        adminRequestDTO.setUsername(username);
        adminRequestDTO.setEmailAddress(emailAddress);
        return adminRequestDTO;
    }
}
