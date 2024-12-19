package com.itmo.techserv.exceptions;
import com.itmo.techserv.dto.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class TotalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ServiceException.class})
    protected ResponseEntity<Object> ServiceHandle(ServiceException ex, WebRequest request){
        ErrorDTO errorDTO = new ErrorDTO(ex.getMessage(), LocalDateTime.now());
        return handleExceptionInternal(ex,errorDTO,new HttpHeaders(),ex.getHttpStatus(),request);
    }
    @ExceptionHandler(value = {BookingException.class})
    protected ResponseEntity<Object> BookingHandle(BookingException ex, WebRequest request){
        ErrorDTO errorDTO = new ErrorDTO(ex.getMessage(), LocalDateTime.now());
        return handleExceptionInternal(ex,errorDTO,new HttpHeaders(),ex.getHttpStatus(),request);
    }
}
