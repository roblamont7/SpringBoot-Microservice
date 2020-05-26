package com.springmicroservice.adminservice.constants;

public class ErrorMessageConstants {

    public interface AdminNotFoundException {
        String DEVELOPER_MESSAGE = "Admin entity returned null";
        String MESSAGE = "admin with given parameters doesnt exist";
    }
}
