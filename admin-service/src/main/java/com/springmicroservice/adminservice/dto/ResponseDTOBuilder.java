package com.springmicroservice.adminservice.dto;

import java.util.List;

public class ResponseDTOBuilder {

    private List<AdminResponseDTO> adminResponseDTOS;
    private String message;

    public ResponseDTOBuilder() {}

    public ResponseDTOBuilder(ResponseDTO responseDTO) {
        this.adminResponseDTOS = responseDTO.getAdminResponseDTOS();
        this.message = responseDTO.getMessage();
    }

    public ResponseDTOBuilder adminResponseDTOS(List<AdminResponseDTO> adminResponseDTOS) {
        this.adminResponseDTOS = adminResponseDTOS;
        return this;
    }

    public ResponseDTOBuilder message(String message) {
        this.message = message;
        return this;
    }

    public ResponseDTO build() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setAdminResponseDTOS(adminResponseDTOS);
        responseDTO.setMessage(message);
        return responseDTO;
    }
}
