package com.springmicroservice.adminservice.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO implements Serializable {

    private List<AdminResponseDTO> adminResponseDTOS;
    private String message;

    public static ResponseDTOBuilder builder() {
        return new ResponseDTOBuilder();
    }

    public List<AdminResponseDTO> getAdminResponseDTOS() {
        return adminResponseDTOS;
    }

    public void setAdminResponseDTOS(List<AdminResponseDTO> adminResponseDTOS) {
        this.adminResponseDTOS = adminResponseDTOS;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
