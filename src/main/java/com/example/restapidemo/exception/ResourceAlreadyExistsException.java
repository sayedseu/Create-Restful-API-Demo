package com.example.restapidemo.exception;

public class ResourceAlreadyExistsException extends Exception{
    public ResourceAlreadyExistsException(String resource) {
        super(resource + " Already exists");
    }
}
