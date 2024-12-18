package com.itmo.techserv.exceptions;
import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingException extends RuntimeException {
    private HttpStatus httpStatus;
    public BookingException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
