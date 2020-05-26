package com.springmicroservice.adminservice.dto;

public class AdminResponseDTOBuilder {
    private String emailAddress;
    private Long id;
    private String password;
    private Character status;
    private Integer loginAttempt;

    public AdminResponseDTOBuilder() {}

    public AdminResponseDTOBuilder(AdminResponseDTO adminResponseDTO) {
        this.emailAddress = adminResponseDTO.getEmailAddress();
        this.id = adminResponseDTO.getId();
        this.password = adminResponseDTO.getPassword();
        this.status = adminResponseDTO.getStatus();
        this.loginAttempt = adminResponseDTO.getLoginAttempt();
    }

    public AdminResponseDTOBuilder emailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public AdminResponseDTOBuilder id(Long id) {
        this.id = id;
        return this;
    }
    public AdminResponseDTOBuilder password(String password) {
        this.password = password;
        return this;
    }

    public AdminResponseDTOBuilder status(Character status) {
        this.status = status;
        return this;
    }

    public AdminResponseDTOBuilder loginAttempt(Integer loginAttempt) {
        this.loginAttempt = loginAttempt;
        return this;
    }

    public AdminResponseDTO build() {
        AdminResponseDTO adminResponseDTO = new AdminResponseDTO();
        adminResponseDTO.setEmailAddress(emailAddress);
        adminResponseDTO.setId(id);
        adminResponseDTO.setPassword(password);
        adminResponseDTO.setStatus(status);
        adminResponseDTO.setLoginAttempt(loginAttempt);
        return adminResponseDTO;
    }
}
