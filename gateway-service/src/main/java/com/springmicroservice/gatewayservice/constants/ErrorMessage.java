package com.springmicroservice.gatewayservice.constants;

public class ErrorMessage {

    public interface TokenInvalid {
        String DEVELOPER_MESSAGE = "Request no authorised";
        String MESSAGE = "unmatched JWT Token";
    }
}
