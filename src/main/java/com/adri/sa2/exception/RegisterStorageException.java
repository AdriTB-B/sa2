package com.adri.sa2.exception;

import com.adri.sa2.application.register.RegisterStorage;

public class RegisterStorageException extends RuntimeException{
    public RegisterStorageException(String message){
        super(message);
    }
}
