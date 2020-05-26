package com.springmicroservice.loginservice.constants;

public class ErrorMessageConstants {

    public interface ForgetPassword {
        String DEV_MESSAGE = "password didnt match the original one";
        String MESSAGE = "Incorrect password.";
    }

    public interface IncorrectPasswordAttempts {
        String DEV_MESSAGE = "Admin is blocked with status 'B'";
        String MESSAGE = "Admin is blocked";
    }

    public interface InvalidAdminStatus {
        String DEV_MESSAGE_FOR_BLOCKED = "the admin has status 'B'";
        String DEV_MESSAGE_FOR_INACTIVE = "the admin has status 'N'";
        String MESSAGE_FOR_BLOCKED = "the admin is in a blocked state";
        String MESSAGE_FOR_INACTIVE = "the admin is in inactive state";
    }

    public interface InvalidAdminUsername {
        String DEV_MESSAGE = "admin entity has returned null";
        String MESSAGE = "Admin with given username doesn't exist";
    }
}
