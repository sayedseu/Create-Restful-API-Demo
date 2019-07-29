package com.example.restapidemo.exception;

public class ResourceDoesNotFoundException extends Exception {
    public ResourceDoesNotFoundException(String resource) {
        super(resource +" Not found");
    }
}
