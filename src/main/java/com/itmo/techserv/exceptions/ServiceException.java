package com.itmo.techserv.exceptions;
import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceException extends RuntimeException{
    private HttpStatus httpStatus;

    public ServiceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
