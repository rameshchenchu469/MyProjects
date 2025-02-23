package com.hunger.saviour.portal.exceptions;

import org.springframework.stereotype.Component;

@Component
public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String message) {
        super(message);
    }
}
